package com.codewithanas;

import java.util.ArrayList;
import java.util.Collections;

public class Genetic extends Method{
    ArrayList<Ville> villes;

    public Genetic() {
        super();
    }
    public Genetic(ArrayList<Ville> villes)
    {
        super();
        this.villes = villes;
    }

    public ArrayList<Solution> generateInitialPopulation(Solution sol)
    {
        ArrayList<Solution> population = new ArrayList<>();
        ArrayList<Ville> villeToShuffle = new ArrayList<Ville>(sol.getVilles().subList(1, sol.getVilles().size() - 1));
        int j = 0;
        System.out.println("---------population---------");
        while (j < 100)
        {
            Solution s = new Solution();
            Collections.shuffle(villeToShuffle);
            for (int i=0; i < sol.getVilles().size(); i++)
            {
                if ((i == 0) || (i == (sol.getVilles().size() - 1)))
                    s.setVille(sol.getVilles().get(i));
                else
                    s.setVille(villeToShuffle.get(i - 1));
            }
            s.setTotalDistance(this.calculTotalDistance(s.getVilles()));
            population.add(s);
            System.out.println(s);
            j++;
        }
        System.out.println("--------------------------------");
        return population;
    }

    public ArrayList<Solution> selectRoulette(ArrayList<Solution> population)
    {
        ArrayList<Solution> meilleurs = new ArrayList<>();
        double valeurMoyen = 0.0;
        int m = population.size();
        double proba;
        double limitProba = 1;
        double fitness[] = new double[m];
        for (int i = 0; i < m; i++)
        {
            fitness[i] = 1.0 / population.get(i).getTotalDistance();
            valeurMoyen += fitness[i];
        }
        valeurMoyen = valeurMoyen / m;
        while (( meilleurs.size() < (int) m/2 ) && limitProba > 0)
        {
            for (int i = 0; i < m; i++)
            {
                //proba = valeurMoyen / fitness[i];
                proba = fitness[i] / valeurMoyen;
                //System.out.println("proba : " + proba + "  distance : " + population.get(i).getTotalDistance());
                if (proba > limitProba)
                {
                    //System.out.println("selection : proba : " + proba + "  distance : " + population.get(i).getTotalDistance());
                    meilleurs.add(population.get(i));
                }
            }
            limitProba -= 0.1;
        }
        return meilleurs;
    }

    public ArrayList<Solution> selectMutationPermute(ArrayList<Solution> population)
    {
        ArrayList<Solution> selections = new ArrayList<>();
        int i = 0;
        int j = 0;
        for (int k = 0; k < population.size(); k++)
        {
            Solution s = new Solution();
            for (Ville v: population.get(k).getVilles())
                s.setVille(v);
            selections.add(s);
            while (i == j || i == 0 || j == 0)
            {
                i = (int)Math.floor(Math.random()*(population.get(k).getVilles().size() - 2));
                j = (int)Math.floor(Math.random()*(population.get(k).getVilles().size() - 2));
            }
            Collections.swap(selections.get(k).getVilles(),i,j);
            selections.get(k).setTotalDistance(calculTotalDistance(selections.get(k).getVilles()));
        }
        return selections;
    }

    private Ville chercherVille(ArrayList<Ville> parent, ArrayList<Ville> enfant)
    {
        int i = 0;
        while (i < parent.size() && enfant.contains(parent.get(i)))
            i++;
        return parent.get(i);
    }
    public Solution genererEnfant(ArrayList<Ville> parent1, ArrayList<Ville> parent2)
    {
        Solution enfant = new Solution();
        for (int i=0; i < parent1.size(); i++)
        {
            if(i < (parent1.size()/2))
                enfant.setVille(parent1.get(i));
            else
            {
                // pour eviter l'ajout d'une ville deja existe sauf la derniere ville car c'est elle la premiere
                if(!enfant.getVilles().contains(parent2.get(i)) || (i == parent2.size() - 1) )
                    enfant.setVille(parent2.get(i));
                else
                    enfant.setVille(chercherVille(parent2,enfant.getVilles()));
            }
        }
        enfant.setTotalDistance(calculTotalDistance(enfant.getVilles()));
        return enfant;
    }

    public ArrayList<Solution> genererDesEnfants(ArrayList<Solution> parents)
    {
        ArrayList<Solution> enfants = new ArrayList<>();

        for (int i=0; i < parents.size(); i=i+2)
        {
            if(i < (parents.size() - 1)) // condition pour eviter le probleme si le nombre de population est impaire
                enfants.add(genererEnfant(parents.get(i).getVilles(),parents.get(i+1).getVilles()));
        }

        return enfants;
    }

    @Override
    public Solution solution() {
        int i = 0;
        Route route = new Route(this.villes);
        Solution solutionCourante = route.glouton();
        System.out.println("------------Glouton-------------");
        solutionCourante.affiche();
        System.out.println("----------------------------------");
        ArrayList<Solution> population = generateInitialPopulation(solutionCourante);
        //population.add(solutionCourante);
        while (i < 10)
        {
            ArrayList<Solution> selectionCroisement = selectRoulette(population);
            ArrayList<Solution> enfantsCroisement = genererDesEnfants(selectionCroisement);
            ArrayList<Solution> selectionMutation = selectMutationPermute(population);
            ArrayList<Solution> enfantsMutation = genererDesEnfants(selectionMutation);
            ArrayList<Solution> populationToJoin = new ArrayList<>();
            populationToJoin.addAll(selectionCroisement);
            populationToJoin.addAll(enfantsCroisement);
            populationToJoin.addAll(enfantsMutation);
            population = selectRoulette(populationToJoin);
            i++;
        }
        Solution meilleurSolution = this.getMinSolution(population);

        /*for (Solution s: population)
        {
            System.out.println("population final : ");
            s.affiche();
        }*/
        System.out.println("------------Meilleur-------------");
        meilleurSolution.affiche();
        System.out.println("----------------------------------");
        /*for (Solution s: population)
        {
            System.out.println("initial-2: ");
            s.affiche();
        }
        for (Solution s: selectionMutation)
        {
            System.out.println("mutation:");
            s.affiche();
        }*/


        return null;
    }
}

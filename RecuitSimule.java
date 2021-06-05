package com.codewithanas;

import java.util.ArrayList;

public class RecuitSimule extends Method {

    ArrayList<Ville> villes;

    public RecuitSimule(ArrayList<Ville> villes) {
        this.villes = villes;
    }

    public RecuitSimule() {
        super();
    }

    public ArrayList<Ville> getVilles() {
        return villes;
    }

    public void setVilles(ArrayList<Ville> villes) {
        this.villes = villes;
    }

    private Solution genererSolutionAleatoir(Solution s)
    {
        ArrayList<Solution> voisins = getVoisins(s);
        return (voisins.get((int)Math.floor(Math.random()*(voisins.size() - 1))));
    }

    public Solution solution() {
        Route route = new Route(this.villes);
        Solution solutionCourante = route.glouton();
        Solution meilleurSol = solutionCourante;

        double temperature = 1000;
        while ( temperature > 1 ){
            boolean conditionEquilibre = false;
            do {
                Solution solutionAleatoire = genererSolutionAleatoir(solutionCourante);
                if ( boltezman(solutionCourante, solutionAleatoire, temperature) > Math.random()){
                    solutionCourante = solutionAleatoire;
                    conditionEquilibre = true;
                }
            } while (!conditionEquilibre);
            if (solutionCourante.getTotalDistance() < meilleurSol.getTotalDistance())
                meilleurSol = solutionCourante;
            temperature = temperature / 10;
        }
        return meilleurSol;
    }

    private double boltezman(Solution solutionCourante, Solution solutionAleatoire, double temperature) {
        if ( solutionCourante.getTotalDistance() > solutionAleatoire.getTotalDistance())
            return 1.0;
        else
            return Math.exp((solutionCourante.getTotalDistance() - solutionAleatoire.getTotalDistance()) / temperature);
    }

}

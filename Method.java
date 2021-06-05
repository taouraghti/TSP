package com.codewithanas;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Method {

    protected double calculTotalDistance(ArrayList<Ville> villes)
    {
        double distance = 0.0;
        for (int k = 0; k < villes.size(); k++)
        {
            if(k == (villes.size() - 1))
                distance += villes.get(k).mesurerDistance(villes.get(0));
            else
                distance += villes.get(k).mesurerDistance(villes.get(k + 1));
        }
        return distance;
    }

    protected Solution voisinOpt2(Solution s, int i, int j)
    {
        Solution v = new Solution();
        ArrayList<Ville> tmp = new ArrayList<>(s.getVilles());
        Collections.swap(tmp,i,j);
        v.setVilles(tmp);
        v.setTotalDistance(calculTotalDistance(v.getVilles()));
        return v;
    }

    protected ArrayList<Solution> getVoisins(Solution s)
    {
        ArrayList<Solution> voisins = new ArrayList<>();
        for (int i = 1; i < (s.getVilles().size() - 2); i++)
        {
            voisins.add(voisinOpt2(s,i, i + 1));
        }
        /*System.out.println("---------------------");
        System.out.println("les voisins: ");
        for (Solution sol : voisins
        ) {
            sol.affiche();
        }
        System.out.println("---------------------");*/
        return voisins;
    }

    public Solution getMinSolution(ArrayList<Solution> solutions)
    {
        double min = solutions.get(0).getTotalDistance();
        int indice = 0;
        for (int i = 0; i < solutions.size(); i++)
        {
            if (solutions.get(i).getTotalDistance() < min)
            {
                min = solutions.get(i).getTotalDistance();
                indice = i;
            }
        }
        return solutions.get(indice);
    }

    public abstract Solution solution();
}

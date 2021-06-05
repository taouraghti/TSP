package com.codewithanas;

import java.util.ArrayList;

public class Descente extends Method{

    private ArrayList<Ville> villes;
    public Descente()
    {
        super();
    }

    public Descente(ArrayList<Ville> villes) {
        this.villes = villes;
    }

    private Solution getMinVoisin(ArrayList<Solution> voisins)
    {
        double min = voisins.get(0).getTotalDistance();
        int indice = 0;
        for (int i = 0; i < voisins.size(); i++)
        {
            if (voisins.get(i).getTotalDistance() < min)
            {
                min = voisins.get(i).getTotalDistance();
                indice = i;
            }
        }
        return voisins.get(indice);
    }

    public Solution solution()
    {
        Route route = new Route(this.villes);
        Solution solution = route.glouton();

        while (true)
        {
            ArrayList<Solution> voisins = getVoisins(solution);
            Solution minVoisin = getMinVoisin(voisins);
            if(solution.getTotalDistance() <=  minVoisin.getTotalDistance())
                break;
            else
                solution = minVoisin;
        }
        return solution;
    }

}

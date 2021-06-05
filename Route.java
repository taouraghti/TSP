package com.codewithanas;

import java.util.ArrayList;

public class Route {
    private ArrayList<Ville> villes = new ArrayList<Ville>();

    public Route(ArrayList<Ville> villes)
    {
        this.villes = villes;
    }
   /* private Ville plusProche(int rand, ArrayList<Ville> villes)
    {
        double min = 0.0;
        Ville v = null;
        for (int i = 0; i < villes.size(); i++)
        {
            if((villes.get(rand).mesurerDistance(villes.get(i)) <= min
                    && villes.get(rand).mesurerDistance(villes.get(i)) != 0.0) || min == 0.0)
            {
                min = villes.get(rand).mesurerDistance(villes.get(i));
                v = villes.get(i);
            }
        }
        return v;
    }

    public Solution glouton()
    {
        int rand = 0;
        int first = rand;
        Ville v = null;
        int i = 0;
        Solution s = null;

        ArrayList<Ville> tempVilles = new ArrayList<Ville>();
        Collections.copy(tempVilles,this.villes);
        s.setTotalDistance(0.0);
        s.setVilles(this.villes.get(rand));
        while (i < this.villes.size())
        {
            v = plusProche(rand ,tempVilles);
            s.setVilles(v);
            s.setTotalDistance(s.getTotalDistance() + tempVilles.get(rand).mesurerDistance(v));
            tempVilles.remove(rand);
        }
        s.setTotalDistance(s.getTotalDistance() + this.villes.get(first).mesurerDistance(v));
        return s;
    }*/
    private Double[][] generateMatrice(ArrayList<Ville> villes)
    {
        Double[][]matrice = new Double[villes.size()][villes.size()];
        for (int i=0; i < villes.size(); i++)
        {
            for (int j = 0; j < villes.size(); j++)
            {
                if (i == j)
                    matrice[i][j] = 0.0;
                else
                    matrice[i][j] = villes.get(i).mesurerDistance(villes.get(j));
                //System.out.print(matrice[i][j] + "  ");
            }
            //System.out.println();
        }
        return matrice;
    }

    private int getMin(Double[]tab, ArrayList<Ville> tmp)
    {
        double min = 0.0;
        int indice = 0;

        for (int i = 0; i < tab.length; i++)
        {
            if(!tmp.contains(this.villes.get(i)) && tab[i] != 0.0)
            {
                min = tab[i];
                indice = i;
                break;
            }
        }
        for (int i=0; i < tab.length; i++)
        {
            if (!tmp.contains(this.villes.get(i)))
            {
                if(min > tab[i] && tab[i] != 0.0)
                {
                    min = tab[i];
                    indice = i;
                }
            }
        }
        return indice;
    }
    public Solution glouton()
    {
        Double[][] matrice;
        Solution s = new Solution();
        int j = 0, k = 0;

        matrice = generateMatrice(this.villes);
        s.setVille(this.villes.get(0));
        for (int i=0; i < (villes.size() - 1); i++)
        {
            k = j;
            j = getMin(matrice[k],s.getVilles());
            s.setVille(this.villes.get(j));
            s.setTotalDistance(s.getTotalDistance() + matrice[k][j]);
        }
        s.setVille(this.villes.get(0));
        s.setTotalDistance(s.getTotalDistance() + matrice[j][0]);
        return s;
    }
}

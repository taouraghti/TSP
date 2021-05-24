package com.codewithanas;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static ArrayList<Ville> generateVilles(int n)
    {
        ArrayList<Ville> villes = new ArrayList<>();

        Random rd = new Random();
        double v = 100.0;
        for (int i = 0; i < n ; i++)
        {
            Ville ville = new Ville();
            ville.setX(rd.nextDouble() * v);
            ville.setY(rd.nextDouble() * v);
            ville.setName("ville" + (i+1));
            villes.add(ville);
        }
        return villes;
    }
    public static void main(String[] args)
    {

        ArrayList<Ville> villes;
        villes = generateVilles(3);
        /*for (Ville v: villes) {
            v.affiche();
        }*/
        Route r = new Route(villes);
        r.glouton().affiche();
    }
}

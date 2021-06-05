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
        villes = generateVilles(100);
        /*for (Ville v: villes) {
            v.affiche();
        }*/

        //Route r = new Route(villes);
        //r.glouton().affiche();

        System.out.println("------------Descente-------------");
        Descente d = new Descente(villes);
        d.solution().affiche();
        System.out.println("----------------------------------");

        System.out.println("------------Recuit-------------");
        RecuitSimule rec = new RecuitSimule(villes);
        rec.solution().affiche();
        System.out.println("----------------------------------");

        Genetic g = new Genetic(villes);
        g.solution();
    }
}

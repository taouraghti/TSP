package com.codewithanas;

import java.util.ArrayList;

public class Solution {

    private ArrayList<Ville> villes = new ArrayList<>();
    private double totalDistance;

    public Solution() {
        super();
        this.totalDistance = 0.0;
    }

    public ArrayList<Ville> getVilles() {
        return villes;
    }

    public void setVille(Ville ville) {
        this.villes.add(ville);
    }

    public void setVilles(ArrayList<Ville> villes) {
        this.villes = villes;
    }


    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "villes=" + villes +
                ", totalDistance=" + totalDistance +
                '}';
    }
    public void affiche()
    {
        System.out.println(this.toString());
    }
}

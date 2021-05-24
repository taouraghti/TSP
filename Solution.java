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

    public void setVilles(Ville ville) {
        this.villes.add(ville);
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

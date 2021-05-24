package com.codewithanas;

public class Ville {

    private String name;
    private double x;
    private double y;

    public Ville() {
        super();
    }

    public Ville(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public double mesurerDistance(Ville b)
    {
        return (Math.sqrt(Math.pow((this.x - b.getX()),2) + Math.pow((this.y - b.getY()),2)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
    public void affiche(){
        System.out.println(this.toString());
    }
}

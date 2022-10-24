package be.ugent.oplossing.model;

public class Vlakje {
    // Create a nested array for points
    double[][] points = new double[4][3];

    String color;

    public Vlakje(double[][] points, String color) {
        this.points = points.clone();
        this.color = color;
    }
}

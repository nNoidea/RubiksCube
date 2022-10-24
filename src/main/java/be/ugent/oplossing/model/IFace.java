package be.ugent.oplossing.model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public interface IFace {
    // Create a nested array for points
    double[][] points = new double[4][3];

    String color;

    public Vlakje(double[][] points, String color) {
        this.points = points.clone();
        this.color = color;
    }

    /////////////////////////////////////////

    Color getFaceColor();

    // Returns co(x,y,z) of the corners in (counter) clock-wise
    Point3D[] getFaceCorners();
}

package be.ugent.oplossing.model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Vlakje implements IFace{
    // Create a nested array for points
    private Point3D[] punten ;
    private Color kleur;

    double[][] points;
    String color;

    public Vlakje(Point3D[] points, Color color) {
        this.punten = points;
        this.kleur = color;
    }
    public Vlakje(double[][] points, String color){
        this.color=color;
        this.points=points;
    }

    @Override
    public Color getFaceColor() {
        return this.kleur;
    }

    @Override
    public Point3D[] getFaceCorners() {
        return punten;
    }
    public String toString(){
        return Arrays.toString(punten)+"   "+ kleur;
    }
}

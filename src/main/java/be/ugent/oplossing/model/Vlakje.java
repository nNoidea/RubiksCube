package be.ugent.oplossing.model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Vlakje implements IFace {
    // Create a nested array for points
    private Point3D[] punten;
    private Color kleur;


    public Vlakje(Point3D[] points, Color color) {
        this.punten = points;
        this.kleur = color;
    }

    @Override
    public Color getFaceColor() {
        return this.kleur;
    }

    @Override
    public Point3D[] getFaceCorners() {
        return punten;
    }

    public String toString() {
        return Arrays.toString(punten) + "   " + kleur;
    }
}

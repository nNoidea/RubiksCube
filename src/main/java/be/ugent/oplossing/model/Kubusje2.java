package be.ugent.oplossing.model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Kubusje2 {

    private Point3D middelpunt;
    private Point3D[] hoekpunten;
    private Vlakje[] vlakjes = new Vlakje[6];
    private String[] kleuren;

    public Kubusje2(Point3D middelpunt, String[] kleuren) {
        // kubusje wordt gemaakt op basis van zijn middelpunt (x,y,z)
        this.middelpunt = middelpunt;
        double x = middelpunt.getX();
        double y = middelpunt.getY();
        double z = middelpunt.getZ();

        // op basis van het middelpunt worden de andere 8 hoekpunten van het kubusje
        // berekend
        // geautomatiseerbaar?
        this.hoekpunten = new Point3D[] { new Point3D(x - 1, y - 1, z - 1), new Point3D(x + 1, y - 1, z - 1),
                new Point3D(x + 1, y + 1, z - 1), new Point3D(x - 1, y + 1, z - 1),
                new Point3D(x - 1, y - 1, z + 1), new Point3D(x + 1, y - 1, z + 1),
                new Point3D(x + 1, y + 1, z + 1), new Point3D(x - 1, y + 1, z + 1) };

        // op basis van de hoekpunten worden er 6 vlakken geinitializeerd
        this.kleuren = kleuren;
        maakVlakjes();

    }

    private Point3D rotatePoint(char axis, Point3D coordinaat, double angle) {
        // draai individueel punt rond de z-as
        if (axis == 'z') {
            if (coordinaat.getY() > 0) {
                angle += Math.atan(-coordinaat.getX() / coordinaat.getY());
            } else {
                angle += (Math.atan(-coordinaat.getX() / coordinaat.getY())) + Math.PI;
            }
            double r = Math.sqrt(coordinaat.getX() * coordinaat.getX() + coordinaat.getY() * coordinaat.getY());
            Point3D coordinate = new Point3D(-r * Math.sin(angle), r * Math.cos(angle), coordinaat.getZ());
            return coordinate;
        }

        // draai individueel punt rond de x-as
        else if (axis == 'x') {
            if (coordinaat.getY() > 0) {
                angle += Math.atan(coordinaat.getZ() / coordinaat.getY());
            } else {
                angle += (Math.atan(coordinaat.getZ() / coordinaat.getY())) + Math.PI;
            }
            double r = Math.sqrt(coordinaat.getZ() * coordinaat.getZ() + coordinaat.getY() * coordinaat.getY());
            Point3D coordinate = new Point3D(coordinaat.getX(), r * Math.cos(angle), r * Math.sin(angle));
            return coordinate;
        }

        // draai individueel punt rond de y-as
        else {
            if (coordinaat.getX() < 0) {
                angle += Math.atan(coordinaat.getZ() / (-coordinaat.getX()));
            } else {
                angle += (Math.atan(coordinaat.getZ() / (-coordinaat.getX()))) + Math.PI;
            }
            double r = Math.sqrt(coordinaat.getZ() * coordinaat.getZ() + coordinaat.getX() * coordinaat.getX());
            Point3D coordinate = new Point3D(-1 * r * Math.cos(angle), coordinaat.getY(), r * Math.sin(angle));
            return coordinate;
        }
    }

    public void rotateKubusje(char axis, double angle) {
        // draai alle hoekpunten van het kubusje
        // pas dan ook de vlakjes jan
        for (int i = 0; i < 8; i++) {
            hoekpunten[i] = rotatePoint(axis, hoekpunten[i], angle);
            maakVlakjes();
        }
    }

    private Color switchColor(String kleur) {
        // maakt van een string een kleur
        if (kleur.equalsIgnoreCase("red"))
            return Color.RED;
        else if (kleur.equalsIgnoreCase("orange"))
            return Color.ORANGE;
        else if (kleur.equalsIgnoreCase("green"))
            return Color.GREEN;
        else if (kleur.equalsIgnoreCase("blue"))
            return Color.BLUE;
        else if (kleur.equalsIgnoreCase("yellow"))
            return Color.YELLOW;
        else if (kleur.equalsIgnoreCase("white"))
            return Color.WHITE;
        return Color.BLACK;
    }

    public Vlakje[] getVlakjes() {
        return vlakjes;
    }

    private void maakVlakjes() {
        this.vlakjes[0] = new Vlakje(new Point3D[] { hoekpunten[0], hoekpunten[1], hoekpunten[2], hoekpunten[3] },
                switchColor(kleuren[1]));
        this.vlakjes[1] = new Vlakje(new Point3D[] { hoekpunten[4], hoekpunten[5], hoekpunten[6], hoekpunten[7] },
                switchColor(kleuren[0]));
        this.vlakjes[2] = new Vlakje(new Point3D[] { hoekpunten[0], hoekpunten[1], hoekpunten[5], hoekpunten[4] },
                switchColor(kleuren[2]));
        this.vlakjes[3] = new Vlakje(new Point3D[] { hoekpunten[2], hoekpunten[3], hoekpunten[7], hoekpunten[6] },
                switchColor(kleuren[3]));
        this.vlakjes[4] = new Vlakje(new Point3D[] { hoekpunten[1], hoekpunten[2], hoekpunten[6], hoekpunten[5] },
                switchColor(kleuren[4]));
        this.vlakjes[5] = new Vlakje(new Point3D[] { hoekpunten[0], hoekpunten[3], hoekpunten[7], hoekpunten[4] },
                switchColor(kleuren[5]));
    }

    public Point3D getMiddelpunt() {
        return middelpunt;
    }

    public Point3D[] getHoekpunten() {
        return hoekpunten;
    }

    public String toString() {
        return middelpunt.toString();
    }
}
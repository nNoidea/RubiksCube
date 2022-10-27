package be.ugent.oplossing.model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class Kubusje {

    private Point3D middelpunt;
    private Point3D[] hoekpunten;
    private Vlakje[] vlakjes = new Vlakje[6];
    private String[] kleuren;

    public Kubusje(Point3D middelpunt, String[] kleuren) {
        // kubusje wordt gemaakt op basis van zijn middelpunt (x,y,z)
        this.middelpunt = middelpunt;
        double x = middelpunt.getX();
        double y = middelpunt.getY();
        double z = middelpunt.getZ();

        // op basis van het middelpunt worden de andere 8 hoekpunten van het kubusje
        // berekend
        // geautomatiseerbaar?
        this.hoekpunten = new Point3D[] {
                new Point3D(x - 1, y - 1, z - 1), new Point3D(x + 1, y - 1, z - 1),
                new Point3D(x + 1, y + 1, z - 1), new Point3D(x - 1, y + 1, z - 1),
                new Point3D(x - 1, y - 1, z + 1), new Point3D(x + 1, y - 1, z + 1),
                new Point3D(x + 1, y + 1, z + 1), new Point3D(x - 1, y + 1, z + 1) };

        // op basis van de hoekpunten worden er 6 vlakken geinitializeerd
        this.kleuren = kleuren;
        maakVlakjes();

    }

    private Point3D rotatePoint(char axis, Point3D coordinaat, double degrees) {
        double[] puntMatrix = { coordinaat.getX(), coordinaat.getY(), coordinaat.getZ() };

        double myCos = Math.cos(Math.toRadians(degrees));
        double mySin = Math.sin(Math.toRadians(degrees));

        double rotationMatrix[][] = new double[3][3];
        if (axis == 'x') {
            rotationMatrix = new double[][] {
                    { 1, 0, 0 },
                    { 0, myCos, -mySin },
                    { 0, mySin, myCos } };
        } else if (axis == 'y') {
            rotationMatrix = new double[][] {
                    { myCos, 0, mySin },
                    { 0, 1, 0 },
                    { -mySin, 0, myCos } };
        } else if (axis == 'z') {
            rotationMatrix = new double[][] {
                    { myCos, -mySin, 0 },
                    { mySin, myCos, 0 },
                    { 0, 0, 1 } };
        }

        // rotationMatrix X puntMatrix = finalPunten
        double[] finalPunten = new double[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                finalPunten[i] += rotationMatrix[i][j] * puntMatrix[j];
            }
        }

        return new Point3D(finalPunten[0], finalPunten[1], finalPunten[2]);
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
        kleur = kleur.toLowerCase();

        // maakt van een string een kleur
        if (kleur.equals("red"))
            return Color.RED;
        else if (kleur.equals("orange"))
            return Color.ORANGE;
        else if (kleur.equals("green"))
            return Color.GREEN;
        else if (kleur.equals("blue"))
            return Color.BLUE;
        else if (kleur.equals("yellow"))
            return Color.YELLOW;
        else if (kleur.equals("white"))
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
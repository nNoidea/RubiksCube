package be.ugent.oplossing.model;

import be.ugent.oplossing.show.RubiksReader;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RubiksKubus implements IRubikCube {

    private List<Kubusje> kubusjes;
    private List<String> corList = new ArrayList<String>();

    // Alle gegevens voor de 27 kubusjes werden in een excel-bestand ingevuld en
    // bewaard.
    // Excel biedt dankzij z'n kolommen een duidelijk overzicht, er kan heel
    // gestructureerd gewerkt
    // (en gecontroleerd) worden.
    // Inlezen van het bestand werd afgekeken van de gegeven code in de show-map.
    // (Moet niet gekend zijn voor op het examen.)
    public RubiksKubus() throws FileNotFoundException {
        kubusjes = new ArrayList<>();

        Scanner sc = new Scanner(
                new File(Objects.requireNonNull(RubiksReader.class.getResource("kubussen_xyz.csv")).getFile()));
        while (sc.hasNext()) {
            Scanner sc_ = new Scanner(sc.nextLine());
            sc_.useDelimiter(";");
            int x = sc_.nextInt();
            int y = sc_.nextInt();
            int z = sc_.nextInt();
            String[] kleuren = new String[6];
            for (int i = 0; i < 6; i++) {
                kleuren[i] = sc_.next();
            }
            kubusjes.add(new Kubusje(x, y, z, kleuren));
        }

    }

    // Dit kan je gebruiken om zelf te testen, zolang de view er nog niet is.
    // Layout niet handig? Pas zelf aan.
    public String toString() {
        // kan je later met streams doen
        String[] strings = new String[kubusjes.size()];
        int i = 0;
        for (Kubusje kubus : kubusjes) {
            strings[i++] = kubus.toString();
        }
        return String.join("\n", strings);
    }

    // Deze methode wordt gebruikt door het showteam om de View te maken.
    // Meer is er niet nodig (in tegenstelling tot wat in sprint0 aangekondigd werd,
    // dus geen onderscheid tussen zichtbare en onzichtbare vlakjes).
    @Override
    public List<IFace> getAllFaces() {
        List<IFace> list = new ArrayList<>();
        for (Kubusje kubus : kubusjes) {
            for (Vlakje vlak : kubus.getVlakjes()) {
                list.add(vlak);
            }
        }
        return list;
    }

    @Override
    public void rotate(Color color, boolean clockwise) {
    }

    public List<Kubusje> getKubusjes() {
        return kubusjes;
    }

    @Override
    public List<IFace> getRotation(Color color, int degree) {
        int originalDegree = degree;

        // Normalize degree.
        if (degree < 0) {
            degree = -5;
        } else if (degree > 0) {
            degree = 5;
        }

        List<IFace> faces = getAllFaces();
        Hoekpunt[] p = new Hoekpunt[4];
        int length = faces.size();
        int coordinate = 0;
        int dimension = 0;

        // Get the determenistic dimension and coordinate
        char axis = 'x';
        if (color == Color.WHITE) {
            coordinate = -3;
            dimension = 1; // y
        } else if (color == Color.RED) {
            coordinate = 3;
            dimension = 0;
        } else if (color == Color.BLUE) {
            coordinate = -3;
            dimension = 2;
        } else if (color == Color.GREEN) {
            coordinate = 3;
            dimension = 2;
        } else if (color == Color.YELLOW) {
            coordinate = 3;
            dimension = 1;
        } else if (color == Color.ORANGE) {
            coordinate = -3;
            dimension = 0;
        }

        // Get the axis based on the constant dimension.
        if (dimension == 0) {
            axis = 'x';
        } else if (dimension == 1) {
            axis = 'y';
        } else if (dimension == 2) {
            axis = 'z';
        }

        if (originalDegree == 0) {
            corList = new ArrayList<String>(); // Empty the corner list.

            // Mark all faces false for rotation at the start of the animation.
            for (int i = 0; i < length; i++) {
                ((Vlakje) faces.get(i)).rotatationMarked = false;
            }

            // At the start of the animation, mark the relevant faces that are going to be
            // rotated.
            int a = 0;
            for (int i = 0; i < length; i++) {
                int test = 0;
                for (int j = 0; j < 4; j++) {
                    double cor[] = new double[3];
                    Point3D corner = faces.get(i).getFaceCorners()[j];
                    cor[0] = corner.getX();
                    cor[1] = corner.getY();
                    cor[2] = corner.getZ();

                    if ((int) cor[dimension] == coordinate) {
                        ((Vlakje) faces.get(i)).rotatationMarked = true;
                        break;
                    }

                    if (Math.abs(Math.round(cor[dimension] - coordinate)) == 2) {
                        test++;
                        // if all 4 corners of a face is 2 values away and its corners are all unique,
                        // mark it.
                        if (test == 4) {
                            if (checkDouble((Vlakje) faces.get(i))) {
                                System.out.println(++a); // If this prints something else than 9 as the final value,
                                                         // there's an error. If it doesn't, everything is fine.
                                ((Vlakje) faces.get(i)).rotatationMarked = true;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < length; i++) {
            if (((Vlakje) faces.get(i)).rotatationMarked) {
                p = getNewCorArray((Vlakje) faces.get(i), axis, degree);
                ((Vlakje) faces.get(i)).changeHoekpunten(p);
            }
        }

        // If its the last frame, round all the coordinates.
        if (Math.abs(originalDegree) == 90) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < 4; j++) {
                    int cor[] = new int[3];
                    Point3D corner = faces.get(i).getFaceCorners()[j];
                    cor[0] = (int) Math.round(corner.getX());
                    cor[1] = (int) Math.round(corner.getY());
                    cor[2] = (int) Math.round(corner.getZ());

                    p[j] = new Hoekpunt(cor[0], cor[1], cor[2]);
                }
                ((Vlakje) faces.get(i)).changeHoekpunten(p);
            }
        }
        return faces;
    }

    // Get all 4 coordinates. Separate each dimension, then sort them from low to
    // high and then sum them as a string to create a normalized variable to
    // compare.
    public boolean checkDouble(Vlakje face) {
        int[] arrX = new int[4];
        int[] arrY = new int[4];
        int[] arrZ = new int[4];

        String coordinate = "";
        for (int i = 0; i < 4; i++) {
            Point3D corner = face.getFaceCorners()[i];
            arrX[i] = (int) Math.round(corner.getX());
            arrY[i] = (int) Math.round(corner.getY());
            arrZ[i] = (int) Math.round(corner.getZ());
        }

        Arrays.sort(arrX);
        Arrays.sort(arrY);
        Arrays.sort(arrZ);

        coordinate = Arrays.toString(arrX) + Arrays.toString(arrY) + Arrays.toString(arrZ);
        // System.out.println(coordinate);

        if (corList.contains(coordinate)) {
            return false;
        } else {
            corList.add(coordinate);
            return true;
        }
    }

    public Hoekpunt[] getNewCorArray(Vlakje face, char axis, int degree) {
        Hoekpunt[] p = new Hoekpunt[4];
        for (int j = 0; j < 4; j++) {
            double x = face.getFaceCorners()[j].getX();
            double y = face.getFaceCorners()[j].getY();
            double z = face.getFaceCorners()[j].getZ();

            Point3D originalCorner = new Point3D(x, y, z);
            Point3D newCorner = rotatePoint(axis, originalCorner, degree);

            x = newCorner.getX();
            y = newCorner.getY();
            z = newCorner.getZ();

            p[j] = new Hoekpunt(x, y, z);
        }
        return p;
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

}

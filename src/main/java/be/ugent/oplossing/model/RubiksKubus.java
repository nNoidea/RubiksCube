package be.ugent.oplossing.model;

import javafx.geometry.Point3D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RubiksKubus implements IRubikCube {
    private Kubusje[] kubusjes = new Kubusje[27];

    public RubiksKubus(String bestandsnaam) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(getClass().getResource(bestandsnaam).getPath()));

        int i = 0;
        while (scanner.hasNextLine()) {
            String[] lineElement = scanner.nextLine().split(",");

            Point3D point = new Point3D(Integer.parseInt(lineElement[0]), Integer.parseInt(lineElement[1]),
                    Integer.parseInt(lineElement[2]));

            String[] kleuren = { lineElement[3], lineElement[4], lineElement[5], lineElement[6], lineElement[7],
                    lineElement[8] };

            kubusjes[i] = new Kubusje(point, kleuren);
            i++;
        }
        scanner.close();

        // toont kubus tijdens manipulatie
        rotateGroepKubusjes('y', -2, 45);
    }

    public void rotateGroepKubusjes(char axis, double coordinaat, double angle) {
        // welke kubusjes moeten gedraaid worden
        for (int i = 0; i < kubusjes.length; i++) {
            if ((axis == 'z' && kubusjes[i].getMiddelpunt().getZ() == coordinaat) ||
                    (axis == 'y' && kubusjes[i].getMiddelpunt().getY() == coordinaat) ||
                    (axis == 'x' && kubusjes[i].getMiddelpunt().getX() == coordinaat)) {
                kubusjes[i].rotateKubusje(axis, angle);
            }
        }
    }

    @Override
    public List<IFace> getAllFaces() {
        // voor elk kubusje worden alle vlakken toegevoegd aan de lijst met vlakken

        List<IFace> vlakken = new ArrayList();
        for (int i = 0; i < 27; i++) {
            for (int i2 = 0; i2 < 6; i2++) {
                vlakken.add(kubusjes[i].getVlakjes()[i2]);
            }
        }
        return vlakken;
    }
}

package be.ugent.oplossing.model;

import javafx.geometry.Point3D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RubiksKubus3 implements IRubikCube {

    private Kubusje2[] kubusjes = new Kubusje2[27];

    public RubiksKubus3(String bestandsnaam) throws FileNotFoundException {
        Scanner sc = new Scanner(
                new File(Objects.requireNonNull(RubiksKubus3.class.getResource(bestandsnaam)).getFile()));
        int teller = 0;
        while (sc.hasNextLine()) {
            Scanner s = new Scanner(sc.nextLine()).useDelimiter(",");
            kubusjes[teller] = new Kubusje2(new Point3D(s.nextDouble(), s.nextDouble(), s.nextDouble()),
                    new String[] { s.next(), s.next(), s.next(), s.next(), s.next(), s.next() });
            s.close();
            teller++;
        }
        sc.close();

        // toont kubus tijdens manipulatie
        // turn('z',2,Math.PI/6);
    }

    public void turn(char axis, double coordinaat, double angle) {
        // welke kubusjes moeten gedraaid worden
        List<Kubusje2> tedraaien = new ArrayList<>();
        for (int i = 0; i < kubusjes.length; i++) {
            if ((axis == 'z' && kubusjes[i].getMiddelpunt().getZ() == coordinaat) ||
                    (axis == 'y' && kubusjes[i].getMiddelpunt().getY() == coordinaat) ||
                    (axis == 'x' && kubusjes[i].getMiddelpunt().getX() == coordinaat)) {
                tedraaien.add(kubusjes[i]);
            }
        }

        // draai de kubusjes
        for (int i = 0; i < tedraaien.size(); i++) {
            tedraaien.get(i).rotateKubusje(axis, angle);
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

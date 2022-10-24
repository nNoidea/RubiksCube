package be.ugent.oplossing.model;

import java.util.Arrays;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        RubiksKubus cube = new RubiksKubus();

        for (int i = 0; i < 27; i++) {
            for (int i2 = 0; i2 < 6; i2++) {
                for (int i3 = 0; i3 < 4; i3++) {
                    String endChar = ",";
                    if (i3 == 3)
                        endChar = "";

                    System.out.print(Arrays.toString(cube.littleCube[i].planes[i2].points[i3]) + endChar);
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }

        test_1();
        test_2();
    }

    public static void test_1() {
        System.out.println("Schrijf hier zelf een test voor de geschreven code.");
    }

    public static void test_2() {
        System.out.println("Schrijf hier zelf een test voor de geschreven code.");
    }

}

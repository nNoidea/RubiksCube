import java.util.Arrays;

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
    }
}
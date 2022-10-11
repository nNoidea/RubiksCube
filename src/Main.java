import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[] myArray = { 0, 0, 0 };
        Kubusje myCube = new Kubusje(myArray);

        for (int i = 0; i < 6; i++){
            System.out.print(Arrays.toString(myCube.planes[i].points[0]) + ",");
            System.out.print(Arrays.toString(myCube.planes[i].points[1])+ ",");
            System.out.print(Arrays.toString(myCube.planes[i].points[2])+ ",");
            System.out.println(Arrays.toString(myCube.planes[i].points[3]));
        }
    }
}
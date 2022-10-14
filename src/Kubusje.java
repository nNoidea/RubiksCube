public class Kubusje {
    Vlakje[] planes = new Vlakje[6];

    public Kubusje(double[] startPoint) {
        String color = "white";
        double[][] points = new double[4][3];

        // Bottom
        resetToStartPoint(points, startPoint);
        // Increase x by 1
        points[1][0] += 1;
        // Increase y by 1
        points[2][1] += 1;
        // Increase both x and y by 1
        points[3][0] += 1;
        points[3][1] += 1;

        this.planes[0] = new Vlakje(points, color);
        copy2DArray(points);

        // Top
        // Is equal to the bottom plane with +1 on the z-axis
        for (int i = 0; i < 4; i++) {
            points[i][2] += 1;
        }
        this.planes[1] = new Vlakje(points, color);
        points = new double[4][3];

        // Left
        // Reset points to startPoint
        resetToStartPoint(points, startPoint);
        // Increase y by 1
        points[1][1] += 1;
        // Increase z by 1
        points[2][2] += 1;
        // Increase both y and z by 1
        points[3][1] += 1;
        points[3][2] += 1;
        this.planes[2] = new Vlakje(points, color);
        copy2DArray(points);

        // Right
        // Is equal to the left plane with +1 on the x-axis
        for (int i = 0; i < 4; i++) {
            points[i][0] += 1;
        }
        this.planes[3] = new Vlakje(points, color);
        points = new double[4][3];

        // Front
        // Reset points to startPoint
        resetToStartPoint(points, startPoint);
        // Increase x by 1
        points[1][0] += 1;
        // Increase z by 1
        points[2][2] += 1;
        // Increase both x and z by 1
        points[3][0] += 1;
        points[3][2] += 1;
        this.planes[4] = new Vlakje(points, color);
        copy2DArray(points);

        // Back
        // Is equal to the front plane with +1 on the y-axis
        for (int i = 0; i < 4; i++) {
            points[i][1] += 1;
        }
        this.planes[5] = new Vlakje(points, color);
    }

    public static void resetToStartPoint(double[][] points, double[] startPoint) {
        for (int i = 0; i < 4; i++) {
            points[i] = startPoint.clone();
        }
    }

    public static void copy2DArray(double[][] points) {
        for (int i = 0; i < points.length; i++) {
            points[i] = points[i].clone();
        }
    }
}

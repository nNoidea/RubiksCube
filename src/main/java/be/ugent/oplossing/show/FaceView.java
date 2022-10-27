package be.ugent.oplossing.show;

import be.ugent.oplossing.model.IFace;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class FaceView implements IFace {
    private final Color color;
    private final Point3D[] corners;

    public FaceView(String color, Point3D[] corners) {
        this.color = switch (color) {
            case "RED" -> Color.RED;
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            case "YELLOW" -> Color.YELLOW;
            case "ORANGE" -> Color.ORANGE;
            case "WHITE" -> Color.WHITE;
            default -> Color.BLACK;
        };
        this.corners = corners;
    }

    @Override
    public Color getFaceColor() {
        return color;
    }

    @Override
    public Point3D[] getFaceCorners() {
        return corners;
    }
}

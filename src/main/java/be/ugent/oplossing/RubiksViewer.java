package be.ugent.oplossing;

import be.ugent.oplossing.model.*;
import be.ugent.oplossing.show.RubiksReader;
import be.ugent.oplossing.show.Shape3DRectangle;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

// drag the mouse over the rectangle to rotate it.
public class RubiksViewer extends Application {
    private static final Color BACKGROUND_COLOR = Color.web("0xe5e5e5");
    private final Rotate rotateX = new Rotate(30, 0, 0, 0, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(20, 0, 0, 0, Rotate.Y_AXIS);

    private double anchorX, anchorY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        final Group root = new Group();
        final Scene scene = new Scene(root, 500, 500, true);
        scene.setFill(BACKGROUND_COLOR);

        addCamera(scene);
        addMouseHandlers(scene);

        List<IFace> faces = initRubikCube();
        List<Node> shapes = faces.stream().map(Shape3DRectangle::new).collect(Collectors.toList());
        Group meshGroup = new Group(shapes);

        meshGroup.getTransforms().addAll(rotateX, rotateY);
        root.getChildren().addAll(meshGroup, new AmbientLight(Color.WHITE));

        primaryStage.setTitle("Rubik's Cube Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<IFace> initRubikCube() throws FileNotFoundException {
        // Haal deze regels uit commentaar; dan zal het RubiksKubus-object gebruikt
        // worden
        // Maak je eigen implementatie van de rubiks interface.
        IRubikCube cube = new RubiksKubus3("infoKubus.csv");
        return cube.getAllFaces();

        // return RubiksReader.ReadFromFile("test.csv");
    }

    private void addCamera(Scene scene) {
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(true);
        perspectiveCamera.setNearClip(0.1);
        perspectiveCamera.setFarClip(10000.0);
        perspectiveCamera.setTranslateZ(-20);
        scene.setCamera(perspectiveCamera);
    }

    private void addMouseHandlers(Scene scene) {
        scene.setOnMousePressed(me -> {
            anchorX = me.getSceneX();
            anchorY = me.getSceneY();
        });

        scene.setOnMouseDragged(me -> {
            rotateX.setAngle(rotateX.getAngle() + (me.getSceneY() - anchorY) / 2);
            rotateY.setAngle(rotateY.getAngle() - (me.getSceneX() - anchorX) / 2);
            anchorX = me.getSceneX();
            anchorY = me.getSceneY();
        });
    }
}

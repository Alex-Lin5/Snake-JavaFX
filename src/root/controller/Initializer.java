package root.controller;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static root.Main.*;

public class Initializer {
    public final GraphicsContext myGraphicsContext;
    public final Canvas myCanvas;
    public final Scene myScene;
    public final Stage myStage;

    public Initializer(Stage myStage) {
        StackPane myStack = new StackPane();
        myCanvas = new Canvas(
                BOARD_WIDTH+PANEL_WIDTH, BOARD_HEIGHT+PANEL_HEIGHT);
        myCanvas.setFocusTraversable(true);
        myGraphicsContext = myCanvas.getGraphicsContext2D();
        myStack.getChildren().add(myCanvas);
        myScene = new Scene(myStack);
        this.myStage = myStage;
    }
    public void initialize() {
        myStage.setResizable((false));
        myStage.setTitle("snake");
        myStage.setOnCloseRequest(windowEvent -> System.exit(0));
        myStage.setScene(myScene);
        myStage.show();

        ThreadLoop loop = new ThreadLoop(myGraphicsContext);
        Keypad keypad1 = new Keypad(loop, myCanvas, myStage);
        keypad1.getKey();
        new Thread(loop).start();
    }
}

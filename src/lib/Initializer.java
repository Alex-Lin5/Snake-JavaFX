package lib;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import root.controller.ThreadLoop;

import static root.Main.*;

public final class Initializer {
    private final GraphicsContext myGraphicsContext;
    private final Canvas canvasFX;
    private final Scene myScene;
    private final Stage myStage;
    
    private final Pen pen;

    public Initializer(Stage myStage) {
        StackPane myStack = new StackPane();
        canvasFX = new Canvas(
                BOARD_WIDTH+PANEL_WIDTH, BOARD_HEIGHT+PANEL_HEIGHT);
        canvasFX.setFocusTraversable(true);
        myGraphicsContext = canvasFX.getGraphicsContext2D();
        myStack.getChildren().add(canvasFX);
        myScene = new Scene(myStack);
        this.myStage = myStage;
        pen = new Pen(myGraphicsContext);
    }
    public void initialize() {
        myStage.setResizable((false));
        myStage.setTitle("snake");
        myStage.setOnCloseRequest(windowEvent -> System.exit(0));
        myStage.setScene(myScene);
        myStage.show();

        ThreadLoop loop = new ThreadLoop(pen);
        Keypad keypad1 = new Keypad(loop, canvasFX, myStage);
        keypad1.getKey();
        new Thread(loop).start();
    }
}

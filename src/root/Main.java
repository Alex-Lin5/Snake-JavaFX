package root;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import root.controller.Keypad;
import root.controller.ThreadLoop;
import root.model.Board;

public class Main extends Application {

    private final int BOARD_WIDTH = 300;
    private final int BOARD_HEIGHT = 200;
    private final int PANEL_WIDTH = 0;
    private final int PANEL_HEIGHT = 20;

    private ThreadLoop loop;
//    private Point SpawnPoint;

    private GraphicsContext myGraphicsContext;
    private Board pad;

    public static void main(String[] args){
//        Application.launch(args);
        launch();
    }
    @Override
    public void start(Stage myStage) throws Exception{
        StackPane myStack = new StackPane();
        Canvas myCanvas = new Canvas(
            BOARD_WIDTH+PANEL_WIDTH, BOARD_HEIGHT+PANEL_HEIGHT);
        myCanvas.setFocusTraversable(true);
        myGraphicsContext = myCanvas.getGraphicsContext2D();

//        GameFrame.setup(myStage, BOARD_WIDTH, BOARD_HEIGHT);
        Board pad = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        Keypad.getKey(pad.getSnake(), myCanvas);
//        Painter.paint(pad, myGraphicsContext);
//        Messenger.Print(pad, myGraphicsContext);

        loop = new ThreadLoop(pad, myCanvas, myGraphicsContext);
        myStack.getChildren().add(myCanvas);
        Scene myScene = new Scene(myStack);

        myStage.setResizable((false));
        myStage.setTitle("snake");
        myStage.setOnCloseRequest(windowEvent -> System.exit(0));
        myStage.setScene(myScene);
        myStage.show();
        new Thread(loop).start();
//        (new Thread(loop)).start();
//        new Thread().start();
    }
}


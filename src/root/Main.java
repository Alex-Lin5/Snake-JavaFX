package root;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import root.controller.Keypad;
import root.controller.ThreadLoop;

public class Main extends Application {

    public static final int BOARD_WIDTH = 300;
    public static final int BOARD_HEIGHT = 200;
    public static final int PANEL_WIDTH = 0;
    public static final int PANEL_HEIGHT = 50;
    public boolean restart;

    private ThreadLoop loop;
//    private Point SpawnPoint;

    private GraphicsContext myGraphicsContext;
//    private Board board;

    public static void main(String[] args){
//        Application.launch(args);
        launch();
    }
    @Override
    public void start(Stage myStage) throws Exception{
        restart = false;
        StackPane myStack = new StackPane();
        Canvas myCanvas = new Canvas(
            BOARD_WIDTH+PANEL_WIDTH, BOARD_HEIGHT+PANEL_HEIGHT);
        myCanvas.setFocusTraversable(true);
        myGraphicsContext = myCanvas.getGraphicsContext2D();

        loop = new ThreadLoop(myGraphicsContext);
        Keypad keypad1 = new Keypad(loop, myCanvas);
        restart = keypad1.getKey();
        myStack.getChildren().add(myCanvas);
        Scene myScene = new Scene(myStack);

        myStage.setResizable((false));
        myStage.setTitle("snake");
        myStage.setOnCloseRequest(windowEvent -> System.exit(0));
        myStage.setScene(myScene);
        myStage.show();

//        Thread t =  new Thread(loop);
//        t.start();
//        t.join();
        new Thread(loop).start();
//        (new Thread(loop)).start();
//        new Thread().start(); // wrong
//        if (restart & loop.board.getSnake(0).isDead()) {
////            loop = new ThreadLoop(board, myGraphicsContext);
//            new Thread(loop).start();
//        }
    }
}


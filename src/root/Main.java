package root;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import root.controller.Keypad;
import root.controller.Painter;
import root.controller.ThreadLoop;
import root.model.Food;
import root.model.Point;
import root.model.Snake;
import root.view.Messager;
import root.view.PixelPad;

public class Main extends Application {

    private final int WIDTH = 300;
    private final int HEIGHT = 200;
    private ThreadLoop loop;
    private Point SpawnPoint;

    private GraphicsContext myGraphicsContext;

    public static void main(String[] args){
//        Application.launch(args);
        launch();
    }
    @Override
    public void start(Stage myStage) throws Exception{
        StackPane myStack = new StackPane();
        Canvas myCanvas = new Canvas(WIDTH, HEIGHT);
        myGraphicsContext = myCanvas.getGraphicsContext2D();
        myCanvas.setFocusTraversable(true);


//        GameFrame.setup(myStage, WIDTH, HEIGHT);
        SpawnPoint = new Point(30, 60);
        Snake snake1 = new Snake(SpawnPoint);
        Food theFood = new Food();
        PixelPad pad = new PixelPad(WIDTH, HEIGHT);

//        loop = new ThreadLoop();
//        loop.start();
        Keypad.getKey(snake1, myCanvas);
        Painter.paint(pad, myGraphicsContext);
        Messager.Print(myGraphicsContext);
        myStack.getChildren().add(myCanvas);
        Scene myScene = new Scene(myStack);

        myStage.setResizable((false));
        myStage.setTitle("snake");
        myStage.setOnCloseRequest(windowEvent -> System.exit(0));
        myStage.setScene(myScene);
        myStage.show();
        new Thread().start();
//        new Thread(loop).start();
    }
}


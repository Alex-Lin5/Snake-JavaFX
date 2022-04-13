package root;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import root.controller.Painter;
import root.controller.ThreadLoop;
import root.model.Food;
import root.model.Snake;
import root.view.PixelPad;

public class Main extends Application {

    private final int WIDTH = 300;
    private final int HEIGHT = 200;
    private ThreadLoop loop;

    private GraphicsContext myGraphicsContext;

    public static void main(String[] args){
//        Application.launch(args);
        launch();
    }
    @Override
    public void start(Stage myStage) throws Exception{
        StackPane myStack = new StackPane();
        Canvas myCanvas = new Canvas(WIDTH, HEIGHT);
        Scene myScene = new Scene(myStack);
        myGraphicsContext = myCanvas.getGraphicsContext2D();

        myCanvas.setFocusTraversable(true);
        myStage.setResizable((false));
        myStage.setTitle("snake");
        myStage.setOnCloseRequest(windowEvent -> System.exit(0));

//        GameFrame.setup(myStage, WIDTH, HEIGHT);

        Snake snake1 = new Snake();
        Food theFood = new Food();
        PixelPad pad = new PixelPad(WIDTH, HEIGHT);

        Painter.paint(pad, theFood, snake1, myGraphicsContext);
        myStack.getChildren().add(myCanvas);
        myStage.setScene(myScene);
        myStage.show();
        new Thread().start();
//        new Thread(loop).start();
//        (new Thread(loop)).start();
    }
}


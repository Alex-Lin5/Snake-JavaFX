package root.view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameFrame {
//    private final int WIDTH = 200;
//    private final int HEIGHT = 200;

//    private GraphicsContext myGraphicsContext;

    public void GameFrame() {
    }

    //    public Stage setup(Stage myStage) throws Exception{
    public static void setup(Stage myStage, Canvas myCanvas,
                             StackPane myStack, GraphicsContext gc) {
//        public static void setup(Stage myStage, final int wd, final int ht) {
//        final int WIDTH = 200;
//        final int HEIGHT = 200;
//        GraphicsContext gc;

//        StackPane myStack = new StackPane();
//        Canvas myCanvas = new Canvas(wd, ht);
        gc = myCanvas.getGraphicsContext2D();
        myCanvas.setFocusTraversable(true);
        // paint now
//        myStack.getChildren().add(myCanvas);
        Scene myScene = new Scene(myStack);

        myStage.setResizable((false));
        myStage.setTitle("snake");
        myStage.setOnCloseRequest(windowEvent -> System.exit(0));
        myStage.setScene(myScene);
//        myStage.show();

//        return myStage;
    }
}

package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import root.controller.ThreadLoop;

public class Messager {
    private static Color ScoreColor = Color.WHITE;
    private static Color OverColor = Color.IVORY;


    public static void Print(int width, int height, GraphicsContext gc, ThreadLoop thread){
        gc.setFill(ScoreColor);
        gc.fillText("Score: 0", 10, 10);

//        if (!thread.isOvered()) {
//            gc.setFill(OverColor);
//            gc.fillText("GAME OVER", width/2-35, height/2, 70);
//        }
    }
}

package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Messager {
    private static Color ScoreColor = Color.WHITE;
    private static Color OverColor = Color.IVORY;
    final int width;
    final int height;

    Messager(PixelPad pad){
        this.width = pad.getWidth();
        this.height = pad.getHeight();
    }


    public static void Print(GraphicsContext gc){
        gc.setFill(ScoreColor);
        gc.fillText("Score: 0", 10, 10);

//        if (!thread.isOvered()) {
//            gc.setFill(OverColor);
//            gc.fillText("GAME OVER", width/2-35, height/2, 70);
//        }
    }
}

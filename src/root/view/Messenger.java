package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import root.model.Board;

public class Messenger {
    private static Color ScoreColor = Color.BLACK;
    private static Color OverColor = Color.IVORY;
    private static Color BackgroundColor = Color.WHITE;
//    final int width;
//    final int height;
//    final int PanelWidth;
//    final int PanelHeight;

//    public Messenger(final int InfoWidth, final int InfoHeight, PixelPad pad){
//        this.width = pad.getWidth();
//        this.height = pad.getHeight();
//        PanelWidth = InfoWidth;
//        PanelHeight = InfoHeight;
//
//    }


    public static void Print(int FPS, Board pad, GraphicsContext gc){
        int width = pad.getWidth();
        int height = pad.getHeight();
        gc.setFill(BackgroundColor);
        gc.fillRect(0 , height, width, 30);
        gc.setFill(ScoreColor);
        gc.fillText("Score: " + pad.getSnake().getScore(), 5, height+15);
//        gc.fillText("FPS: " + FPS,5, height+30);
//        if (!thread.isOvered()) {
//            gc.setFill(OverColor);
//            gc.fillText("GAME OVER", width/2-35, height/2, 70);
//        }
    }
}

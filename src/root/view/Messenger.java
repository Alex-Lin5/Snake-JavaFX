package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import root.model.Board;

public class Messenger {
    private static Color ScoreColor = Color.BLACK;
    private static Color DeadColor = Color.RED;
    private static Color OverColor = Color.IVORY;
    private static Color BackgroundColor = Color.WHITE;

    private final GraphicsContext gc;
    private final Board board;

    final int width;
    final int height;
    final int PanelWidth;
    final int PanelHeight;

    public Messenger(final int InfoWidth, final int InfoHeight,
                     Board board, GraphicsContext gc){
        this.width = board.getWidth();
        this.height = board.getHeight();
        this.board = board;
        this.gc = gc;
        PanelWidth = InfoWidth;
        PanelHeight = InfoHeight;

    }


    public void Print(int FPS){
//        public static void Print(int FPS, Board board, GraphicsContext gc){
        int width = board.getWidth();
        int height = board.getHeight();
        gc.setFill(BackgroundColor);
        gc.fillRect(0 , height, width, 30);
        gc.setFill(ScoreColor);
        gc.fillText("Score: " + board.getSnake().getScore(), 5, height+15);

        if (board.getSnake().isDead()) {
            gc.setFill(DeadColor);
            gc.fillText("snake is dead", 5, height+30);
            gc.setFill(OverColor);
            gc.fillText("GAME OVER", width/2-35, height/2, 70);
        }
        //        gc.fillText("FPS: " + FPS,5, height+30);
//        if (!thread.isOvered()) {
//        }
    }
}

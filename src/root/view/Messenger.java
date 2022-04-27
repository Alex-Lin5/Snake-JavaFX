package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import root.controller.Debugger;
import root.controller.Recorder;
import root.model.Board;

public class Messenger {
    private static Color InfoColor = Color.BLACK;
    private static Color DeadColor = Color.RED;
    private static Color OverColor = Color.IVORY;
    private static Color BackgroundColor = Color.WHITE;

    private final GraphicsContext gc;
    private final Board board;
    private final Recorder recorder;
    private final Debugger debugger;

    final int width;
    final int height;
    final int PanelWidth;
    final int PanelHeight;

    public Messenger(final int InfoWidth, final int InfoHeight,
                     Board board, Recorder recorder, Debugger debugger, GraphicsContext gc){
        this.width = board.getWidth();
        this.height = board.getHeight();
        this.board = board;
        this.gc = gc;
        this.recorder = recorder;
        this.debugger = debugger;
        PanelWidth = InfoWidth;
        PanelHeight = InfoHeight;

    }


    public void Print(int FPS){
//        int width = board.getWidth();
//        int height = board.getHeight();
        int lineSpace = 15;
        int columnSpace = 70;
        gc.setFill(BackgroundColor);
        gc.fillRect(0 , height, width, PanelHeight);
        gc.setFill(InfoColor);
        // Line 1
        gc.fillText("Score: " + board.getSnake(0).getScore(), 5, height+lineSpace);
        if (debugger.on)
            gc.fillText("Debugger on", width/2, height+lineSpace*3);


        if (board.getSnake(0).isDead()) {
            // Line 2
            gc.setFill(DeadColor);
            gc.fillText("snake is dead", 5, height+lineSpace*2);
            gc.setFill(OverColor);
            gc.fillText("GAME OVER", width/2-35, height/2, 70);

            int life = recorder.getSteps();
            System.out.printf("Game over on %d steps. Timetick: %d.\n",
                life, recorder.getTimeTick());
            recorder.PrintValue(0);
//            recorder.PrintValue(life);
        }
        else if (debugger.on)
            gc.fillText("Debugger on", width/2, height+lineSpace*3);
        else if (!board.getSnake(0).isMoving())
            // Line 2
            gc.fillText("snake "+board.getSnake(0).getSerialNum()+" is resting.", 5, height+lineSpace*2);
        //        gc.fillText("FPS: " + FPS,5, height+30);
    }
}

package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import root.controller.Debugger;
import root.controller.Recorder;
import root.model.Board;

import static root.model.Board.SIZE;
import static root.view.Painter.SnakeColor;

public class Messenger {
    private static Color InfoColor = Color.BLACK;
    private static Color DeadColor = Color.RED;
    private static Color OverColor = Color.IVORY;
    private static Color BackgroundColor = Color.WHITE;

    private final GraphicsContext gc;
    private final Board board;
    private final Recorder recorder;
    private final Debugger debugger;

    private final int width;
    private final int height;
    private final int PanelWidth;
    private final int PanelHeight;
    private boolean processed;

    public Messenger(final int InfoWidth, final int InfoHeight,
                     Board board, Recorder recorder, Debugger debugger, GraphicsContext gc) {
        this.width = board.getWidth();
        this.height = board.getHeight();
        this.board = board;
        this.gc = gc;
        this.recorder = recorder;
        this.debugger = debugger;
        PanelWidth = InfoWidth;
        PanelHeight = InfoHeight;

    }


    public void Print() {
        int lineSpace = 15;
        int columnSpace = 70;
        gc.setFill(BackgroundColor);
        gc.fillRect(0, height, width, PanelHeight);

        for (int line = 1; line <= 4; line++) {
            if (line == 1) {
                gc.setFill(SnakeColor);
                gc.fillRect(5, height + lineSpace*line - SIZE, SIZE, SIZE);
                gc.setFill(InfoColor);
                gc.setTextAlign(TextAlignment.LEFT);
                gc.fillText(board.getSnake(0).getName(), 10 + SIZE, height + lineSpace*line);

            }
            else if (line == 2) {
                gc.fillText("Score: " + board.getSnake(0).getScore(), 5, height + lineSpace*line);
            }
            else if (line == 3) {
                if (board.getSnake(0).isDead()) {
                    gc.setFill(DeadColor);
                    gc.fillText("Dead.", 5, height + lineSpace * line);
                } else if (!board.getSnake(0).isMoving() & !debugger.isOn())
                    gc.fillText("Resting.", 5, height + lineSpace * line);
//                gc.fillText("snake " + board.getSnake(0).getSerialNum() + " is resting.", 5, height + lineSpace * line);
                //        gc.fillText("FPS: " + FPS,5, height+30);
            }
            else if (line == 4) {
                if (debugger.isOn()) {
                    gc.setFill(InfoColor);
//                    gc.setTextAlign(TextAlignment.RIGHT);
//                    gc.fillText("Debugger on", width, height + lineSpace * line);
                    gc.fillText("Debugger on", 5, height + lineSpace * line);
                }

            }
        }

        if (board.getSnake(0).isDead()) {
//            if (!processed & board.getSnake(0).isDead()) {
            gc.setFill(OverColor);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("GAME OVER", width / 2, height / 2, 70);
            if (!processed) {
                int life = recorder.getSteps();
                System.out.printf("Game over on %d steps. TimeTick: %d.\n",
                        life, recorder.getTimeTick());
                recorder.printDeath(0);
                processed = true;
            }
        }

    }
}

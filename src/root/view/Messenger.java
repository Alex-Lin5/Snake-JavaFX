package root.view;

import root.controller.Debugger;
import root.controller.Recorder;
import root.model.Board;
import root.model.Point.RectPoint;
import root.model.Snake;

import static root.Main.PANEL_HEIGHT;
import static root.Main.PANEL_WIDTH;
import static root.model.Board.SIZE;
import static root.view.Painter.SnakeColor;

public final class Messenger {
    private static Color InfoColor = Color.BLACK;
    private static Color DeadColor = Color.RED;
    private static Color OverColor = Color.WHITE;
    private static Color BackgroundColor = Color.WHITE;

    private final Pen pen;
    private final Board board;
    private final Recorder recorder;
    private final Debugger debugger;

    private final int width;
    private final int height;
    private final int PanelWidth;
    private final int PanelHeight;
    private int step;
    private boolean processed;

    public Messenger(Board board, Recorder recorder, Debugger debugger, Pen pen) {
        this.width = board.getWidth();
        this.height = board.getHeight();
        this.board = board;
        this.pen = pen;
        this.recorder = recorder;
        this.debugger = debugger;
        PanelWidth = PANEL_WIDTH;
        PanelHeight = PANEL_HEIGHT;
        step = 0;

    }


    public void Print() {
        int lineSpace = 15;
        int columnSpace = 70;
        pen.setColor(BackgroundColor);
        pen.drawRect(new RectPoint(0, height),
                new RectPoint(width, PanelHeight));

        Snake snake = board.getSnake(0);
        for (int line = 1; line <= 4; line++) {
            if (line == 1) {
                pen.setColor(SnakeColor);
                pen.drawSquare(new RectPoint(5, height + lineSpace*line - SIZE), SIZE);
                pen.setColor(InfoColor);
                pen.setAlign(Pen.Align.LEFT);
                pen.textCanvas(snake.getName(),
                    new RectPoint(10 + SIZE, height + lineSpace*line));
            }
            else if (line == 2) {
                pen.textCanvas("Score: " + snake.getScore(),
                    new RectPoint(5, height + lineSpace*line));
            }
            else if (line == 3) {
                if (snake.isDead()) {
                    pen.setColor(DeadColor);
                    pen.textCanvas("Dead.",
                        new RectPoint(5, height + lineSpace * line));
                } else if (!snake.isMoving() & !debugger.isOn())
                    pen.textCanvas("Resting.",
                        new RectPoint(5, height + lineSpace * line));
                else if (snake.isMoving())
                    pen.textCanvas("Moving",
                        new RectPoint(5, height + lineSpace * line));
//                if (snake.isGrowing() ) {
//                    step = recorder.getSteps();
//                    processed = true;
//                }
//                else if (processed & step+3 > recorder.getSteps())
//                    pen.textCanvas("Growing",
//                        new RectPoint(5, height + lineSpace * line));
//                else processed = false;


//                if (snake.isMolting())
//                    pen.textCanvas("Molting",
//                            new RectPoint(5, height + lineSpace * line));

            }
            else if (line == 4) {
                if (debugger.isOn()) {
                    pen.setColor(InfoColor);
                    pen.textCanvas("Debugger on",
                        new RectPoint(5, height + lineSpace * line));
                }
            }
        }

        if (snake.isDead()) {
            pen.setColor(OverColor);
            pen.setAlign(Pen.Align.CENTER);
            pen.textCanvas("GAME OVER",
                new RectPoint(width / 2, height / 2));
            if (!processed) {
                int life = recorder.getSteps();
                System.out.printf("Game over on %d steps. TimeTick: %d.\n",
                        life, recorder.getTimeTick());
                recorder.printDeath((byte) 0);
                processed = true;
            }
//            processed = false;
        }
        else processed = false;

    }
}

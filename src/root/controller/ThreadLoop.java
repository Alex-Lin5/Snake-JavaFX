package root.controller;

import javafx.scene.canvas.GraphicsContext;
import root.model.Board;
import root.view.Messenger;
import root.view.Painter;

import static root.Main.*;
import static root.model.Board.SIZE;

public class ThreadLoop implements Runnable {
    public boolean running;
    private boolean restart;
//    private boolean stopped;

    private float interval;
//    private static int FPS;

    private final GraphicsContext gc;
    public final Board board;
    private final Painter painter;
    private final Messenger messenger;
    private final Recorder recorder;

    public ThreadLoop(GraphicsContext gc) {
//        this.restart = restart;
        this.gc = gc;
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        float speed = board.getSnake().getSpeed();
        float frames = SIZE*speed;
        interval = 1000f/frames;
        running = true;
        painter = new Painter(board, gc);
        messenger = new Messenger(PANEL_WIDTH, PANEL_HEIGHT, board, gc);
        recorder = new Recorder(board);
    }

    @Override
    public void run() {
        while (true) {
            if (running) {
                float time = System.currentTimeMillis();
//                float Delay = System.currentTimeMillis() - time;
                int FPS = (int) (1000/interval);

                board.update();
////                Painter.clear(board, gc);
                painter.Paint();
                messenger.Print(FPS);
                recorder.record();
                if (board.getSnake().isDead()) {
                    int life;
                    running = false;
                    life = recorder.getSteps();
                    System.out.printf("Game over on %d steps.\n", life);
//                    recorder.getData(life);
                    recorder.PrintValue(life);
                    break;
                }

                time = System.currentTimeMillis() - time;
                if (time < interval) {
                    try {
                        Thread.sleep((long) (interval - time));
                    } catch (InterruptedException ignore) {
                    }
                }
                else {
                    try {
//                        Thread.sleep((long) (interval));
                        Thread.sleep((long) (1));
                    } catch (InterruptedException ignore) {
                    }
                }
            }
            else {
                try {
                    Thread.sleep((long) (interval));
//                    if (restart)

                } catch (InterruptedException ignore) {
                }
//                continue;
            }
        }
    }

    public boolean isRunning() { return running;}
    public void Pause() { running = false;}
    public void Resume() { running = true;}

//    public boolean isOvered() { return stopped;}
//    public void setOvered() { stopped = true;}
//    public void setContinued() { stopped = false;}

//    public static int getFPS() {return FPS;}
}

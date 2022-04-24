package root.controller;

import javafx.scene.canvas.GraphicsContext;
import root.model.Board;
import root.view.Messenger;
import root.view.Painter;

import static root.Main.*;
import static root.model.Board.SIZE;

public class ThreadLoop implements Runnable {
    public boolean running;
//    private boolean restart;
//    private boolean stopped;

    private float interval;
    private float timeInitial, timeLast;
//    private static int FPS;

//    private final GraphicsContext gc;
    public final Board board;
    private final Painter painter;
    private final Messenger messenger;
    private final Recorder recorder;
    public final Debugger debugger;

    public ThreadLoop(GraphicsContext gc) {
//        this.restart = restart;
        timeInitial = System.currentTimeMillis();
        timeLast = 0;
//        this.gc = gc;
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        float speed = board.getSnake(0).getSpeed();
        float frames = SIZE*speed;
        interval = 1000f/frames;
        running = true;
        recorder = new Recorder(board);
        painter = new Painter(board, gc);
        debugger = new Debugger(recorder, board);
        messenger = new Messenger(PANEL_WIDTH, PANEL_HEIGHT, board, recorder, debugger, gc);
    }

    @Override
    public void run() {
        while (true) {
            if (running) {
                float delay = System.currentTimeMillis();
                int FPS = (int) (1000/interval);

                if (debugger.on)
                    debugger.debug();
//                    ;
                else {
                    if (debugger.tuned) {
                        debugger.reset();
                        debugger.tuned = false;
                    }
                    board.update();
                    recorder.record();
                    if (board.getSnake(0).isDead()) {
                        running = false;
                        System.out.printf("Game lasts %f.\n", timeLast);
//                        break;
                    }
                }
//                Painter.clear(board, gc);
                painter.Paint();
                messenger.Print(FPS);

                delay = System.currentTimeMillis() - delay;
                timeLast = System.currentTimeMillis() - timeInitial;
//                if (delay < interval) {
//                    try {
//                        Thread.sleep((long) (interval - delay));
//                    } catch (InterruptedException ignore) {
//                    }
//                }
//                else {
//                    try {
//                        Thread.sleep((long) (interval));
////                        Thread.sleep((long) (1));
//                    } catch (InterruptedException ignore) {
//                    }
//                }
                try {
                    Thread.sleep((long) (interval));
//                    LinkedList<Integer> temp = new LinkedList<>();
                } catch (InterruptedException ignore) {
                }

            }
            else { // thread is not running
                try {
                    Thread.sleep((long) (interval));
//                    if (restart)

                } catch (InterruptedException ignore) {
                }
                continue;
            }
        }
    }

//    public Recorder getRecorder() { return recorder;}
//    public boolean isRunning() { return running;}
//    public void Pause() { running = false;}
//    public void Resume() { running = true;}

//    public boolean isOvered() { return stopped;}
//    public void setOvered() { stopped = true;}
//    public void setContinued() { stopped = false;}

//    public static int getFPS() {return FPS;}
}

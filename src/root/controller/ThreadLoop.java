package root.controller;

import javafx.scene.canvas.GraphicsContext;
import root.model.Board;
import root.view.Messenger;
import root.view.Painter;

import static root.Main.*;
import static root.model.Board.SIZE;

public class ThreadLoop implements Runnable {
    public boolean running;
    private float interval;
    private float timeInitial, timeLast;

    public final Board board;
    private final Painter painter;
    private final Messenger messenger;
    private final Recorder recorder;
    public final Debugger debugger;

    public ThreadLoop(GraphicsContext gc) {
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        recorder = new Recorder(board);
        painter = new Painter(board, gc);
        debugger = new Debugger(recorder, board);
        messenger = new Messenger(PANEL_WIDTH, PANEL_HEIGHT, board, recorder, debugger, gc);

        running = true;
        timeInitial = System.currentTimeMillis();
        timeLast = 0;
        float speed = board.getSnake(0).getSpeed();
        float frames = SIZE*speed;
        interval = 1000f/frames;
    }

    @Override
    public void run() {
        while (true) {
            if (running) {
                float delay = System.currentTimeMillis();
                int FPS = (int) (1000/interval);

                if (debugger.isOn())
                    debugger.debug();
                else {
                    if (debugger.isTuned()) {
                        debugger.reset();
                    }
                    board.update();
                    recorder.record();
                    if (board.getSnake(0).isDead()) {
                        running = false;
                        System.out.printf("Game lasts %f.\n", timeLast);
                    }
                }
//                Painter.clear(board, gc);
                painter.Paint();
                messenger.Print(FPS);

                delay = System.currentTimeMillis() - delay;
                timeLast = System.currentTimeMillis() - timeInitial;
                if (delay < interval) {
                    try {
                        Thread.sleep((long) (interval - delay));
                    } catch (InterruptedException ignore) {
                    }
                }
                else {
                    try {
                        Thread.sleep((long) (interval));
//                        Thread.sleep((long) (1));
                    } catch (InterruptedException ignore) {
                    }
                }

//                try {
//                    Thread.sleep((long) (interval));
////                    LinkedList<Integer> temp = new LinkedList<>();
//                } catch (InterruptedException ignore) {
//                }

            }
            else { // thread is not running
                try {
                    Thread.sleep((long) (interval));
                } catch (InterruptedException ignore) {
                }
                continue;
            }
        }
    }

//    public Recorder getRecorder() { return recorder;}
}

package root.controller;

import javafx.scene.canvas.GraphicsContext;
import root.model.Board;
import root.view.Messenger;
import root.view.Painter;

import static root.Main.*;
import static root.model.Board.SIZE;

public final class ThreadLoop implements Runnable {
    public boolean running;
    private float interval;
    private float timeInitial, timeLast;

    public final Board board;
    private final Arbiter arbiter;
    private final Painter painter;
    private final Messenger messenger;
    private final Recorder recorder;
    public final Debugger debugger;

    public ThreadLoop(GraphicsContext gc) {
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        arbiter = new Arbiter(board, 1, false);
        recorder = new Recorder(board, arbiter);
        debugger = new Debugger(board, recorder);
        painter = new Painter(board, gc);
        messenger = new Messenger(PANEL_WIDTH, PANEL_HEIGHT, board, recorder, debugger, gc);

        running = true;
        timeInitial = System.currentTimeMillis();
        timeLast = 0;
        float speed = board.getSnake((byte) 0).getSpeed();
        float frames = SIZE*speed;
        interval = 1000f/frames;
    }

    @Override
    public void run() {
        while (true) {
            if (running) {
                float delay = System.currentTimeMillis();
                int FPS = (int) (1000/interval);

                if (board.getSnake((byte) 0).isDead());
                else if (!debugger.isOn()){
                    arbiter.update();
                    recorder.record();
                }
                if (debugger.isOn())
                    debugger.debug();
                else if (debugger.isTuned())
                        debugger.reset();

//                Painter.clear();
                painter.Paint();
                messenger.Print();

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
                    } catch (InterruptedException ignore) {
                    }
                }
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
}

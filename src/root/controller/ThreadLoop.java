package root.controller;

import root.model.Board;
import root.view.Messenger;
import root.view.Painter;
import root.view.Pen;

import static root.model.Board.SIZE;

public final class ThreadLoop implements Runnable {
    public boolean running;
    private float interval;
    private float timeInitial, timeLast;

    private final Board board;
    private final Engine engine;
    private final Painter painter;
    private final Messenger messenger;
    private final Recorder recorder;
    private final Debugger debugger;

    public ThreadLoop(Pen pen) {
        board = new Board();
        engine = new Engine(board, 1, false);
        recorder = new Recorder(board, engine);
        debugger = new Debugger(board, recorder);

        painter = new Painter(board, pen);
        messenger = new Messenger(board, recorder, debugger, pen);

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
                    engine.work();
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
    public Debugger getDebugger() { return debugger;}
    public Board getBoard() { return board;}
}

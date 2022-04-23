package root.controller;

import javafx.scene.canvas.GraphicsContext;
import root.model.Board;
import root.view.Messenger;
import root.view.Painter;

import static root.model.Board.SIZE;

public class ThreadLoop implements Runnable {
    public boolean running;
    private boolean stopped;

    private float interval;
//    private static int FPS;

    private final GraphicsContext gc;
    private final Board pad;
//    private final Canvas theCanvas;

//    public ThreadLoop(final Board pad, final Canvas theCanvas, final GraphicsContext gc) {
    public ThreadLoop(final Board pad, final GraphicsContext gc) {
//        FPS = snake1.getSpeed();
//        float FPS = 60.0f;
//        interval = (float) (1000 / FPS);
        this.gc = gc;
        this.pad = pad;
//        this.theCanvas = theCanvas;
        float speed = pad.getSnake().getSpeed();
        float frames = SIZE*speed;
        interval = 1000f/frames;
        running = true;
        stopped = false;
    }

    @Override
    public void run() {
        while (true) {
            if (running) {

                float time = System.currentTimeMillis();
//                float Delay = System.currentTimeMillis() - time;
                int FPS = (int) (1000/interval);

                pad.update();
//                Painter.clear(pad, gc);
                Painter.paint(pad, gc);
                Messenger.Print(FPS, pad, gc);

                time = System.currentTimeMillis() - time;
                int temp = 1;
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
                } catch (InterruptedException ignore) {
                }
//                continue;
            }
        }
    }

    public boolean isRunning() { return running;}
    public void Pause() { running = false;}
    public void Resume() { running = true;}

    public boolean isOvered() { return stopped;}
    public void setOvered() { stopped = true;}
    public void setContinued() { stopped = false;}

//    public static int getFPS() {return FPS;}
}

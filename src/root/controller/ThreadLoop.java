package root.controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import root.model.Board;
import root.view.Messenger;
import root.view.Painter;

public class ThreadLoop implements Runnable {
    public boolean running;
    private boolean stopped;

    private float interval;

    private final GraphicsContext gc;
    private final Board pad;
    private final Canvas theCanvas;

    public ThreadLoop(final Board pad, final Canvas theCanvas, final GraphicsContext gc) {
//        Snake snake1 = new Snake();
//        FPS = snake1.getSpeed();
        float FPS = 60.0f;
        interval = (float) (1000 / FPS);
        running = true;
        stopped = false;
        this.gc = gc;
        this.pad = pad;
        this.theCanvas = theCanvas;
    }

    @Override
    public void run() {
        while (true) {
            if (running) {

                float time = System.currentTimeMillis();
//                float Delay = System.currentTimeMillis() - time;


                pad.update();
                Painter.clear(theCanvas, gc);
                Messenger.Print(pad, gc);
                Painter.paint(pad, gc);

                time = System.currentTimeMillis() - time;


                if (time < interval) {
                    try {
                        Thread.sleep((long) (interval - time));
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
}

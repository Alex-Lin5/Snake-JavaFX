package root.controller;

import javafx.scene.canvas.GraphicsContext;
import root.view.Messager;
import root.model.PixelPad;
import root.view.Painter;

public class ThreadLoop implements Runnable {
    public boolean running;
    private boolean stopped;

    private float interval;

    private final GraphicsContext gc;
    private final PixelPad pad;

    public ThreadLoop(final PixelPad pad, final GraphicsContext gc) {
//        Snake snake1 = new Snake();
//        SnakeSpeed = snake1.getSpeed();
        float snakeSpeed = 20.0f;
        interval = (float) (1000 / snakeSpeed);
        running = true;
        stopped = false;
        this.gc = gc;
        this.pad = pad;
    }

    @Override
    public void run() {
        while (true) {
            if (running) {

                float time = System.currentTimeMillis();
//                float Delay = System.currentTimeMillis() - time;


                time = System.currentTimeMillis() - time;
                Painter.paint(pad, gc);
                Messager.Print(gc);


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
                continue;
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

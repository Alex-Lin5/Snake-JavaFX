package root.controller;

import root.model.Snake;
public class ThreadLoop implements Runnable {
    private boolean running;
    private boolean stopped;

    private float interval;
    private float SnakeSpeed = 20.0f;

    public ThreadLoop() {
        Snake snake1 = new Snake();
        SnakeSpeed = snake1.getSpeed();
        interval = (float) (1000 / SnakeSpeed);
    }

    @Override
    public void run() {
        while (true) {
            if (running) ;
            else {
                try {
                    Thread.sleep((long) (interval));
                } catch (InterruptedException ignore) {
                }
                continue;
            }
        }
    }
}

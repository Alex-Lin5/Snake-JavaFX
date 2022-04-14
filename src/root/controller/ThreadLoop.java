package root.controller;

public class ThreadLoop implements Runnable {
    public boolean running;
    private boolean stopped;

    private float interval;
    private float SnakeSpeed = 20.0f;

    public ThreadLoop() {
//        Snake snake1 = new Snake();
//        SnakeSpeed = snake1.getSpeed();
        interval = (float) (1000 / SnakeSpeed);
        running = true;
        stopped = false;
    }

    @Override
    public void run() {
        while (true) {
            if (running) {

                float time = System.currentTimeMillis();
//                float Delay = System.currentTimeMillis() - time;
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

package root.controller;

import root.model.Board;
import root.model.Trail;
import root.model.Snake;

import java.util.HashMap;

public final class Recorder {
    private int step;
    private int timeTick;
    private final Board board;
    private Snake snake;
    private Trail value;
    private HashMap<Integer, Trail> recording;
    public Recorder(Board board) {
        this.board = board;
        this.recording = new HashMap<>();
        snake = board.getSnake(0);
        step = 0;
        timeTick = 0;
        
        value = new Trail(snake.getHead(),
            board.getFood().getPoint(), snake.getLength());
        recording = new HashMap<>();
        recording.put(snake.getSerialNum(), value);

    }

    public void record() {
        timeTick += 1;
        if (board.isValidUpdate()) {
            step += 1;
            value.setHead(step, snake.getBody().get(0));
            if (snake.isGrowing()) {
                value.setFood(step, board.getFood().getPoint());
                value.setLength(step, snake.getLength());
                value.setScore(step, snake.getScore());
            }
            if (snake.isDead())
                value.setLifespan(step);

//            if (snake.isSpawned())
//                if (snake.isDead())
//                    value.dead = true;
            
        }
    }
    public void removeData(int serialNum, Integer step) {
        Trail data = getData(serialNum);
        data.removeFrom(step);
    }
    public Trail getData(Integer serialNum) {
        return recording.get(serialNum);
    }
    public void printDeath(Integer serialNum) {
        Trail data = recording.get(serialNum);
        int life = data.getLifespan();
        System.out.printf(
                "Score: %d, length: %d, head hits on (%d, %d).\n" +
                        "Seed is %d\n",
                data.getScore(life), data.getLength(life),
                (int) data.getHead(life).getX(), (int) data.getHead(life).getY(),
                board.getSeed());
    }
    public void printValue(Integer serialNum, Integer step, Debugger.status theStatus) {
        Trail data = recording.get(serialNum);
        String sign;
        if (theStatus == Debugger.status.BACK)
            sign = "--";
        else if (theStatus == Debugger.status.FORWARD)
            sign = "++";
        else
            sign = "==";
        System.out.printf(
                "%s Step: %d, Score: %d, length: %d, head on (%d, %d).\n",
                sign, step, data.getScore(step), data.getLength(step),
                (int) data.getHead(step).getX(), (int) data.getHead(step).getY());
    }
    public void setSteps(int step) { this.step = step;}
    public int getTimeTick() { return timeTick;}
    public int getSteps() { return step;}
}

package root.controller;

import root.model.Board;
import root.model.Trail;
import root.model.Snake;

import java.util.HashMap;

public final class Recorder {
    private int step;
    private int timeTick;
    private final Board board;
    private final Engine engine;
    private Snake snake;
    private Trail value;
    private HashMap<Byte, Trail> recording;
    public Recorder(Board board, Engine engine) {
        this.board = board;
        this.engine = engine;
        this.recording = new HashMap<>();
        snake = board.getSnake(0);
        step = 0;
        timeTick = 0;
        
        value = new Trail(snake.getHead(),
            board.getFoodList(), snake.getLength());
        recording = new HashMap<>();
        recording.put(snake.getSerialNum(), value);

    }

    public void record() {
        timeTick += 1;
        if (engine.isValid()) {
            step += 1;
            value.setHead(step, snake.getBody().get(0));
            if (snake.isGrowing()) {
//                value.setFood(step, board.getFood().getPoint());
                value.setFood(step, board.getFoodList());
                value.setLength(step, snake.getLength());
                value.setScore(step, snake.getScore());
            }
            value.setLifespan(step);

//            if (snake.isSpawned())
//                if (snake.isDead())
//                    value.dead = true;
            
        }
    }
    public Trail getData(Number serialNum) {
        return recording.get(serialNum.byteValue());
    }
    public void removeData(Number serialNum, Integer step) {
        Trail data = getData(serialNum);
        data.removeFrom(step);
    }
    public void printDeath(Number serialNum) {
        Trail data = getData(serialNum);
        int life = data.getLifespan();
        System.out.printf(
            "Score: %d, length: %d, head hits on (%d, %d).\n" +
                    "Seed is %d\n",
            data.getScore(life), data.getLength(life),
            data.getHead(life).getX(), data.getHead(life).getY(),
            engine.getSeed());
    }
    public void printValue(Number serialNum, Integer step, Debugger.status theStatus) {
        Trail data = getData(serialNum);
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
            data.getHead(step).getX(), data.getHead(step).getY());
    }
    public void setSteps(int step) { this.step = step;}
    public int getTimeTick() { return timeTick;}
    public int getSteps() { return step;}
}

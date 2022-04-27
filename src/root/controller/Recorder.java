package root.controller;

import root.model.Board;
import root.model.Trail;
import root.model.Snake;

import java.util.HashMap;

public class Recorder {
    private int step;
    private int timeTick;
    private final Board board;
    private Snake snake;
//    private T value;
    private Trail value;
//    private Map<Integer, DataPacket> recording;
    private HashMap<Integer, Trail> recording;
    public Recorder(Board board) {
        this.board = board;
        this.recording = new HashMap<>();
        snake = board.getSnake(0);
        step = 0;
        timeTick = 0;
        
        value = new Trail(snake.getHead(),
            board.getFood().getFoodPoint(), snake.getLength());
        recording = new HashMap<>();
        recording.put(snake.getSerialNum(), value);

    }

    public void record() {
        timeTick += 1;
        if (board.isValidUpdate()) {
            step += 1;
            value.setHead(step, snake.getBody().get(0));
            if (snake.isGrowing()) {
                value.setFood(step, board.getFood().getFoodPoint());
                value.setLength(step, snake.getLength());
                value.setScore(step, snake.getScore());
            }
            if (snake.isDead())
                value.setLifespan(step);
//            value = new DataPacket();
//            value.snakeDirection = snake.getDirection();
//            value.head = snake.getBody().get(0);
//            value.food = board.getFood().getFoodPoint();
//            value.length = snake.getLength();
//            value.score = snake.getScore();

//            if (snake.isSpawned())
//                if (snake.isDead())
//                    value.dead = true;

//            recording.put(step, value);

        }
    }
    public void removeData(int serialnum, Integer step) {
        Trail data = getData(serialnum);
        data.removeFrom(step);
    }
    public Trail getData(Integer serialnum) {
        return recording.get(serialnum);
    }
    public void PrintValue(Integer serialnum) {
        Trail data = recording.get(serialnum);
        int life = data.getLifespan();
        System.out.printf(
            "Score: %d, length: %d, head hits on (%d, %d).\n",
                data.getScore(life), data.getLength(life),
                (int) data.getHead(life).getX(), (int) data.getHead(life).getY());
    }
    public void setSteps(int step) { this.step = step;}
    public int getTimeTick() { return timeTick;}
    public int getSteps() { return step;}
}

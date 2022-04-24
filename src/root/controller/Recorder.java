package root.controller;

import root.model.Board;
import root.model.DataPacket;
import root.model.Snake;

import java.util.HashMap;

public class Recorder {
    private int step;
    private int timeTick;
    private final Board board;
    private Snake snake;
//    private T value;
    private DataPacket value;
//    private Map<Integer, DataPacket> recording;
    private HashMap<Integer, DataPacket> recording;
    public Recorder(Board board) {
        this.board = board;
        this.recording = new HashMap<>();
        snake = board.getSnake(0);
//        this.list = new LinkedList<Point>();
        step = 0;
        timeTick = 0;
        
        value = new DataPacket();
        value.snakeDirection = snake.getDirection();
        value.head = snake.getBody().get(0);
        value.food = board.getFood().getFoodPoint();
        value.length = snake.getLength();
        value.score = snake.getScore();
        recording.put(step, value);

    }

    public void record() {
        timeTick += 1;
        if (board.isValidUpdate()) {
            step += 1;
            value = new DataPacket();
            value.snakeDirection = snake.getDirection();
            value.head = snake.getBody().get(0);
            value.food = board.getFood().getFoodPoint();
            value.length = snake.getLength();
            value.score = snake.getScore();
            if (snake.isSpawned())
                if (snake.isDead())
                    value.dead = true;

            recording.put(step, value);

        }
//        list = board.getSnake(0).getBody();
    }
    public void removeData(Integer index) { recording.remove(index);}
    public DataPacket getData(Integer index) {
        return recording.get(index);
    }
    public void PrintValue(Integer index) {
        DataPacket data = getData(index);
        System.out.printf(
            "Score: %d, length: %d, head hits on (%d, %d).\n",
                data.score, data.length, (int) data.head.getX(), (int) data.head.getY());
    }
    public void setSteps(int step) { this.step = step;}
    public int getTimeTick() { return timeTick;}
    public int getSteps() { return step;}
}

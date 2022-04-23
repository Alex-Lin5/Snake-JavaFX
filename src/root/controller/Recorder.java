package root.controller;

import root.model.Board;
import root.model.DataPacket;
import root.model.Snake;

import java.util.HashMap;
import java.util.Map;

public class Recorder {
    private int steps;
    private int timeTick;
    private final Board board;
    private Snake snake;
//    private T value;
    private DataPacket value;
    private Map<Integer, DataPacket> recording;
//    private LinkedList<Point> list;
    public Recorder(Board board) {
        this.board = board;
        this.recording = new HashMap<>();
        snake = board.getSnake();
//        this.list = new LinkedList<Point>();
        steps = 0;
        timeTick = 0;
    }

    public void record() {
        timeTick += 1;
        if (board.isValidUpdate()) {
            steps += 1;
            value = new DataPacket();
            value.snakeDirection = snake.getDirection();
            value.head = snake.getBody().get(0);
            value.food = board.getFood().getFoodPoint();
            value.length = snake.getLength();
            value.score = snake.getScore();
            if (snake.isSpawned())
                if (snake.isDead())
                    value.dead = true;

            recording.put(steps, value);

        }
//        list = board.getSnake().getBody();
    }
    public DataPacket getData(Integer index) {
        return recording.get(index);
    }
    public void PrintValue(Integer index) {
        DataPacket data = getData(index);
        System.out.printf(
            "Score: %d, length: %d, head on (%d, %d).\n",
                data.score, data.length, (int) data.head.getX(), (int) data.head.getY());
    }
    public int getSteps() { return steps;}
}

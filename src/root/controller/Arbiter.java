package root.controller;

import root.model.Board;
import root.model.Food;
import root.model.Point;
import root.model.Snake;

import java.util.LinkedList;
import java.util.Random;

public final class Arbiter {
    private Random generator;
    private Board board;
    private final long seed;
    private boolean valid;

    public Arbiter(Board board, final long seedNew,
                   final boolean receive){
        this.board = board;
        int width, height;
        width = board.getWidth();
        height = board.getHeight();
        Byte serialNum = (byte) 0;
        LinkedList<Snake> snakeList = board.getSnakeList();
        Food food = board.getFood();
        valid = false;
        generator = new Random();
        if (receive)
            seed = seedNew;
        else
            seed = generator.nextLong();
        generator.setSeed(seed);

        snakeList.add(new Snake(generatePoint(
            new LinkedList<>()), serialNum, "Green"));
        Snake snake = board.getSnake(serialNum);
        food.setPoint(generatePoint(snake.getBody()));

    }

    public Point generatePoint (LinkedList<Point> list) {
//        public Point generatePoint (int width, int height) {
        boolean stacked;
        int x, y;
        int width, height;
        width = board.getWidth();
        height = board.getHeight();
        Point point;
//        LinkedList<Point> body = board.getSnake(0).getBody();

        do {
            stacked = false;
            x = generator.nextInt(width);
            y = generator.nextInt(height);
            point = new Point(x, y);
            point = point.getPointOnGrid();
            if (list.contains(point))
                stacked = true;
        } while (stacked);
        return point;
    }

    public void update() {
        Snake snake = board.getSnake((byte) 0);
        Food food = board.getFood();
        int width, height;
        width = board.getWidth();
        height = board.getHeight();

        if (snake.isMoving()) {
            if (snake.getHead().meetGrid())
                valid = true;
            else valid = false;
            // update head
            if (food.getPoint().equals(snake.getHead())) {
                snake.grow();
                snake.setScore(snake.getScore() + food.getScore());
                food.setPoint(generatePoint(snake.getBody()));
            }
            else
                snake.move(width, height);
            // set snake dead
            if (!snake.isSpawned() & isCollided()){
                snake.setDead();
            }

        }
        else if (!snake.isMoving());

    }

    private boolean isCollided() {
        Snake snake = board.getSnake((byte) 0);
        if (snake.getBody().contains(snake.getHead()))
            return true;
        return false;
    }
    public boolean isValidUpdate() { return valid;}
    public long getSeed() { return seed;}

}

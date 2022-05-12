package root.controller;

import root.model.Board;
import root.model.Food;
import root.model.Point.RectPoint;
import root.model.Snake;

import java.util.LinkedList;
import java.util.Random;

public final class Engine {
    private final Random generator;
    private final Board board;
    private final long seed;
    private boolean valid;

    public Engine(Board board, long seedNew, boolean receive){
        this.board = board;
        Byte serialNum = (byte) 0;
        LinkedList<Snake> snakeList = board.getSnakeList();
        LinkedList<Food> foodList = board.getFoodList();
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
//        foodList.add(new Food((generatePoint(snake.getBody()))));
        foodList.add(Food.Base);
        Food.Base.setPoint(generatePoint(snake.getBody()));
    }

    public RectPoint generatePoint (LinkedList<RectPoint> list) {
        boolean stacked;
        int x, y;
        int width, height;
        width = board.getWidth();
        height = board.getHeight();
        RectPoint point;
        do {
            stacked = false;
            x = generator.nextInt(width);
            y = generator.nextInt(height);
            point = new RectPoint(x, y);
            point = point.getPointOnGrid();
            if (list.contains(point))
                stacked = true;
        } while (stacked);
        return point;
    }

    public void work() {
        Snake snake = board.getSnake(0);
        Food food = board.getFood(0);
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
        Snake snake = board.getSnake(0);
        if (snake.getBody().contains(snake.getHead()))
            return true;
        return false;
    }
    public boolean isValid() { return valid;}
    public long getSeed() { return seed;}

}

package root.model;

import root.controller.Arbiter;

import java.util.ArrayList;
import java.util.LinkedList;

public class Board {
    public static final int SIZE = 10;

    private final int width;
    private final int height;
//    private final int snakeNum;
    private boolean valid;

    private Snake snake;
    private LinkedList<Snake> snakeList;
//    private Snake[] snakeList;
    private Food food;
    private Arbiter arbiter;

    public Board(final int width, final int height) {
        arbiter = new Arbiter();
        valid = false;
        this.width = width;
        this.height = height;

//        snakeNum = 1;
//        snake = new Snake(arbiter.generatePoint(
//                new LinkedList<>(), width, height), 0, "Green");
//        snakeList = new Snake[]{snake};

        snakeList = new LinkedList<>();
        snakeList.add(new Snake(arbiter.generatePoint(
            new LinkedList<>(), width, height), 0, "Green"));
        snake = snakeList.get(0);

        this.food = new Food(arbiter.generatePoint(
            snake.getBody(), width, height));
    }

    public void update() {
        if (snake.isMoving() & snake.getHead().meetGrid())
            valid = true;
        else valid = false;
        if (!snake.isSpawned() & isCollided()){
            snake.setDead();
        }
        else if (!snake.isMoving());
        else {
            if (food.getFoodPoint().equals(snake.getHead())) {
                snake.grow();
                snake.setScore(snake.getScore() + food.getScore());
                food.setFoodPoint(arbiter.generatePoint(
                    snake.getBody(), width, height));
            }
            else
                snake.move(width, height);
        }

    }
    private boolean isCollided() {
        if (snake.getHead().isDuplicateIn(
                snake.getBody(), 1, snake.getLength()))
            return true;
//        if (snake.getBody().contains(snake.getBody().get(0)))
//        if (snake.getBody().contains(snake.getHead()))
//            return true;

        // Linkedlist contains method should include equal comparator
//        int index = snake.getBody().indexOf(snake.getHead());
//        if (index>0 & index<=snake.getLength())
//            return true;
        return false;
    }
    public boolean isValidUpdate() { return valid;}
    public int getWidth() { return width;}
    public int getHeight() { return height;}

    public Snake getSnake(int serialNum) { return snakeList.get(serialNum);}
//    public Snake getSnake(int serialNum) { return snakeList[serialNum];}
    public Food getFood() { return food;}

}

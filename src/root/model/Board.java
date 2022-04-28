package root.model;

import root.controller.RandomGenerator;

import java.util.LinkedList;

public class Board {
    public static final int SIZE = 10;

    private final int width;
    private final int height;
    private boolean valid;

    private Snake snake;
    private LinkedList<Snake> snakeList;
    private Food food;

    public Board(final int width, final int height) {
        valid = false;
        this.width = width;
        this.height = height;

        snakeList = new LinkedList<>();
        snakeList.add(new Snake(RandomGenerator.generatePointonGrid(
            new LinkedList<>(), width, height), 0, "Green"));

        snake = snakeList.get(0);
        this.food = new Food(RandomGenerator.generatePointonGrid(
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
            if (food.getFoodPoint().isEqualTo(snake.getHead())) {
//                if (Point.Equal(food.getFoodPoint(), snake.getHead())) {
                snake.grow();
                snake.setScore(snake.getScore() + food.getScore());
                food.setFoodPoint(RandomGenerator.generatePointonGrid(
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
    public Food getFood() { return food;}

}

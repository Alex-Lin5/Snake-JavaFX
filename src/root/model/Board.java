package root.model;

import root.controller.RandomGenerator;

public class Board {
    public static final int SIZE = 10;

//    private final int cols;
//    private final int rows;
    private final int width;
    private final int height;
    private boolean valid;

    private Snake snake;
    private Food food;

    public Board(final int width, final int height) {
//        rows = (int) height/SIZE;
//        cols = (int) width/SIZE;
        valid = false;
        this.width = width;
        this.height = height;

        this.snake = new Snake(new Point(30, 50));
        this.food = new Food(new Point(100, 100));
    }

    public void update() {
        if (snake.isMoving() & snake.getHead().meetGrid())
            valid = true;
        else valid = false;

        if (!snake.isSpawned() & isCollided()){
            snake.setDead();
        }
        else {
            if (Point.Equal(food.getFoodPoint(), snake.getHead())) {
//            if (Point.Equal(food.getFoodPoint(), snake.getHead().getXYonGrid())) {
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
        if (snake.getBody().get(0).isDuplicateIn(
            snake.getBody(), 1, snake.getLength()))
            return true;
//        for (int i=1; i<snake.getLength(); i++) {
//            if (Point.Equal(snake.getBody().get(0), snake.getBody().get(i)))
//                return true;
//        }
        return false;
    }
    public boolean isValidUpdate() { return valid;}
    public int getWidth() { return width;}
    public int getHeight() { return height;}

    public Snake getSnake() { return snake;}
    public Food getFood() { return food;}

}

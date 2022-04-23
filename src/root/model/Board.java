package root.model;

import root.controller.RandomGenerator;

public class Board {
    public static final int SIZE = 10;

    private final int cols;
    private final int rows;
    private final int width;
    private final int height;

    private Snake snake;
    private Food food;

    public Board(final int width, final int height) {
        rows = (int) height/SIZE;
        cols = (int) width/SIZE;
        this.width = width;
        this.height = height;

        this.snake = new Snake(new Point(30, 50));
        this.food = new Food(new Point(100, 100));
    }

    public void update() {
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
    public int getWidth() { return cols*SIZE;}
    public int getHeight() { return rows*SIZE;}

    public Snake getSnake() { return snake;}
    public Food getFood() { return food;}

}

package root.model;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public final class Board {
    public static final int SIZE = 10;

    private final int width;
    private final int height;
    private final long seed;
    private boolean valid;

    private Snake snake;
    private LinkedList<Snake> snakeList;
    private Food food;
    private Random random;
//    private Arbiter arbiter;

    public Board(final long seedNew, final boolean receive,
                 final int width, final int height) {
        random = new Random();
        Byte serialNum = (byte) 0;
        if (receive)
            this.seed = seedNew;
        else
            this.seed = random.nextLong();
        valid = false;
        this.width = width;
        this.height = height;
        random.setSeed(this.seed);

        snakeList = new LinkedList<>();
        snakeList.add(new Snake(generatePoint(
            new LinkedList<>(), width, height), serialNum, "Green"));
        snake = snakeList.get(0);

        this.food = new Food(generatePoint(
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
            if (food.getPoint().equals(snake.getHead())) {
                snake.grow();
                snake.setScore(snake.getScore() + food.getScore());
                food.setPoint(generatePoint( snake.getBody(),
                    width, height));
            }
            else
                snake.move(width, height);
        }

    }

    private Point generatePoint (LinkedList<Point> list, int width, int height) {
        int x, y;
        boolean stacked;
        Point point;

        do {
            stacked = false;
            x = random.nextInt(width);
            y = random.nextInt(height);
//            System.out.printf("(%d, %d)\n", x, y);
            point = new Point(x, y);
            point = point.getPointOnGrid();
            if (list.contains(point))
                stacked = true;
        } while (stacked);
        return point;
    }
    private boolean isCollided() {
        if (snake.getBody().contains(snake.getHead()))
            return true;
        return false;
    }
    public boolean isValidUpdate() { return valid;}
    public int getWidth() { return width;}
    public int getHeight() { return height;}
    public Snake getSnake(int serialNum) { return snakeList.get(serialNum);}
    public Food getFood() { return food;}
    public long getSeed() { return seed;}

}

package root.model;


import java.util.LinkedList;

public final class Board {
    public static final int SIZE = 10;

    private final int width;
    private final int height;
    private final LinkedList<Snake> snakeList;
    private final LinkedList<Food> foodList;
    private byte snakeNum;

    public Board(final int width, final int height) {
        this.width = width;
        this.height = height;
        snakeNum = 1;

        snakeList = new LinkedList<>();
        foodList = new LinkedList<>();
        foodList.add(new Food());
    }

    public int getWidth() { return width;}
    public int getHeight() { return height;}
    public Snake getSnake(Number serialNum) {
        return snakeList.get(serialNum.byteValue());
    }
    public LinkedList<Snake> getSnakeList() { return snakeList;}
    public Food getFood() { return foodList.get(0);}
    public LinkedList<Food> getFoodList() { return foodList;}
    public byte getSnakeNum() { return snakeNum;}
    public void setSnakeNum(byte snakeNum) { this.snakeNum = snakeNum;}
}

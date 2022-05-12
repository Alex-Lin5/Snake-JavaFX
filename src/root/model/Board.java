package root.model;


import java.util.LinkedList;

import static root.Main.BOARD_HEIGHT;
import static root.Main.BOARD_WIDTH;

public final class Board {
    public static final int SIZE = 10;

    private final int width;
    private final int height;
    private final LinkedList<Snake> snakeList;
    private final LinkedList<Food> foodList;
//    private byte snakeNum;
//    private byte foodNum;

    public Board() {
        this.width = BOARD_WIDTH;
        this.height = BOARD_HEIGHT;

        snakeList = new LinkedList<>();
        foodList = new LinkedList<>();
    }

    public int getWidth() { return width;}
    public int getHeight() { return height;}
    public Snake getSnake(Number serialNum) {
        return snakeList.get(serialNum.byteValue());
    }
    public LinkedList<Snake> getSnakeList() { return snakeList;}
    public Food getFood(Number num) { return foodList.get(num.byteValue());}
    public LinkedList<Food> getFoodList() { return foodList;}
//    public byte getSnakeNum() { return snakeNum;}
//    public void setSnakeNum(byte snakeNum) { this.snakeNum = snakeNum;}
}

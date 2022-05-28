package root.model;


import root.model.Food.Food;
import root.model.Point.RectPoint;

import java.util.LinkedList;

import static root.Main.BOARD_HEIGHT;
import static root.Main.BOARD_WIDTH;

public final class Board {
    public static final int SIZE = 10;

    private final int width;
    private final int height;
    private final LinkedList<Snake> snakeList;
    private final LinkedList<Food> foodList;
    private int step;
//    private byte snakeNum;
//    private byte foodNum;

    public Board() {
        this.width = BOARD_WIDTH;
        this.height = BOARD_HEIGHT;
        step = 0;

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
    public int getStep() { return step;}
    public void setStep(int step) { this.step = step;}

    public LinkedList<RectPoint> getFoodPointList() {
        LinkedList<RectPoint> list = new LinkedList<>();
        for (Food food: foodList)
            list.add(food.getPoint());
        return list;
    }
    public LinkedList<RectPoint> getSnakeBodyList() {
        LinkedList<RectPoint> list = new LinkedList<>();
        for (Snake snake: snakeList)
            list.addAll(snake.getBody());
        return list;
    }
    public void printStatus() {
        System.out.printf("Board Step: %d: Food::", step);
        int index = 1;
        for (Food food: foodList) {
            System.out.printf("%d tag%s = (%d, %d). ", index, food.getSerialNum(),
                    food.getPoint().getX(), food.getPoint().getY());
            index++;
        }
        System.out.printf("\n");
        Snake snake = snakeList.get(0);
        System.out.printf("Head on (%d, %d).\n", snake.getHead().getX(),
                snake.getHead().getY());
    }
}

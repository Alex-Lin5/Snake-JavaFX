package root.model;

import root.model.Food;
import root.model.Snake;

public class PixelPad {
    public static final int SIZE = 10;

    private final int cols;
    private final int rows;

    private Snake snake;
    private Food food;

    public PixelPad(final int width, final int height) {
        rows = (int) height/SIZE;
        cols = (int) width/SIZE;

        Snake snake1 = new Snake(new Point(30, 50));
        Food food1 = new Food(new Point(10, 20));
    }

    public int getCols() { return cols;}
    public int getRows() { return rows;}
    public int getWidth() { return cols*SIZE;}
    public int getHeight() { return rows*SIZE;}

    public Snake getSnake() { return snake;}
    public Food getFood() { return food;}

}

package root.view;

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
    }

    public int getCols() { return cols;}
    public int getRows() { return rows;}
    public int getWidth() { return cols*SIZE;}
    public int getHeight() { return rows*SIZE;}

    public Snake getSnake() { return snake;}
    public Food getFood() { return food;}

}

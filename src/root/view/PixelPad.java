package root.view;

public class PixelPad {
    public static final int SIZE = 10;

    private final int cols;
    private final int rows;

    public PixelPad(final int width, final int height) {
        rows = (int) height/SIZE;
        cols = (int) width/SIZE;
    }

    public int getCols() { return cols;}
    public int getRows() { return rows;}
    public int getWidth() { return rows*SIZE;}
    public int getHeight() { return cols*SIZE;}

}

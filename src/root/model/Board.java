package root.model;

public class Board {
    public static final int SIZE = 10;

    private final int cols;
    private final int rows;

    private Snake snake;
    private Food food;

    public Board(final int width, final int height) {
        rows = (int) height/SIZE;
        cols = (int) width/SIZE;

        this.snake = new Snake(new Point(30, 50));
        this.food = new Food(new Point(100, 100));
    }

    public void update() {
        snake.move(cols * SIZE, rows * SIZE);
    }
    public int getWidth() { return cols*SIZE;}
    public int getHeight() { return rows*SIZE;}

    public Snake getSnake() { return snake;}
    public Food getFood() { return food;}

}

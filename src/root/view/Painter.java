package root.view;

import root.model.Board;
import root.model.Food;
import root.model.Point;
import root.model.Snake;

import static root.model.Board.SIZE;
public final class Painter {
    private static String PadColor = Palette.BLACK;
    private static String FoodColor = Palette.WHEAT;
    public static String SnakeColor = Palette.BROWN;
    private static String GridColor = Palette.GREY;
    private static String DeadColor = Palette.RED;
    private static int size = SIZE;
    private final Pen pen;
    private final Board board;

    public Painter(Board board, Pen pen) {
        this.board = board;
        this.pen = pen;
    }

    public void Paint(){
        Snake snake = board.getSnake(0);
        pen.setColor(PadColor);
        pen.drawRect(new Point(0, 0),
                new Point(board.getWidth(), board.getHeight()));

        pen.setColor(GridColor);
        pen.drawGrid(board.getWidth(), board.getHeight(), size);
        pen.setColor(FoodColor);
        for (Food food: board.getFoodList())
            pen.drawSquare(food.getPoint(), size);


        pen.setColor(SnakeColor);
        for(int i=0; i<snake.getLength()-1; i++) {
            pen.drawSquare(snake.getBody().get(i), size);
        }
        pen.drawSquare(snake.getHead(), size);
        pen.drawSquare(snake.getTail(), size);

        if (snake.isDead()) {
            pen.setColor(DeadColor);
            pen.drawSquare(snake.getHead(), size);
        }

    }
}

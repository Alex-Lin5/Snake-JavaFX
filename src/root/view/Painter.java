package root.view;

import root.model.Board;
import root.model.Food.Food;
import root.model.Point.RectPoint;

import root.model.Snake;

import static root.model.Board.SIZE;
public final class Painter {
    private static Color PadColor = Color.BLACK;
    private static Color FoodColor = Color.WHEAT;
    public static Color SnakeColor = Color.BROWN;
    private static Color GridColor = Color.GREY;
    private static Color DeadColor = Color.RED;
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
        pen.drawRect(new RectPoint(0, 0),
                new RectPoint(board.getWidth(), board.getHeight()));

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

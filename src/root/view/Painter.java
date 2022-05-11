package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import root.model.Board;
import root.model.Point;
import root.model.Snake;

import static root.model.Board.SIZE;
//import static root.Main.PANEL
public final class Painter {
    private static Color PadColor = Color.BLACK;
    private static Color FoodColor = Color.WHEAT;
    public static Color SnakeColor = Color.GREEN;
    private static Color GridColor = Color.GREY;
    private static Color DeadColor = Color.RED;
    private static int size = SIZE;
    private final GraphicsContext gc;
    private final Board board;

    public Painter(Board board, GraphicsContext gc) {
        this.board = board;
        this.gc = gc;
    }

    public void Paint(){
        Snake snake = board.getSnake((byte) 0);
        gc.setFill(PadColor);
        gc.fillRect(0 , 0, board.getWidth(), board.getHeight());

        gc.setFill(GridColor);
        gc.setStroke(GridColor);

        DrawSquare(FoodColor, board.getFood().getPoint(), size);

        for (int i=0; i<board.getWidth()/size; i++)
            gc.strokeLine(i*size, 0, i*size, board.getHeight()-gc.getLineWidth());
        for (int i=0; i<board.getHeight()/size; i++)
            gc.strokeLine(0, i*size, board.getWidth()-gc.getLineWidth(), i*size);

        gc.setFill(SnakeColor);
        for(int i=0; i<snake.getLength()-1; i++) {
            DrawSquare(SnakeColor, snake.getBody().get(i), size);
        }
        DrawSquare(SnakeColor, snake.getHead(), size);
        DrawSquare(SnakeColor, snake.getTail(), size);
        if (snake.isDead()) {
            DrawSquare(DeadColor, snake.getHead(), size);
        }

    }
    private void DrawSquare(Color color, Point point, int size) {
        gc.setFill(color);
        gc.fillRect(point.getX(), point.getY(), size, size);
    }

    public void clear() {
//    gc.clearRect(0,0, theBoard.getWidth(), theBoard.getHeight()+40);
    gc.clearRect(0,0, board.getWidth(), board.getHeight());
    }
}

package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import root.model.Board;
import root.model.Point;

import static root.model.Board.SIZE;
//import static root.Main.PANEL
public class Painter {
    private static Color PadColor = Color.BLACK;
    private static Color FoodColor = Color.WHEAT;
    private static Color SnakeColor = Color.GREEN;
    private static Color GridColor = Color.GREY;
    private static Color DeadColor = Color.RED;
    private static int size = SIZE;
//    private static int panelWidth = PANEL_WIDTH;
//    private static int panelHeight = PANEL_HEIGHT;
    private final GraphicsContext gc;
    private final Board board;

    public Painter(Board board, GraphicsContext gc) {
        this.board = board;
        this.gc = gc;
    }

    public void Paint(){
//        public static void paint(Board board, GraphicsContext gc){
        gc.setFill(PadColor);
        gc.fillRect(0 , 0, board.getWidth(), board.getHeight());

        gc.setFill(GridColor);
        gc.setStroke(GridColor);
        for (int i=0; i<board.getWidth()/size; i++)
            gc.strokeLine(i*size, 0, i*size, board.getHeight()-gc.getLineWidth());
        for (int i=0; i<board.getHeight()/size; i++)
            gc.strokeLine(0, i*size, board.getWidth()-gc.getLineWidth(), i*size);

        gc.setFill(SnakeColor);
        for(int i=0; i<board.getSnake().getLength()-1; i++) {
            DrawSquare(SnakeColor, board.getSnake().getBody().get(i), size);
        }
        DrawSquare(SnakeColor, board.getSnake().getHead(), size);
        DrawSquare(SnakeColor, board.getSnake().getTail(), size);

        if (board.getSnake().isDead()) {
            DrawSquare(DeadColor, board.getSnake().getHead(), size);
        }

        DrawSquare(FoodColor, board.getFood().getFoodPoint(), size);
    }
    private void DrawSquare(Color color, Point point, int size) {
        gc.setFill(color);
        gc.fillRect(point.getX(), point.getY(), size, size);
    }

//    public static void clear(Canvas theCanvas, GraphicsContext gc) {
//        gc.clearRect(0,0, theCanvas.getWidth(), theCanvas.getHeight());
//    }
public void clear() {
//    public static void clear(Board theBoard, GraphicsContext gc) {
//    gc.clearRect(0,0, theBoard.getWidth(), theBoard.getHeight()+40);
    gc.clearRect(0,0, board.getWidth(), board.getHeight()+40);
    }
}

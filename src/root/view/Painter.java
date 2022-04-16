package root.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import root.model.Board;

import static root.model.Board.SIZE;

public class Painter {
    private static Color PadColor = Color.BLACK;
    private static Color FoodColor = Color.WHEAT;
    private static Color SnakeColor = Color.WHITE;
    private static Color GridColor = Color.GREY;
    private static int size = SIZE;

    public static void paint(Board pad, GraphicsContext gc){
        gc.setFill(PadColor);
        gc.fillRect(0 , 0, pad.getWidth(), pad.getHeight());

        gc.setFill(GridColor);
        gc.setStroke(GridColor);
        for (int i=0; i<pad.getWidth()/size; i++)
            gc.strokeLine(i*size, 0, i*size, pad.getHeight());
        for (int i=0; i<pad.getHeight()/size; i++)
            gc.strokeLine(0, i*size, pad.getWidth(), i*size);

        gc.setFill(SnakeColor);
        for(int i=0; i<pad.getSnake().getLength()-1; i++) {
            double x = pad.getSnake().getBody().get(i).getX();
            double y = pad.getSnake().getBody().get(i).getY();
            gc.fillRect(x, y, size, size);
        }
        gc.fillRect(pad.getSnake().getHead().getX(), pad.getSnake().getHead().getY(),
                size, size);
        gc.fillRect(pad.getSnake().getTail().getX(), pad.getSnake().getTail().getY(),
                size, size);

        gc.setFill(FoodColor);
        gc.fillRect(pad.getFood().getFoodPoint().getX(),
                pad.getFood().getFoodPoint().getY(), size, size);
    }

    public static void clear(Canvas theCanvas, GraphicsContext gc) {
        gc.clearRect(0,0, theCanvas.getWidth(), theCanvas.getHeight());
    }
}

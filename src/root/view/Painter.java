package root.view;

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

        gc.setFill(FoodColor);
        gc.fillRect(pad.getFood().getFoodPoint().getX(),
            pad.getFood().getFoodPoint().getY(), size, size);
//        gc.fillRect(10, 20, size, size);

        gc.setFill(SnakeColor);
//        gc.fillRect(50, 50, size, size);
//        pad.getSnake().body.forEach( Pixel -> gc.fillRect(
//            Pixel.getX(), Pixel.getY(), size, size));
//        double xShift = xHead - pad.getSnake().head.getCols()*size;
//        double yShift = yHead - pad.getSnake().head.getRows()*size;
//        double xHead = pad.getSnake().head.getX();
//        double yHead = pad.getSnake().head.getY();
//        gc.fillRect(xHead, yHead, size, size);

        for(int i=0; i<pad.getSnake().getBody().size(); i++) {
//            Point temp
//            int HeadIndex = pad.getSnake().body.size();
//            int rows = pad.getSnake().body.get(i).getRows();
//            int cols = pad.getSnake().body.get(i).getCols();
            double x = pad.getSnake().getBody().get(i).getX();
            double y = pad.getSnake().getBody().get(i).getY();
//            gc.fillRect(x-xShift, y-yShift, size, size);
            gc.fillRect(x, y, size, size);
        }

    }
}

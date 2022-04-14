package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import root.model.Food;
import root.model.Snake;
import root.model.PixelPad;

import static root.model.PixelPad.SIZE;

public class Painter {
    private static Color PadColor = Color.BLACK;
    private static Color FoodColor = Color.WHEAT;
    private static Color SnakeColor = Color.WHITE;
    private static Color GridColor = Color.GREY;
    private static int size = SIZE;

//    private static Snake snake;
//    private static Food food;

    public static void paint(PixelPad pad, GraphicsContext gc){
        gc.setFill(PadColor);
        gc.fillRect(0 , 0, pad.getWidth(), pad.getHeight());

        gc.setFill(GridColor);

        gc.setFill(FoodColor);
        gc.fillRect(pad.getFood().getFoodPoint().getX(),
                pad.getFood().getFoodPoint().getY(), size, size);
//        gc.fillRect(10, 20, size, size);

        gc.setFill(SnakeColor);
//        gc.fillRect(50, 50, size, size);
        pad.getSnake().body.forEach(Pixel -> gc.fillRect(
                Pixel.getX(), Pixel.getY(), size, size));
        
    }
}

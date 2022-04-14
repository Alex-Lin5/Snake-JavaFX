package root.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import root.model.Food;
import root.model.Snake;
import root.view.PixelPad;

import static root.view.PixelPad.SIZE;

public class Painter {
    private static Color PadColor = Color.BLACK;
    private static Color FoodColor = Color.WHEAT;
    private static Color SnakeColor = Color.WHITE;
    private static Color GridColor = Color.GREY;
    private static int size = SIZE;

    private static Snake snake;
    private static Food food;

    public static void paint(PixelPad pad, GraphicsContext gc){
        gc.setFill(PadColor);
        gc.fillRect(0 , 0, pad.getWidth(), pad.getHeight());

        gc.setFill(GridColor);

        
        gc.setFill(FoodColor);
//        food = pad.getFood();
//        gc.fillRect(food.getX(), food.getY(), size, size);
        gc.fillRect(pad.getFood().getX(), pad.getFood().getY(), size, size);

        gc.setFill(SnakeColor);
        snake = pad.getSnake();
        gc.fillRect(50, 50, size, size);
        
    }
}

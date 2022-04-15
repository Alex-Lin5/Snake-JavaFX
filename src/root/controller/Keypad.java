package root.controller;

import javafx.scene.canvas.Canvas;
import root.model.Snake;

public class Keypad {
    public static void getKey(Snake snake, Canvas myCanvas) {
        myCanvas.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP:
                    snake.setUp();
                    break;
                case DOWN:
                    snake.setDown();
                    break;
                case LEFT:
                    snake.setLeft();
                    break;
                case RIGHT:
                    snake.setRight();
                    break;
                case DIGIT1:
                    snake.setStatic();
                    break;
                case ENTER:
//                    if (loop.isOver()) {
//                        reset();
//                        (new Thread(loop)).start();
//                    }
                    break;
                default:
                    break;

            }
        });
    }
}

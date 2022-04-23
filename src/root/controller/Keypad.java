package root.controller;

import javafx.scene.canvas.Canvas;
import root.model.Snake;

public class Keypad {
    public static void getKey(Snake snake, Canvas myCanvas) {
        myCanvas.setOnKeyPressed(event -> {
            if (snake.isTurned()) {
                if (snake.isMoving())
                    return;

            }
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
                    if (snake.isDead()) {
//                        (new Thread(loop)).start();
                    }
                    break;
                default:
                    break;

            }
            if (snake.isDead())
                return;
            else snake.setTurned();
        });
    }
}

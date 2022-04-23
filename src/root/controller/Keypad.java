package root.controller;

import javafx.scene.canvas.Canvas;
import root.model.Snake;

public class Keypad {
    private boolean restart;
    private final Canvas canvas;
    private final Snake snake;

    public Keypad(Snake snake, Canvas myCanvas) {
        canvas = myCanvas;
        this.snake = snake;
    }
    public boolean getKey() {
//        public boolean getKey(boolean restart) {
        restart = false;
        canvas.setOnKeyPressed(event -> {
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
                    if (snake.isDead())
                        restart = true;
                    break;
                default:
                    break;

            }
            if (snake.isDead())
                ;
//                return;
            else snake.setTurned();
        });
        return restart;
    }
}

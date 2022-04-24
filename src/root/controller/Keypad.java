package root.controller;

import javafx.scene.canvas.Canvas;
import root.model.Snake;

public class Keypad {
    private boolean restart;
    private final Canvas canvas;
    private final Snake snake;
    private final Debugger debugger;

    public Keypad(ThreadLoop loop, Canvas myCanvas) {
        canvas = myCanvas;
        snake = loop.board.getSnake(0);
        debugger = loop.debugger;
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
                case DIGIT0:
                    if (debugger.on)
                        debugger.on = false;
                    else debugger.on = true;
                    break;
                case PAGE_DOWN:
                    debugger.backSteps -= 1;
                    break;
                case PAGE_UP:
                    debugger.backSteps += 1;
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
            else if (debugger.on) ;
            else snake.setTurned();
        });
        return restart;
    }
}

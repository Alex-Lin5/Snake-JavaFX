package root.controller;

import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import root.model.Snake;

public class Keypad {
    private boolean restart;
    private final Canvas canvas;
    private final Snake snake;
    private final Debugger debugger;
    private final Stage theStage;

    public Keypad(ThreadLoop loop, Canvas myCanvas, Stage stage) {
        theStage = stage;
        this.canvas = myCanvas;
        snake = loop.board.getSnake(0);
        debugger = loop.debugger;
    }
    public void getKey() {
        restart = false;
        canvas.setOnKeyPressed(event -> {
            if (snake.isDead()) ;
            else if (snake.isTurned()) {
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
                    if (debugger.isOn())
                        debugger.setOff();
                    else debugger.setOn();
                    break;
                case PAGE_DOWN:
                    debugger.setBackSteps(-1);
                    break;
                case PAGE_UP:
                    debugger.setBackSteps(1);
                    break;
                case ENTER:
                    if (snake.isDead())
                        restart = true;
                    break;
                default:
                    break;
            }
            if (debugger.isOn()) ;
            else snake.setTurned();

            if (restart) {
                Initializer init = new Initializer(theStage);
                init.initialize();
            }
        });
    }
}

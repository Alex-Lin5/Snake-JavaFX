package root.controller;

import root.model.Board;
import root.model.Trail;
import root.model.Snake;

public class Debugger {
    private boolean on;
    private boolean tuned;
    public int backSteps;
    private int currentStep;
    public int goingBack;
    private final Recorder recorder;
    private final Board board;

    public Debugger(Recorder recorder, Board board) {
        on = false;
        tuned = false;
        goingBack = 0;
        backSteps = 0;
        currentStep = 0;
        this.recorder = recorder;
        this.board = board;
    }
    public void debug() {
        if (!tuned) {
            tuned = true;
            currentStep = recorder.getSteps();
        }

        setSnake();
        setFood();
    }

    public void setSnake() {
        Trail data = recorder.getData(0);
        Snake snake = board.getSnake(0);
        int LatestStep = recorder.getSteps();
        if (backSteps < 0)
            backSteps = 0;
        else if (backSteps > LatestStep-1)
            backSteps = LatestStep-1;
        int nextStep = LatestStep-backSteps;
        if (currentStep < nextStep)
            goingBack = 1; // goingBack = false;
        else if (currentStep > nextStep)
            goingBack = -1;// goingBack = true;
        else if (currentStep == nextStep)
            goingBack = 0;

        int length = data.getLength(nextStep);
        int tailindex = currentStep-length;
        if (tailindex < 1)
            tailindex = 1;
        snake.setAlive();
        snake.setStatic();
        snake.setLength(length);
        snake.setScore(data.getScore(nextStep));

        if (goingBack == -1) { // going backward
            snake.getBody().removeFirst();
            if (snakeExtended(currentStep, nextStep) == 1) {
                snake.getBody().addLast(data.getHead(tailindex));
                snake.getBody().addLast(data.getHead(tailindex-1));
            }
            if (snakeExtended(currentStep, nextStep) == -1);
            if (snakeExtended(currentStep, nextStep) == 0)
                snake.getBody().addLast(data.getHead(tailindex));
        }
        else if (goingBack == 1){ // going forward
            snake.getBody().addFirst(data.getHead(nextStep));
            if (snakeExtended(currentStep, nextStep) == 1);
            if (snakeExtended(currentStep, nextStep) == -1) {
                snake.getBody().removeLast();
                snake.getBody().removeLast();
            }
            if (snakeExtended(currentStep, nextStep) == 0);
                snake.getBody().removeLast();

        }
        else if (goingBack == 0); // no key input, no operation

        snake.setHead(snake.getBody().get(0));
        snake.setTail(snake.getBody().get(length-1));

        currentStep = nextStep;        
        }

    public void reset() {
        int stepNow = currentStep-backSteps;
        recorder.setSteps(stepNow);
        recorder.removeData(
            (Integer) board.getSnake(0).getSerialNum(),
                stepNow);
        backSteps = 0;
        goingBack = 0;
        tuned = false;
    }
    private int snakeExtended(int currentStep, int nextStep) {
        Trail data = recorder.getData(0);
        int currentLength = data.getLength(currentStep);
        int nextLength = data.getLength(nextStep);
        if (currentLength < nextLength)
            return 1; // will add length of 1 
        else if (currentLength > nextLength)
            return -1; // will subtract length of 1
        else return 0;
    }

    private void setFood() {
        Trail value = recorder.getData(0);
        board.getFood().setFoodPoint(value.getFood(currentStep));
    }
    public boolean isOn() { return on;}
    public void setOn() { on = true;}
    public void setOff() { on = false;}
    public boolean isTuned() { return tuned;}
}

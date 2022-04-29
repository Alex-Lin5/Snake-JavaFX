package root.controller;

import root.model.Board;
import root.model.Trail;
import root.model.Snake;

public class Debugger {
    enum status { BACK, FORWARD, STILL};
    enum len { LONGER, SHORTER, STILL};
    status object;
    private boolean on;
    private boolean tuned;
    private int backSteps;
    private int currentStep;
    private int nextStep;
    private final Recorder recorder;
    private final Board board;

    public Debugger(Recorder recorder, Board board) {
        on = false;
        tuned = false;
        object = status.STILL;
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

        checkStatus();
        setSnake();
        setFood();
        currentStep = nextStep;
    }

    public void setSnake() {
        Trail data = recorder.getData(0);
        Snake snake = board.getSnake(0);

        int length = data.getLength(nextStep);
        int tailIndex = currentStep-length;
        if (tailIndex < 1)
            tailIndex = 1;
        snake.setAlive();
        snake.setStatic();
        snake.setLength(length);
        snake.setScore(data.getScore(nextStep));

        if (object == status.BACK) { // going backward
            snake.getBody().removeFirst();
            if (snakeExtended(currentStep, nextStep) == len.LONGER) {
                snake.getBody().addLast(data.getHead(tailIndex));
                snake.getBody().addLast(data.getHead(tailIndex-1));
            }
            else if (snakeExtended(currentStep, nextStep) == len.SHORTER);
            else if (snakeExtended(currentStep, nextStep) == len.STILL)
                snake.getBody().addLast(data.getHead(tailIndex));
        }
        else if (object == status.FORWARD){ // going forward
            snake.getBody().addFirst(data.getHead(nextStep));
            if (snakeExtended(currentStep, nextStep) == len.LONGER);
            else if (snakeExtended(currentStep, nextStep) == len.SHORTER) {
                snake.getBody().removeLast();
                snake.getBody().removeLast();
            }
            else if (snakeExtended(currentStep, nextStep) == len.STILL)
                snake.getBody().removeLast();

        }
        else if (object == status.STILL); // no key input, no operation

        snake.setHead(snake.getBody().get(0));
        snake.setTail(snake.getBody().get(length-1));

        if (object != status.STILL)
            recorder.printValue(0, nextStep, object);
//        currentStep = nextStep;
        }

    public void reset() {
        int stepNow = currentStep-backSteps;
        recorder.setSteps(stepNow);
        recorder.removeData(
            (Integer) board.getSnake(0).getSerialNum(),
                stepNow);
        backSteps = 0;
        object = status.STILL;
        tuned = false;
    }
    private len snakeExtended(int currentStep, int nextStep) {
        Trail data = recorder.getData(0);
        int currentLength = data.getLength(currentStep);
        int nextLength = data.getLength(nextStep);
        if (currentLength < nextLength)
            return len.LONGER; // will add length of 1
        else if (currentLength > nextLength)
            return len.SHORTER; // will subtract length of 1
        else return len.STILL;
    }

    private void setFood() {
        Trail value = recorder.getData(0);
        board.getFood().setFoodPoint(value.getFood(currentStep));
    }
    private void checkStatus() {
        int LatestStep = recorder.getSteps();
        if (backSteps < 0)
            backSteps = 0;
        else if (backSteps > LatestStep-1)
            backSteps = LatestStep-1;
        nextStep = LatestStep-backSteps;
        if (currentStep < nextStep)
            object = status.FORWARD;
        else if (currentStep > nextStep)
            object = status.BACK;
        else if (currentStep == nextStep)
            object = status.STILL;
    }
    public boolean isOn() { return on;}
    public void setOn() { on = true;}
    public void setOff() { on = false;}
    public boolean isTuned() { return tuned;}

    public void setBackSteps(int step) {
        this.backSteps += step;
    }
}

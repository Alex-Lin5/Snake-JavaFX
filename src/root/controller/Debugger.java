package root.controller;

import root.model.Board;
import root.model.DataPacket;
import root.model.Snake;

public class Debugger {
    public boolean on;
    public boolean tuned;
    public int backSteps;
    private int currentStep;
    public int goingBack;
//    private boolean goingBack;
    private final Recorder recorder;
    private final Board board;

//    private int steps;
//    private DataPacket value;

    public Debugger(Recorder recorder, Board board) {
        on = false;
        tuned = false;
//        goingBack = false;
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

//        if (!on) {
//            recorder.setSteps(currentStep-backSteps);
//            goingBack = 0;
//            for (int i=0; i<backSteps; i++)
//                recorder.removeData(recorder.getSteps()-i);
//        }
    }

    public void setSnake() {
        DataPacket valueNext;
        DataPacket valueLatest, valueTail;
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

        valueNext = recorder.getData(nextStep);
        int tailindex = currentStep-valueNext.length;
        if (tailindex < 1)
            tailindex = 1;
        valueTail = recorder.getData(tailindex);
        snake.setAlive();
        snake.setLength(valueNext.length);
        snake.setScore(valueNext.score);
        snake.setStatic();

        int length = valueNext.length;
        if (goingBack == -1) { // going backward
            snake.getBody().removeFirst();
            if (snakeExtended(currentStep, nextStep) == 1) {
                snake.getBody().addLast(valueTail.head);
                snake.getBody().addLast(
                    recorder.getData(tailindex-1).head
                );
            }
            if (snakeExtended(currentStep, nextStep) == -1);
            if (snakeExtended(currentStep, nextStep) == 0);
                snake.getBody().addLast(valueTail.head);
        }
        else if (goingBack == 1){ // going forward
            snake.getBody().addFirst(valueNext.head);
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
        recorder.setSteps(currentStep-backSteps);
        for (int i=0; i<backSteps; i++)
            recorder.removeData(currentStep-i);
        backSteps = 0;
        goingBack = 0;
    }
    private int snakeExtended(int currentStep, int nextStep) {
        int currentLength = recorder.getData(currentStep).length;
        int nextLength = recorder.getData(nextStep).length;
        if (currentLength < nextLength)
            return 1; // will add length of 1 
        else if (currentLength > nextLength)
            return -1; // will subtract length of 1
        else return 0;
    }

    private void setFood() {
        DataPacket value;
        value = recorder.getData(
            recorder.getSteps() - backSteps);
        board.getFood().setFoodPoint(value.food);
    }


}

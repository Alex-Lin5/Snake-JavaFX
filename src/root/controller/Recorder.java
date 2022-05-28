package root.controller;

import root.model.Board;
import root.model.Food.*;
import root.model.Trail;
import root.model.Snake;

import java.util.HashMap;

public final class Recorder {
    private int step;
    private int timeTick;
    private final Board board;
    private final Engine engine;
    private Snake snake;
    private Trail value;
    private HashMap<Byte, Trail> recording;
    public Recorder(Board board, Engine engine) {
        this.board = board;
        this.engine = engine;
        this.recording = new HashMap<>();
        snake = board.getSnake(0);
        step = 0;
        timeTick = 0;
        
        value = new Trail(snake.getHead(),
            board.getFoodList(), snake.getLength());
        recording = new HashMap<>();
        recording.put(snake.getSerialNum(), value);

    }

    public void record() {
        timeTick += 1;
        if (engine.isValid()) {
            step += 1;
            board.setStep(step);
            value.addHead(step, snake.getBody().get(0));
            if (snake.isGrowing()) {
//                value.setFood(step, board.getFood().getPoint());
                value.addFoodList(step, board.getFoodList());
                value.addLength(step, snake.getLength());
                value.addScore(step, snake.getScore());
            }
            value.setLifespan(step);

//            if (snake.isSpawned())
//                if (snake.isDead())
//                    value.dead = true;
            
        }
    }
    public Trail getData(Number serialNum) {
        return recording.get(serialNum.byteValue());
    }
    public void removeData(Number serialNum, Integer step) {
        Trail data = getData(serialNum);
        data.removeFrom(step);
    }
    public void printDeath(Number serialNum) {
        Trail data = getData(serialNum);
        String out;
        int life = data.getLifespan();
        out = String.format("Score: %d, length: %d, ",
                data.getScore(life), data.getLength(life));
        out += "head hits on " + String.valueOf(data.getHead(life)) + '\n';
        out += String.format("Seed is %d\n", engine.getSeed());
        System.out.printf(out);
    }
    public void printValue(Number serialNum, Integer step, Debugger.status theStatus) {
        Trail data = getData(serialNum);
        String sign, out;
        if (theStatus == Debugger.status.BACK)
            sign = "--";
        else if (theStatus == Debugger.status.FORWARD)
            sign = "++";
        else
            sign = "==";
        out = String.format("%s Step: %d, Score: %d, length: %d, head on ",
                sign, step, data.getScore(step), data.getLength(step));
        out += String.valueOf(data.getHead(step)) + ".";
        System.out.printf(out + "\n");
        printFood(serialNum, step);
    }
    private void printFood(Number serialNum, Integer step){
        Trail data = getData(serialNum);
        String out = "FoodList: ";
        int index = 0;
        for (Food food: data.getFoodList(step)) {
            String oneFood = String.format("%d/", index);
            oneFood += String.valueOf(food) + " ";
            out += oneFood;
            index++;
        }
        System.out.printf(out + "\n");
    }
    public void setSteps(int step) { this.step = step;}
    public int getTimeTick() { return timeTick;}
    public int getSteps() { return step;}
}

package root.model;

import java.util.HashMap;

public class Trail {
//    private int step;

    //    private int[] snakeDirection;
//    private boolean dead;
    private HashMap<Integer, Point> head; // current point of head
    private HashMap<Integer, Point> food;
    private HashMap<Integer, Integer> length;
    private HashMap<Integer, Integer> score;
    private int lifespan;

    public Trail(Point head, Point food, int length) {
        this.head = new HashMap<>();
        this.food = new HashMap<>();
        this.length = new HashMap<>();
        score = new HashMap<>();

        lifespan = 0;
        this.head.put(0, head);
        this.food.put(0, food);
        this.length.put(0, length);
        score.put(0, 0);
    }

    public int getLifespan() { return lifespan;}
    public void setLifespan(int step) { lifespan = step;}
    public void setFood(int step, Point point) {
        food.put(step, point);
    }
    public Point getFood(int step) {
        return food.get(getKey((Integer) step, food));
    }
    public void setHead(int step, Point point) {
        head.put(step, point);
    }
    public Point getHead(int step) {
        return head.get(step);
    }
    public void setLength(int step, int length) {
        this.length.put(step, length);
    }
    public int getLength(int step) {
        return length.get(getKey((Integer) step, length));
    }
    public void setScore(int step, int score) {
        this.score.put(step, score);
    }
    public int getScore(int step) {
        return score.get(getKey((Integer) step, score));
    }

    public void removeFrom(Integer step) {
        removeKeys(step, head);
        removeKeys(step, food);
        removeKeys(step, length);
        removeKeys(step, score);
        lifespan = step;
    }
    private <V> void removeKeys(Integer step, HashMap<Integer, V> obj) {
//        for (Integer key: obj.keySet()) {
//            if (key > step)
//                obj.remove(key);
//        }
        int stepLatest = head.size();
        for (int i=0; i<stepLatest-step; i++) {
            if (obj.containsKey(stepLatest-i))
                obj.remove(stepLatest-i);
        }

    }
    private <V> Integer getKey(Integer step, HashMap<Integer, V> obj) {
        int target = step;
        int min = step;
        for (Integer key: obj.keySet()) {
            if (key == step) break;
            else if (key > step) ;
            else if (key < step) {
                if (step-key <= min) {
                    min = step-key;
                    target = key;
                }
            }
        }
        return target;
    }

}

package root.model;

import java.util.ArrayList;
import java.util.HashMap;

public final class Trail {
    private HashMap<Integer, Point> head; // current point of head
//    private ArrayList<Point> head; // current point of head
    private HashMap<Integer, Point> food;
    private HashMap<Integer, Short> length;
    private HashMap<Integer, Integer> score;
    private int lifespan;

    public Trail(Point head, Point food, Short length) {
        this.head = new HashMap<>();
//        this.head = new ArrayList<>();
        this.food = new HashMap<>();
        this.length = new HashMap<>();
        score = new HashMap<>();

        lifespan = 0;
//        this.head.add(0, head);
        this.head.put(0, head);
        this.food.put(0, food);
        this.length.put(0, length);
        score.put(0, 0);
    }

    public void removeFrom(Integer step) {
        removeKeys(step, head);

        removeKeys(step, food);
        removeKeys(step, length);
        removeKeys(step, score);
        lifespan = step;
    }
    private <V> void removeKeys(Integer step, HashMap<Integer, V> obj) {
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
    public int getLifespan() { return lifespan;}
    public void setLifespan(int step) { lifespan = step;}
    public void setFood(int step, Point point) {
        food.put(step, point);
    }
    public Point getFood(int step) {
        return food.get(getKey(step, food));
    }
    public void setHead(int step, Point point) {
        head.put(step, point);
    }
    public Point getHead(int step) {
        return head.get(step);
    }
    public void setLength(int step, Short length) {
        this.length.put(step, length);
    }
    public short getLength(int step) {
        return length.get(getKey(step, length));
    }
    public void setScore(int step, int score) {
        this.score.put(step, score);
    }
    public int getScore(int step) {
        return score.get(getKey(step, score));
    }

}

//class packet{
//    private Food food;
//    private int length;
//    private int score;
//}
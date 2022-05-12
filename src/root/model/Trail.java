package root.model;

import java.util.HashMap;
import java.util.LinkedList;

public final class Trail {
    private final HashMap<Integer, Point> head; // current point of head
    private final HashMap<Integer, LinkedList<Food>> foodList;
    private final HashMap<Integer, Short> length;
    private final HashMap<Integer, Integer> score;
    private int lifespan;

    public Trail(Point head, LinkedList<Food> foodL, Number length) {
//    public Trail(Point head, Point foodList, Short length) {
        this.head = new HashMap<>();
//        this.head = new ArrayList<>();
        this.foodList = new HashMap<>();
        this.length = new HashMap<>();
        score = new HashMap<>();

        lifespan = 0;
        this.head.put(0, head);
        this.foodList.put(0, foodL);
        this.length.put(0, length.shortValue());
        score.put(0, 0);
    }

    public void removeFrom(Integer step) {
        removeKeys(step, head);

        removeKeys(step, foodList);
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
            if (key.equals(step)) break;
//            else if (key > step) ;
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
    public void setFood(int step, LinkedList<Food> point) {
        foodList.put(step, point);
    }
    public LinkedList<Food> getFood(int step) {
        return foodList.get(getKey(step, foodList));
    }
    public void setHead(int step, Point point) {
        head.put(step, point);
    }
    public Point getHead(int step) {
        return head.get(step);
    }
    public void setLength(int step, Number length) {
        this.length.put(step, length.shortValue());
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
//    private Food foodList;
//    private int length;
//    private int score;
//}
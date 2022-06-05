package root.model;

import root.model.Food.Food;
import root.model.Point.RectPoint;

import java.util.HashMap;
import java.util.LinkedList;

public final class Trail {
    private final HashMap<Integer, RectPoint> head; // current point of head
    private final HashMap<Integer, LinkedList<Food>> foodList;
    private final HashMap<Integer, Short> length;
    private final HashMap<Integer, Integer> score;
    private int lifespan;

    public Trail(RectPoint head, LinkedList<Food> foodL, Number length) {
        this.head = new HashMap<>();
        this.foodList = new HashMap<>();
        this.length = new HashMap<>();
        score = new HashMap<>();

        lifespan = 0;
        this.head.put(0, head);
        addFoodList(0, foodL);
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
    public void setLifespan(Integer step) { lifespan = step;}
    public void addFoodList(Integer step, LinkedList<Food> list) {
        LinkedList<Food> temp = new LinkedList<>();
        for (Food food: list) {
            temp.add(new Food(food));
        }
        foodList.put(step, temp);
    }
    public LinkedList<Food> getFoodList(int step) {
        return foodList.get(getKey(step, foodList));
    }
    public void addHead(int step, RectPoint point) {
        head.put(step, point);
    }
    public RectPoint getHead(int step) {
        return head.get(step);
    }
    public void addLength(int step, Number length) {
        this.length.put(step, length.shortValue());
    }
    public short getLength(int step) {
        return length.get(getKey(step, length));
    }
    public void addScore(int step, int score) {
        this.score.put(step, score);
    }
    public int getScore(int step) {
        return score.get(getKey(step, score));
    }

}

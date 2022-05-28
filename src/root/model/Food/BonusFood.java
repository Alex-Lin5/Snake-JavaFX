package root.model.Food;

import root.model.Point.RectPoint;

public class BonusFood extends Food {
    public BonusFood(RectPoint point){
        super(point);
        serialNum = 1;
        score = 300;
    }
}

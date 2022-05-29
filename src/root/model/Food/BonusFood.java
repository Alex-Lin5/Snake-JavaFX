package root.model.Food;

import root.model.Point.RectPoint;
import root.view.Color;

public class BonusFood extends Food {
    public BonusFood(RectPoint point){
        super(point);
        serialNum = 1;
        score = 300;
        color = Color.GREEN;
    }
}

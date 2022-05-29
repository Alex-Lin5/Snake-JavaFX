package root.model.Food;

import root.model.Point.RectPoint;
import root.view.Color;

public class BaseFood extends Food{
//    BaseFood() {}
    public BaseFood(RectPoint point){
        super(point);
        serialNum = 0;
        score = 100;
        color = Color.WHEAT;
    }
}

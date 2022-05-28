package root.model.Food;

import root.model.Point.RectPoint;

public class BaseFood extends Food{
//    BaseFood() {}
    public BaseFood(RectPoint point){
        super(point);
        serialNum = 0;
        score = 100;
    }
}

package root.model.Food;

import root.model.Point.RectPoint;

public class Bonus extends Base{
    final byte serialNum = 1;
    final int score = 300;
    Bonus() {}
    Bonus(RectPoint point) {
        super(point);
    }
}

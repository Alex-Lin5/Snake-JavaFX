package root.model.Food;

import root.model.Point.RectPoint;

public class Food {
    final byte serialNum;
    final int score;
    RectPoint point;
    Base foodBase;
    Bonus foodBonus;
//    Food() {}
    Food(Number num, RectPoint point) {
        byte numB = num.byteValue();
        serialNum = num.byteValue();
        score = 0;
        this.point = point;
        if (numB == 0)
            foodBase = new Base(point);
        else if (numB == 1)
            foodBonus = new Bonus(point);
    }
    public int getScore() {
        return score;
    }
    public void setPoint(RectPoint pt) { point = pt;}
    public RectPoint getPoint() { return point;}
    public byte getSerialNum() { return serialNum;}

}

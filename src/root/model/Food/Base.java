package root.model.Food;

import root.model.Point.RectPoint;

public class Base {
    final byte serialNum = 0;
    final int score = 100;
    RectPoint point;
    Base() {}
    Base(RectPoint point) {
        this.point = point;
    }
    public int getScore() {
        return score;
    }
    public void setPoint(RectPoint pt) { point = pt;}
    public RectPoint getPoint() { return point;}
    public byte getSerialNum() { return serialNum;}
}

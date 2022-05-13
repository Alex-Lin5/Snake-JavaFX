package root.model;

import root.model.Point.RectPoint;

public enum Food {
    Base(0, 100),
    Bonus(1, 300);
    private final byte serialNum;
    private final int score;
    private RectPoint point;
    Food(Number num, int score) {
        serialNum = num.byteValue();
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public void setPoint(RectPoint pt) { point = pt;}
    public RectPoint getPoint() { return point;}
    public byte getSerialNum() { return serialNum;}
}

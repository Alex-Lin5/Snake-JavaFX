package root.model.Food;

import root.model.Point.RectPoint;
import root.view.Color;

public class Food {
    byte serialNum;
    int score;
    RectPoint point;
    Color color;
    public Food(Food fd) {
        this.serialNum = fd.serialNum;
        this.score = fd.score;
        this.point = fd.point;
        this.color = fd.color;
    }
    public Food(RectPoint point) { this.point = point;}

    public Color getColor() { return color;}
    public int getScore() {
        return score;
    }
    public void setPoint(RectPoint pt) { point = pt;}
    public RectPoint getPoint() { return point;}
    public byte getSerialNum() { return serialNum;}
    @Override public String toString(){
        String out;
        out = String.format("%d.%d ", serialNum, score);
        out += String.valueOf(point);
        return out;
    }

}

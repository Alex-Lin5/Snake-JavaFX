package root.model;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import static root.model.Board.SIZE;

public class Point {
    private final Number x;    // The X coordinate
    private final Number y;    // The Y coordinate
    enum slide { X, Y, XY, NONE, WRONG};
    enum direction { POS, NEG, STILL, WRONG};
    public Point(Number x, Number y) {
        this.x = x;
        this.y = y;
    }

    public Number getX(){ return x;}
    public Number getY(){ return y;}

    @Override
    public boolean equals(@NotNull Object pointOther) {
        if (!(pointOther instanceof Point)) return false;
        Point point = (Point) pointOther;
        if (point.getX().intValue() == x.intValue() &
                point.getY().intValue() == y.intValue())
            return true;
        else return false;
    }

    @Override
    public int hashCode(){
        int result = x.intValue() << 16 + y.intValue();
        return result;
    }

    public Number distanceTo(Point newOne) {
        Number distance;
        distance = Math.abs(newOne.getX().intValue() - x.intValue()) +
                Math.abs(newOne.getY().intValue() - y.intValue());
        return distance;
    }
    public Point getPointOnGrid() {
        return new Point (
            Math.floor(x.doubleValue()/SIZE)*SIZE ,
                Math.floor(y.doubleValue()/SIZE)*SIZE);
    }
    public Point getPointNearGrid() {
        Point point = new Point(
            (int) Math.round(x.doubleValue()/SIZE)*SIZE,
                (int) Math.round(y.doubleValue()/SIZE)*SIZE);
        return point;
    }
    public boolean meetGrid() {
        Point point = new Point(x, y);
        if (point.equals(point.getPointNearGrid()))
            return true;
        else return false;
    }

    public boolean isAdjacentTo(Point point1) {
        if (point1.distanceTo(new Point(x, y)).intValue() <= SIZE)
            return true;
        else return false;
    }
    public direction directionTo(Point newOne) {
        Number xNew, yNew;
        xNew = newOne.getX(); yNew = newOne.getY();
        if (x.intValue() == xNew.intValue() &
            y.intValue() == yNew.intValue()) return direction.STILL;
        if ((x.intValue() == xNew.intValue() &
            y.intValue() < yNew.intValue()) |
            x.intValue() < xNew.intValue() &
            y.intValue() == yNew.intValue())
            return direction.POS;
        if ((x.intValue() == xNew.intValue() &
            y.intValue() > yNew.intValue()) |
            x.intValue() > xNew.intValue() &
            y.intValue() == yNew.intValue())
            return direction.NEG;
        return direction.WRONG;
    }
    public slide slideInXY(Point point2) {
        Number xNew, yNew;
        xNew = point2.getX(); yNew = point2.getY();
        if (x.intValue() == xNew.intValue() &
            y.intValue() == yNew.intValue())
            return slide.NONE; // no slide
        if (x.intValue() != xNew.intValue() &
            y.intValue() == yNew.intValue())
            return slide.X; // X slide
        if (x.intValue() == xNew.intValue() &
            y.intValue() != yNew.intValue())
            return slide.Y; // Y slide
        if (x.intValue() != xNew.intValue() &
            y.intValue() != yNew.intValue())
            return slide.XY; // XY slide
        return slide.WRONG; // error
    }
}

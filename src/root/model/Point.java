package root.model;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import static root.model.Board.SIZE;

public class Point extends LinkedList {
    private final double x;    // The X coordinate
    private final double y;    // The Y coordinate
    enum slide { X, Y, XY, NONE, WRONG};
    enum direction { POS, NEG, STILL, WRONG};
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){ return (double) x;}
    public double getY(){ return (double) y;}
    public double distanceTo(Point newOne) {
        double distance;
        distance = Math.abs(newOne.getX() - x) +
                Math.abs(newOne.getY() - y);
        return distance;
    }
    public Point getPointOnGrid() {
        return new Point (Math.floor(x/SIZE)*SIZE , Math.floor(y/SIZE)*SIZE);
    }
    public Point getPointNearGrid() {
        Point point = new Point(
            (int) Math.round(x/SIZE)*SIZE,
                (int) Math.round(y/SIZE)*SIZE);
        return point;
    }
    public boolean meetGrid() {
        Point point = new Point(x, y);
        if (point.equals(point.getPointNearGrid()))
            return true;
        else return false;
    }

//    @Override
    public boolean equals(@NotNull Point point1) {
        if (point1.getX() == x & point1.getY() == y)
            return true;
        else return false;
    }
//    public boolean isEqualTo(Point point1) {
//        if (point1.getX() == x & point1.getY() == y)
//            return true;
//        else return false;
//    }
    public boolean isDuplicateIn (LinkedList<Point> list, int begin, int end) {
        Point point = new Point(x, y);
        for (int i=begin; i<end; i++) {
            if (point.equals(list.get(i)))
                return true;
        }
        return false;
    }

    public boolean isAdjacentTo(Point point1) {
        if (point1.distanceTo(new Point(x, y)) <= SIZE)
            return true;
        else return false;
    }
    public direction directionTo(Point newOne) {
        double xNew, yNew;
        xNew = newOne.getX(); yNew = newOne.getY();
        if (x == xNew & y == yNew) return direction.STILL;
        if ((x == xNew & y < yNew) |
                x < xNew & y == yNew)
            return direction.POS;
        if ((x == xNew & y > yNew) |
                x > xNew & y == yNew)
            return direction.NEG;
        return direction.WRONG;
    }
    public slide slideInXY(Point point2) {
        double xNew, yNew;
        xNew = point2.getX(); yNew = point2.getY();
        if (x == xNew & y == yNew) return slide.NONE; // no slide
        if (x != xNew & y == yNew) return slide.X; // X slide
        if (x == xNew & y != yNew) return slide.Y; // Y slide
        if (x != xNew & y != yNew) return slide.XY; // XY slide
        return slide.WRONG; // error

    }
}

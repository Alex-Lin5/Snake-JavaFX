package root.model;

import java.util.LinkedList;

import static root.model.Board.SIZE;

public class Point {
    private final double x;    // The X coordinate
    private final double y;    // The Y coordinate
    enum slide { X, Y, XY, NONE, WRONG};
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){ return (double) x;}
    public double getY(){ return (double) y;}
    public int getXGrid() { return (int) Math.round(x/SIZE);}
    public int getYGrid() { return (int) Math.round(y/SIZE);}
    public boolean meetGrid() {
        Point point = new Point(x, y);
        if (x == point.getXGrid()*SIZE &
                y == point.getYGrid()*SIZE)
            return true;
        else return false;
    }

    public Point getPointOnGrid() {
        return new Point (Math.floor(x/SIZE)*SIZE , Math.floor(y/SIZE)*SIZE);
    }
    public boolean isEqualTo(Point point1) {
        if (point1.getX() == x & point1.getY() == y)
            return true;
        else return false;
    }
    public boolean isDuplicateIn (LinkedList<Point> list, int begin, int end) {
        Point point = new Point(x, y);
        for (int i=begin; i<end; i++) {
            if (point.isEqualTo(list.get(i)))
                return true;
        }
        return false;
    }

    public int directionTo(Point newOne) {
        double xNew, yNew;
        xNew = newOne.getX(); yNew = newOne.getY();
        if (x == xNew & y == yNew) return 0;
        if ((x == xNew & y < yNew) |
            x < xNew & y == yNew)
            return 1;
        if ((x == xNew & y > yNew) |
                x > xNew & y == yNew)
            return -1;
        return 100;
    }
    public double distanceTo(Point newOne) {
        double distance;
        distance = Math.abs(newOne.getX() - x) +
                Math.abs(newOne.getY() - y);
        return distance;
    }
    public boolean isAdjacentTo(Point point1) {
        if (point1.distanceTo(new Point(x, y)) <= 10)
            return true;
        else return false;
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

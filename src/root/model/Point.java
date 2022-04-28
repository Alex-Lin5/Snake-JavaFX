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
//    public static boolean Equal(Point point1, Point point2) {
//        if (point1.getX() == point2.getX() & point1.getY() == point2.getY())
//            return true;
//        else return false;
//    }
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

    public static int whichDirection(Point preOne, Point newOne) {
        double x, y, xNew, yNew;
        x = preOne.getX(); y = preOne.getY();
        xNew = newOne.getX(); yNew = newOne.getY();
        if (x == xNew & y == yNew) return 0;
        if ((x == xNew & y < yNew) |
            x < xNew & y == yNew)
            return 1;
        if ((x == xNew & y > yNew) |
                x > xNew & y == yNew)
            return -1;
        return 11;
    }
//    public static double DiffenceBetween(Point preOne, Point newOne) {
//        double distance;
//        distance = newOne.getX() - preOne.getX() +
//                newOne.getY() - preOne.getY();
//        return distance;
//    }
    public static double DistanceBetween(Point preOne, Point newOne) {
        double distance;
        distance = Math.abs(newOne.getX() - preOne.getX()) +
                Math.abs(newOne.getY() - preOne.getY());
        return distance;
    }
    public static boolean onAdjacentGrid(Point point1, Point point2) {
        if (Point.DistanceBetween(point1, point2) <= 10)
            return true;
        else return false;
    }
    public static slide slideXY(Point point1, Point point2) {
        double x, y, xNew, yNew;
        x = point1.getX(); y = point1.getY();
        xNew = point2.getX(); yNew = point2.getY();
//        if (x == xNew & y == yNew) return 0; // no slide
//        if (x != xNew & y == yNew) return 1; // X slide
//        if (x == xNew & y != yNew) return 2; // Y slide
//        if (x != xNew & y != yNew) return 3; // XY slide
//        return -1; // error
        if (x == xNew & y == yNew) return slide.NONE; // no slide
        if (x != xNew & y == yNew) return slide.X; // X slide
        if (x == xNew & y != yNew) return slide.Y; // Y slide
        if (x != xNew & y != yNew) return slide.XY; // XY slide
        return slide.WRONG; // error

    }
}

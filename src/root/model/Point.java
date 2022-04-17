package root.model;

import static root.model.Board.SIZE;

public class Point {
    private double x;    // The X coordinate
    private double y;    // The Y coordinate
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){ return (double) x;}
    public double getY(){ return (double) y;}
    public int GetXGrid() { return (int) Math.round(x/SIZE);}
    public int GetYGrid() { return (int) Math.round(y/SIZE);}
    public void setXY(double x, double y) {
        this.x = x; this.y = y;}
    public void setPoint(Point setOne) {
        this.x = setOne.getX();
        this.y = setOne.getY();
    }
    public static Point addPoint(Point newPoint) {
        return new Point(newPoint.getX(), newPoint.getY());
    }
    public static boolean Equal(Point point1, Point point2) {
        if (point1.getX() == point2.getX() & point1.getY() == point2.getY())
            return true;
        else return false;
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
    public static int XYslide(Point point1, Point point2) {
        double x, y, xNew, yNew;
        x = point1.getX(); y = point1.getY();
        xNew = point2.getX(); yNew = point2.getY();
        if (x == xNew & y == yNew) return 0; // no slide
        if (x != xNew & y == yNew) return 1; // X slide
        if (x == xNew & y != yNew) return 2; // Y slide
        if (x != xNew & y != yNew) return 3; // XY slide
        return -1; // error

    }
}

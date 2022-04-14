package root.model;

public class Point {
    private final double x;    // The X coordinate
    private final double y;    // The Y coordinate
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
//        return x, y;
    }

    public double getX(){ return (double) x;}
    public double getY(){ return (double) y;}
//    public Point getPoint() { return point;}
}

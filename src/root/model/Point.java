package root.model;

import static root.model.Board.SIZE;

public class Point {
    private final double x;    // The X coordinate
    private final double y;    // The Y coordinate
//    private int cols, rows;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
//        double temp = -1.0;
//        rows = (int) Math.floor(y/SIZE);
//        cols = (int) Math.floor(x/SIZE);
//        return x, y;
    }

    public double getX(){ return (double) x;}
    public double getY(){ return (double) y;}
    public int getCols() { return (int) Math.floor(x/SIZE);}
    public int getRows() { return (int) Math.floor(y/SIZE);}
//    public int getCols() { return cols;}
//    public int getRows() { return rows;}

//    public Point getPoint() { return point;}
}

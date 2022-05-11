package root.model;

import org.jetbrains.annotations.NotNull;

import static root.model.Board.SIZE;

public class Point {
    private final int x;    // The X coordinate
    private final int y;    // The Y coordinate
    enum slide { X, Y, XY, NONE, WRONG}
    enum direction { POS, NEG, STILL, WRONG}

    public int getX(){ return x;}
    public int getY(){ return y;}
    public Point(final Integer x, final Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(@NotNull Object pointOther) {
        if (!(pointOther instanceof Point)) return false;
        Point point = (Point) pointOther;
        return point.getX() == x & point.getY() == y;
    }
    @Override
    public int hashCode(){
        int result = x << 16 + y;
        return result;
    }
    public int distanceTo(Point pointNew) {
        int distance;
        distance = Math.abs(pointNew.getX() - x) +
                Math.abs(pointNew.getY() - y);
        return distance;
    }
    public Point getPointOnGrid() {
        Number xNew = Math.floor(x/SIZE)*SIZE;
        Number yNew = Math.floor(y/SIZE)*SIZE;
        return new Point(xNew.intValue(), yNew.intValue());
    }
    public Point getPointNearGrid() {
        Point point = new Point(
            Math.round(x/SIZE)*SIZE,
            Math.round(y/SIZE)*SIZE);
        return point;
    }
    public boolean meetGrid() {
        Point point = new Point(x, y);
        return point.equals(point.getPointNearGrid());
    }

    public boolean isAdjacentTo(Point pointNew) {
        return pointNew.distanceTo(new Point(x, y)) <= SIZE;
    }
    public direction directionTo(Point pointNew) {
        int xNew, yNew;
        xNew = pointNew.getX(); yNew = pointNew.getY();
        if (x == xNew & y == yNew) return direction.STILL;
        if ((x == xNew & y < yNew) |
                x < xNew & y == yNew)
            return direction.POS;
        if ((x == xNew & y > yNew) |
                x > xNew & y == yNew)
            return direction.NEG;
        return direction.WRONG;
    }
    public slide slideInXY(Point pointNew) {
        int xNew, yNew;
        xNew = pointNew.getX(); yNew = pointNew.getY();
        if (x == xNew & y == yNew) return slide.NONE; // no slide
        if (x != xNew & y == yNew) return slide.X; // X slide
        if (x == xNew & y != yNew) return slide.Y; // Y slide
        if (x != xNew & y != yNew) return slide.XY; // XY slide
        return slide.WRONG; // error

    }
}
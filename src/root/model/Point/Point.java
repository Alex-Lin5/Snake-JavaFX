package root.model.Point;

import org.jetbrains.annotations.NotNull;

public class Point {
    final int x;    // The X coordinate
    final int y;    // The Y coordinate
//    enum slide { X, Y, XY, NONE, WRONG}
//    enum direction { POS, NEG, STILL, WRONG}

    public int getX(){ return x;}
    public int getY(){ return y;}
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(@NotNull Object pointOther) {
        if (!(pointOther instanceof Point point)) return false;
        return point.getX() == x & point.getY() == y;
    }
    @Override
    public int hashCode(){
        return x << 16 + y;
    }
//    public int distanceTo(Point pointNew) {
//        int distance;
//        distance = Math.abs(pointNew.getX() - x) +
//                Math.abs(pointNew.getY() - y);
//        return distance;
//    }
//    public Point getPointOnGrid() {
//        float xF = (float) x;
//        float yF = (float) y;
//        Number xNew = Math.floor(xF/SIZE)*SIZE;
//        Number yNew = Math.floor(yF/SIZE)*SIZE;
//        return new Point(xNew.intValue(), yNew.intValue());
//    }
//    public Point getPointNearGrid() {
//        return new Point(
//            Math.round(x/SIZE)*SIZE,
//            Math.round(y/SIZE)*SIZE);
//    }
//    public boolean meetGrid() {
//        Point point = new Point(x, y);
//        return point.equals(point.getPointNearGrid());
//    }
//
//    public boolean isAdjacentTo(Point pointNew) {
//        return pointNew.distanceTo(new Point(x, y)) <= SIZE;
//    }
//    public direction directionTo(Point pointNew) {
//        int xNew, yNew;
//        xNew = pointNew.getX(); yNew = pointNew.getY();
//        if (x == xNew & y == yNew) return direction.STILL;
//        if ((x == xNew & y < yNew) |
//                x < xNew & y == yNew)
//            return direction.POS;
//        if ((x == xNew & y > yNew) |
//                x > xNew & y == yNew)
//            return direction.NEG;
//        return direction.WRONG;
//    }
//    public slide slideInXY(Point pointNew) {
//        int xNew, yNew;
//        xNew = pointNew.getX(); yNew = pointNew.getY();
//        if (x == xNew & y == yNew) return slide.NONE; // no slide
//        if (x != xNew & y == yNew) return slide.X; // X slide
//        if (x == xNew & y != yNew) return slide.Y; // Y slide
//        if (x != xNew & y != yNew) return slide.XY; // XY slide
//        return slide.WRONG; // error
//
//    }
}


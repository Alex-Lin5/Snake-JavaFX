package root.model.Point;

import static root.model.Board.SIZE;

public final class RectPoint extends BasePoint {
    public enum slide { X, Y, XY, NONE, WRONG}
    public enum direction { POS, NEG, STILL, WRONG}

    public RectPoint(int x, int y) {
        super(x, y);
    }
    public int distanceTo(RectPoint pointNew) {
        int distance;
        distance = Math.abs(pointNew.getX() - x) +
                Math.abs(pointNew.getY() - y);
        return distance;
    }
    public RectPoint getPointOnGrid() {
        float xF = (float) x;
        float yF = (float) y;
        Number xNew = Math.floor(xF/SIZE)*SIZE;
        Number yNew = Math.floor(yF/SIZE)*SIZE;
        return new RectPoint(xNew.intValue(), yNew.intValue());
    }
    public RectPoint getPointNearGrid() {
        return new RectPoint(
                Math.round(x/SIZE)*SIZE,
                Math.round(y/SIZE)*SIZE);
    }
    public boolean meetGrid() {
        RectPoint point = new RectPoint(x, y);
        return point.equals(point.getPointNearGrid());
    }

    public boolean isAdjacentTo(RectPoint pointNew) {
        return pointNew.distanceTo(new RectPoint(x, y)) <= SIZE;
    }
    public direction directionTo(RectPoint pointNew) {
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
    public slide slideInXY(RectPoint pointNew) {
        int xNew, yNew;
        xNew = pointNew.getX(); yNew = pointNew.getY();
        if (x == xNew & y == yNew) return slide.NONE; // no slide
        if (x != xNew & y == yNew) return slide.X; // X slide
        if (x == xNew & y != yNew) return slide.Y; // Y slide
        if (x != xNew & y != yNew) return slide.XY; // XY slide
        return slide.WRONG; // error

    }
}
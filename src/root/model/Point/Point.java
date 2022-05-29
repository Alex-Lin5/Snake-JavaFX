package root.model.Point;

import org.jetbrains.annotations.NotNull;

public class Point {
    final int x;    // The X coordinate
    final int y;    // The Y coordinate

    public int getX(){ return x;}
    public int getY(){ return y;}
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    public Point(final Point pt){
        x = pt.x;
        y = pt.y;
    }

    @Override
    public boolean equals(@NotNull Object object) {
        if (object == this) return true;
        if (!(object instanceof Point pt)) return false;
        return pt.getX() == x & pt.getY() == y;
    }
    @Override
    public int hashCode(){
        return x << 16 | y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
package root.model.Point;

import org.jetbrains.annotations.NotNull;

public class BasePoint {
    final int x;    // The X coordinate
    final int y;    // The Y coordinate

    public int getX(){ return x;}
    public int getY(){ return y;}
    public BasePoint(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(@NotNull Object object) {
        if (!(object instanceof BasePoint point)) return false;
        return point.getX() == x & point.getY() == y;
    }
    @Override
    public int hashCode(){
        return x << 16 + y;
    }
}
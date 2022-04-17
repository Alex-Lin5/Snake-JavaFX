package root.model;

import java.util.LinkedList;

import static root.model.Board.SIZE;

public class Snake {
//    public class Snake extends Point{
    private float speed;
    private int length;

    private LinkedList<Point> body;
    private Point head, tail;
    private int headDirection, tailDirection;
    private double distance;

    private int xVelocity;
    private int yVelocity;
    private boolean turned;
    private boolean moving;

    private final int step = SIZE;

    public Snake(Point SpawnPoint) {
        xVelocity = 0;
        yVelocity = 0;
        headDirection = 0;
        tailDirection = 0;
//        speed = 80f; // from 0 to 1000, ms to traverse a block
        speed = 20f; // from 0 to 10, number of blocks traverse in 1s
        length = 4;
        turned = false;
        moving = false;

        head = Point.addPoint(SpawnPoint);
        tail = Point.addPoint(SpawnPoint);
        body = new LinkedList<Point>();
//        body.addFirst(head);
        body.addFirst(Point.addPoint(SpawnPoint));
        for(int i=1; i<length; i++){
            body.add(i, Point.addPoint(SpawnPoint));
        }
    }

    public LinkedList<Point> getBody() { return body;}
    public Point getHead() { return head;}
    public Point getTail() { return tail;}
    public int getLength() { return length;}
    public float getSpeed() {
        return speed;
    }
    public double getEndDistance() { return distance;}

    public void move(int width, int height) {
        double x, y, xNew, yNew, head2body0, tail2bodyr1;
        x = head.getX();
        y = head.getY();
        xNew = x + xVelocity;
        yNew = y + yVelocity;
        Point newPoint = new Point(xNew, yNew);

        turned = isTurned(newPoint);
        moving = (!isStatic());
        headDirection = Point.whichDirection(body.get(1), body.get(0));
        tailDirection = Point.whichDirection(body.get(length-1), body.get(length-2));
        distance = Point.DistanceBetween(body.get(0), newPoint);

        if (xNew<0) xNew = width-step;
        if (xNew>width-step) xNew = 0;
        if (yNew<0) yNew = height-step;
        if (yNew>height-step) yNew = 0;
        Point wrappedPoint = new Point(xNew, yNew);
        boolean wrapped = true;
        if (!Point.Equal(wrappedPoint, newPoint))
            newPoint = wrappedPoint;
        else
            wrapped = false;

        if (!bodyStacked()) { // snake is dynamic
            if (turned) {
                if (Point.XYslide(head, body.get(0)) == 1)
                    head.setXY(body.get(0).getX() + Point.whichDirection(body.get(0), head)*distance,
                            body.get(0).getY());
                if (Point.XYslide(head, body.get(0)) == 2)
                    head.setXY(body.get(0).getX(),
                            body.get(0).getY() + Point.whichDirection(body.get(0), head)*distance);
            }
            else head = newPoint;
            // tail part
            if (Point.onAdjacentGrid(body.get(length-1), body.get(length-2))) {
                if (Point.XYslide(body.get(length-1), body.get(length-2)) == 1)
                    tail.setXY(body.get(length-1).getX() + tailDirection*distance,
                            body.get(length-1).getY());
                if (Point.XYslide(body.get(length-1), body.get(length-2)) == 2)
                    tail.setXY(body.get(length-1).getX(),
                            body.get(length-1).getY() + tailDirection*distance);
            }
            else {
                if (Point.XYslide(body.get(length-1), body.get(length-2)) == 1)
                    tail.setXY(body.get(length-1).getX() - tailDirection*distance,
                            body.get(length-1).getY());
                if (Point.XYslide(body.get(length-1), body.get(length-2)) == 2)
                    tail.setXY(body.get(length-1).getX(),
                            body.get(length-1).getY() - tailDirection*distance);
            }
            // body part
            if (meetGrid(head)) {
                body.addFirst(Point.addPoint(head));
                body.removeLast();
            }
        }
        else { // snake body is stacked, initial state
            tail.setPoint(tail);
            head = newPoint;
            // body part
            if (meetGrid(newPoint)) {
                body.addFirst(newPoint);
                body.removeLast();
            }
        }

        head2body0 = Point.DistanceBetween(body.get(0), head);
        tail2bodyr1 = Point.DistanceBetween(body.get(length-1), tail);
        int tempp = 1;
    }
    private boolean meetGrid(Point newPoint) {
        double x, y;
        x = newPoint.getX(); y = newPoint.getY();
        if (x == newPoint.GetXGrid()*step &
            y == newPoint.GetYGrid()*step)
            return true;
        else return false;
    }
    private void tailShift() {}
    private boolean bodyStacked() {
        boolean stacked = false;
        for(int i=0; i<length-1; i++) {
            stacked |= Point.Equal(body.get(i), body.get(i+1));
        }
        return stacked;
    }
    private boolean isTurned(Point newOne) {
        if ((Point.XYslide(head , newOne) !=
            Point.XYslide(body.get(0), head)) &
            Point.XYslide(body.get(0), head) != 0)
            return true;
        else return false;
    }
    private boolean isStatic() {
        if ((xVelocity == 0 & yVelocity == 0) |
            (headDirection == 0 & tailDirection == 0))
            return true;
        else return false;
    }
    public void setStatic() {
        xVelocity = 0;
        yVelocity = 0;
    }
    public void setUp() {
        if (yVelocity == 1 && length > 1) return;
        xVelocity = 0;
        yVelocity = -1;
    }

    public void setDown() {
        if (yVelocity == -1 && length > 1) return;
        xVelocity = 0;
        yVelocity = 1;
    }

    public void setLeft() {
        if (xVelocity == 1 && length > 1) return;
        xVelocity = -1;
        yVelocity = 0;
    }

    public void setRight() {
        if (xVelocity == -1 && length > 1) return;
        xVelocity = 1;
        yVelocity = 0;
    }
}

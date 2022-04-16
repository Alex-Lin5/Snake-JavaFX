package root.model;

import java.util.LinkedList;

import static root.model.Board.SIZE;

public class Snake {
//    public class Snake extends Point{
    private float speed;
    private int length;

    private LinkedList<Point> body;
    private Point head, tail;

    private int xVelocity;
    private int yVelocity;
    private final int step = SIZE;

    public Snake(Point SpawnPoint) {
        xVelocity = 0;
        yVelocity = 0;
        speed = 0.3f;
        length = 4;

        head = SpawnPoint;
        tail = SpawnPoint;
        body = new LinkedList<Point>();
        body.addFirst(head);
        Point temp = new Point(head.getX(), head.getY());
        for(int i=1; i<length; i++){
//            temp = new Point(temp.getX()-step, temp.getY());
            temp = new Point(temp.getX(), temp.getY());
            body.addLast(temp);
        }
    }

    public LinkedList<Point> getBody() { return body;}
    public Point getHead() { return head;}
    public Point getTail() { return tail;}
    public int getLength() { return length;}
    public float getSpeed() {
        return speed;
    }

    public void move(int width, int height) {
        double x, y, xNew, yNew, slide;
        x = head.getX();
        y = head.getY();
        xNew = x + xVelocity*speed*step;
        yNew = y + yVelocity*speed*step;
        slide = xNew - x + yNew - y;
        if (xNew<0) xNew = width;
        if (xNew>width) xNew = 0;
        if (yNew<0) yNew = height;
        if (yNew>height) yNew = 0;

        if (!isStatic()) {
            Point temp = new Point(xNew, yNew);
            if (isTurned(x, y, xNew, yNew)) {
                if (SlideXY(x, y, xNew, yNew) == 3) {
                    if (SlideXY(body.get(0).getX(), body.get(0).getY(),
                            body.get(1).getX(), body.get(1).getY()) == 1)
                        head = new Point(temp.GetXGrid()*step,
                                temp.GetYGrid()*step+slide);
                    if (SlideXY(body.get(0).getX(), body.get(0).getY(),
                            body.get(1).getX(), body.get(1).getY()) == 2)
                        head = new Point(temp.GetXGrid()*step+slide,
                                temp.GetYGrid()*step);
                }

            }
            else head = temp;
            // body part
            if (meetGrid(x, y, xNew, yNew)) {
                body.addFirst(new Point(head.GetXGrid()*step,
                        head.GetYGrid()*step));
                body.removeLast();
            }
            // tail part
            if (body.get(length-1).getX() ==
                    body.get(length-2).getX())
                tail = new Point(body.getLast().getX(),
                        body.getLast().getY() + slide);
            else if (body.get(length-1).getY() ==
                    body.get(length-2).getY())
                tail = new Point(body.getLast().getX() + slide,
                        body.getLast().getY());
        }
    }
    private int SlideXY(double x, double y,
                            double xNew, double yNew) {
        if (x == xNew & y == yNew) return 0; // no slide
        if (x != xNew & y == yNew) return 1; // X slide
        if (x == xNew & y != yNew) return 2; // Y slide
        if (x != xNew & y != yNew) return 3; // XY slide
        return -1; // error
    }
    private boolean meetGrid(double x, double y,
                             double xNew, double yNew) {
        Point previous = new Point(x, y);
        Point current = new Point(xNew, yNew);
        if (previous.GetYGrid() != current.GetYGrid() |
        previous.GetXGrid() != current.GetXGrid())
            return true;
        else
            return false;
    }
    private boolean isTurned(double x, double y,
                             double xNew, double yNew) {
        if (x != xNew & y != yNew) return true;
        else return false;
    }
    private boolean isStatic() {
        if (xVelocity == 0 & yVelocity == 0) return true;
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

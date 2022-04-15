package root.model;

import java.util.LinkedList;

import static root.model.Board.SIZE;

public class Snake {
//    public class Snake extends Point{
    private float speed;
    private int length;

    public LinkedList<Point> body;
    public Point head, tail;

    private int xVelocity;
    private int yVelocity;
    private final int step = SIZE;

    public Snake(Point SpawnPoint) {
        xVelocity = 0;
        yVelocity = 0;
        speed = 0.2f;
        length = 3;

//        Point head = SpawnPoint;
        head = SpawnPoint;
        body = new LinkedList<Point>();
        body.addFirst(head);
        Point temp = new Point(head.getX(), head.getY());
        for(int i=1; i<length; i++){
//            temp = new Point(temp.getX()-step, temp.getY());
            temp = new Point(temp.getX(), temp.getY());
            body.addLast(temp);

//        tail = body.get(0);
        }
    }

    public LinkedList<Point> getBody() { return body;}
    public Point getHead() { return head;}
    public float getSpeed() {
        return speed;
    }

    public void move(int width, int height) {
        double x, y, xNew, yNew;
        x = head.getX();
        y = head.getY();
        xNew = x + xVelocity*speed*step;
        yNew = y + yVelocity*speed*step;
        if (xNew<0) xNew = width;
        if (xNew>width) xNew = 0;
        if (yNew<0) yNew = height;
        if (yNew>height) yNew = 0;


        if (!isStatic()) {
            head = new Point(xNew, yNew);
            if (meetGrid(x, y, xNew, yNew)) {
                body.addFirst(head);
                body.removeLast();
            }
            else {
                body.set(0, head);
            }
        }
    }
    private boolean meetGrid(double x, double y,
                             double xNew, double yNew) {
        Point previous = new Point(x, y);
        Point current = new Point(xNew, yNew);
        if (previous.getRows() != current.getRows() |
        previous.getCols() != current.getCols())
            return true;
        else
            return false;
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

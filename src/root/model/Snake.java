package root.model;

import java.util.LinkedList;
import java.util.List;

import static root.model.PixelPad.SIZE;

public class Snake {
//    public class Snake extends Point{
    private float speed;
    private int length;

    public List<Point> body;
    public Point head, tail;

    private int xVelocity;
    private int yVelocity;
//    private double x, y;
    private final int step = SIZE;

//    private boolean xDirection;
//    private boolean yDirection;
//    private final boolean UP = false;
//    private final boolean DOWN = true;
//    private final boolean LEFT = false;
//    private final boolean RIGHT = true;

    public Snake(Point SpawnPoint) {
        xVelocity = 0;
        yVelocity = 0;
        speed = 0.2f;
        length = 3;

//        Point head = SpawnPoint;
        head = SpawnPoint;
        body = new LinkedList<>();
        body.add(head);
        Point temp = new Point(head.getX(), head.getY());
        for(int i=0; i<length-1; i++){
            temp = new Point(temp.getX()-step, temp.getY());
            body.add(temp);

        tail = body.get(0);
        }

//        spawn();

    }

//    public Point getHead() { return (Point) (x, y);}
    public float getSpeed() {
        return speed;
    }
//    public double getX() { return x;}
//    public double getY() { return y;}

//    private void spawn() {
//        x = 30;
//        y = 40;
//    }

    public void move(int width, int height) {
        double x, y;
        x = head.getX();
        y = head.getY();
        x = x + xVelocity*speed*step;
        y = y + yVelocity*speed*step;
        if (x<=0) x = width;
        if (x>=width) x = 0;
        if (y<=0) y = height;
        if (y>=height) y = 0;

        head = new Point(x, y);
        body.add(new Point(x, y));
        body.remove(0);
        tail = body.get(1);
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

package root.model;

import java.util.LinkedList;
import java.util.List;

import static root.view.PixelPad.SIZE;

public class Snake {
//    public class Snake extends Point{
    private float speed;
    private int length;

    public List<Point> body;
    public Point head;

    private int xVelocity;
    private int yVelocity;
    private double x, y;
    private final int step = SIZE;

    private boolean xDirection;
    private boolean yDirection;
    private final boolean UP = false;
    private final boolean DOWN = true;
    private final boolean LEFT = false;
    private final boolean RIGHT = true;

    public Snake(Point SpawnPoint) {
//        super(60, 100);
        head = SpawnPoint;
//        xDirection = RIGHT;
//        yDirection = UP;
        body = new LinkedList<>();
        body.add(head);

        xVelocity = 0;
        yVelocity = 0;
        speed = 0.4f;
        length = 3;
        spawn();

    }

//    public Point getHead() { return (Point) (x, y);}
    public float getSpeed() {
        return speed;
    }
//    public double getX() { return x;}
//    public double getY() { return y;}

    private void spawn() {
        x = 30;
        y = 40;
    }

    public void move() {
        x = x + xVelocity*speed*step;
        y = y + yVelocity*speed*step;
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

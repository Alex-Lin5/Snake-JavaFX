package root.model;

import java.util.LinkedList;

import static root.model.Board.SIZE;

public class Snake {
    private int serialNum;
    private String name;
    private float speed;
    private int length;
    private LinkedList<Point> body;
    private Point head, tail;
    private int headDirection, tailDirection;
    private double distance;
    private int score;

    private int xVelocity;
    private int yVelocity;
    private boolean turned;
    private boolean dead;
    private boolean moving;
//    private boolean resting;
    private boolean spawn;
    private boolean growing;

    private final int stride = SIZE;

    public Snake(Point SpawnPoint, int num, String name) {
        this.name = name;
        serialNum = num;
        score = 0;
        xVelocity = 0;
        yVelocity = 0;
        headDirection = 0;
        tailDirection = 0;
        speed = 20f; // from 0 to 50, number of blocks traverse in 1s
        length = 9;
        turned = false;
        dead = false;
        moving = false;
        spawn = true;
//        resting = true;
        growing = false;

        head = Point.addPoint(SpawnPoint);
        tail = Point.addPoint(SpawnPoint);
        body = new LinkedList<Point>();
        for(int i=0; i<length; i++){
            body.add(i, Point.addPoint(SpawnPoint));
        }
    }
//    public int[] getDirection() {
//        return new int[]{xVelocity, yVelocity};
//    }
    public boolean isMoving() {
        if ((xVelocity == 0 & yVelocity == 0))
            return false;
        else return true;
    }
    public void setDead() {
        this.dead = true;
        setStatic();
    }
    public String getName() { return name;}
    public boolean isGrowing() { return growing;}
    public int getSerialNum() { return serialNum;}
    public boolean isSpawned() { return spawn;}
    public void setTurned() { this.turned = true;}
    public void setAlive() { this.dead = false;}
    public boolean isDead() { return dead;}
    public void setScore(int scoreNew) { this.score = scoreNew;}
    public int getScore() { return score;}
    public LinkedList<Point> getBody() { return body;}
    public void setHead(Point point) { head = point;}
    public Point getHead() { return head;}
    public Point getTail() { return tail;}
    public void setTail(Point point) { tail = point;}
    public int getLength() { return length;}
    public void setLength(int num) { length = num;}
    public float getSpeed() { return speed;}
    public boolean isTurned() { return turned;}

    public void grow() {
        double x, y, xNew, yNew;
        growing = true;
        length += 1;
        x = head.getX();
        y = head.getY();
        xNew = x + xVelocity;
        yNew = y + yVelocity;
        body.addFirst(Point.addPoint(head));
        Point newPoint = new Point(xNew, yNew);
        head = newPoint;
    }
    public void move(int width, int height) {
        double x, y, xNew, yNew, head2body0, tail2bodyr1;
        boolean toMove;
        x = head.getX();
        y = head.getY();
        xNew = x + xVelocity;
        yNew = y + yVelocity;
        Point newPoint = new Point(xNew, yNew);

        headDirection = Point.whichDirection(body.get(1), body.get(0));
        tailDirection = Point.whichDirection(body.get(length-1), body.get(length-2));
        distance = Point.DistanceBetween(body.get(0), newPoint);

        growing = false;
//        moving = isMoving();
//        if (resting == moving & moving == true)
//            toMove = true;
//        else toMove = false;
//        resting = !moving;
        if (!moving & isMoving())
            toMove = true;
        else toMove = false;
        moving = isMoving();

        if (xNew<0) xNew = width-stride;
        if (xNew>width-stride) xNew = 0;
        if (yNew<0) yNew = height-stride;
        if (yNew>height-stride) yNew = 0;
        Point wrappedPoint = new Point(xNew, yNew);
//        boolean wrapped = true;
        if (!Point.Equal(wrappedPoint, newPoint))
            newPoint = wrappedPoint;
//        else
//            wrapped = false;

        if (!bodyStacked()) { // snake body is not stacked
            spawn = false;
            // tail part
            if (Point.onAdjacentGrid(body.get(length-1), body.get(length-2))) {
                tailShift(1);}
            else {
                tailShift(-1);}

            // body part
            if (!toMove & head.meetGrid()) {
                body.addFirst(Point.addPoint(head));
                body.removeLast();
                turned = false;
            }
            // head part
            if (turned) {
                if (Point.XYslide(head, body.get(0)) == 1)
                    head.setXY(body.get(0).getX() + Point.whichDirection(body.get(0), head)*distance,
                        body.get(0).getY());
                else if (Point.XYslide(head, body.get(0)) == 2)
                    head.setXY(body.get(0).getX(),
                        body.get(0).getY() + Point.whichDirection(body.get(0), head)*distance);
                else if (Point.XYslide(head, body.get(0)) == 0)
                    head = newPoint;
            }
            else head = newPoint;
        }
        else { // snake body is stacked, initial state
            // body part
            if (!toMove & head.meetGrid()) {
                body.addFirst(Point.addPoint(head));
                body.removeLast();
            }
            tail.setPoint(tail);
            head = newPoint;
        }
        head2body0 = Point.DistanceBetween(body.get(0), head);
        tail2bodyr1 = Point.DistanceBetween(body.get(length-1), tail);
    }
    private void tailShift(int Direction) {
        if (Point.XYslide(body.get(length-1), body.get(length-2)) == 1)
            tail.setXY(body.get(length-1).getX() +
                Direction*tailDirection*distance,
                    body.get(length-1).getY());
        if (Point.XYslide(body.get(length-1), body.get(length-2)) == 2)
            tail.setXY(body.get(length-1).getX(),
                body.get(length-1).getY() +
                        Direction*tailDirection*distance);
    }
    private boolean bodyStacked() {
        boolean stacked = false;
        for(int i=0; i<length-1; i++) {
            stacked |= Point.Equal(body.get(i), body.get(i+1));
        }
        return stacked;
    }
    public void setStatic() {
        xVelocity = 0;
        yVelocity = 0;
        moving = false;
//        resting = true;
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

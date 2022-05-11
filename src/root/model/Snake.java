package root.model;

import java.util.LinkedList;

import static root.model.Board.SIZE;

public class Snake {
    private final byte serialNum;
    private final String name;
    private final float speed;
    private short length;
    private final LinkedList<Point> body;
    private Point head, tail;
    private Point.direction headDirection, tailDirection;
    private int distance;
    private int score;

    private int xVelocity;
    private int yVelocity;
    private boolean turned;
    private boolean dead;
    private boolean moving;
    private boolean spawn;
    private boolean growing;

    public Snake(Point SpawnPoint, Byte num, String name) {
        this.name = name;
        serialNum = num;
        score = 0;
        xVelocity = 0;
        yVelocity = 0;
        speed = 20f; // from 0 to 50, number of blocks traverse in 1s
        length = 9;
        turned = false;
        dead = false;
        moving = false;
        spawn = true;
        growing = false;

        head = SpawnPoint;
        tail = SpawnPoint;
        body = new LinkedList<Point>();
        for(int i=0; i<length; i++){
            body.add(i, SpawnPoint);
        }
    }
    public void grow() {
        Number x, y, xNew, yNew;
        growing = true;
        length += 1;
        x = head.getX();
        y = head.getY();
        xNew = x.intValue() + xVelocity;
        yNew = y.intValue() + yVelocity;
        body.addFirst(head);
        head = new Point(xNew, yNew);
    }
    public void move(int width, int height) {
        Number x, y, xNew, yNew, head2body0, tail2bodyr1;
        boolean toMove;
        x = head.getX();
        y = head.getY();
        xNew = x.intValue() + xVelocity;
        yNew = y.intValue() + yVelocity;
        Point newPoint = new Point(xNew, yNew);

        headDirection = body.get(1).directionTo(body.get(0));
        tailDirection = body.get(length-1).directionTo(body.get(length-2));
        distance = body.get(0).distanceTo(newPoint).intValue();

        growing = false;
        toMove = !moving & isMoving();
        moving = isMoving();

        newPoint = wrapMove(xNew, yNew, width, height);

        // tail part
        if (!isBodyStacked()) { // snake body is not stacked
            spawn = false;
            if (body.get(length-1).isAdjacentTo(body.get(length-2))) {
                tailMove(Point.direction.POS);}
            else {
                tailMove(Point.direction.NEG);}
        }
//        else tail = tail;

        // body part
        if (!toMove & head.meetGrid()) {
            body.addFirst(head);
            body.removeLast();
            turned = false;
        }
        // head part
        headMove(newPoint);

        head2body0 = body.get(0).distanceTo(head);
        tail2bodyr1 = body.get(length-1).distanceTo(tail);
    }
    private Point wrapMove(Number xNew, Number yNew, int width, int height) {
        Point newPoint = new Point(xNew, yNew);
        int stride = SIZE;
        if (xNew.intValue() < 0) xNew = width- stride;
        if (xNew.intValue() > width- stride) xNew = 0;
        if (yNew.intValue() < 0) yNew = height- stride;
        if (yNew.intValue() > height- stride) yNew = 0;
        Point wrappedPoint = new Point(xNew, yNew);
        if (!wrappedPoint.equals(newPoint))
            newPoint = wrappedPoint;
        return newPoint;
    }
    private void headMove(Point newPoint) {
        if (turned) {
            Point.direction front = body.get(0).directionTo(head);
            int factor;
            if (front == Point.direction.POS)
                factor = 1;
            else if (front == Point.direction.NEG)
                factor = -1;
            else factor = 0;

            if (head.slideInXY(body.get(0)) == Point.slide.X)
                head = new Point(body.get(0).getX().intValue() + factor*distance,
                        body.get(0).getY());
            else if (head.slideInXY(body.get(0)) == Point.slide.Y)
                head = new Point(body.get(0).getX(),
                        body.get(0).getY().intValue() + factor*distance);
            else if (head.slideInXY(body.get(0)) == Point.slide.NONE)
                head = newPoint;
        }
        else head = newPoint;

    }
    private void tailMove(Point.direction split) {
        int factorS, factorT;
        if (split == Point.direction.POS)
            factorS = 1;
        else if (split == Point.direction.NEG)
            factorS = -1;
        else factorS = 0;
        if (tailDirection == Point.direction.POS)
            factorT = 1;
        else if (tailDirection == Point.direction.NEG)
            factorT = -1;
        else factorT = 0;
        
        if (body.get(length-1).slideInXY(body.get(length-2)) == Point.slide.X)
            tail= new Point(body.get(length-1).getX().intValue() +
                            factorS*factorT*distance,
                            body.get(length-1).getY());
        if (body.get(length-1).slideInXY(body.get(length-2)) == Point.slide.Y)
            tail = new Point(body.get(length-1).getX(),
                            body.get(length-1).getY().intValue() +
                                factorS*factorT*distance);
    }
    private boolean isBodyStacked() {
        boolean stacked = false;
        for(int i=0; i<length-1; i++) {
            stacked |= body.get(i).equals(body.get(i+1));
        }
        return stacked;
    }
    public void setStatic() {
        xVelocity = 0;
        yVelocity = 0;
        moving = false;
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
    public boolean isMoving() {
        return !(xVelocity == 0 & yVelocity == 0);
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
    public void setLength(short num) { length = num;}
    public float getSpeed() { return speed;}
    public boolean isTurned() { return turned;}
    
}

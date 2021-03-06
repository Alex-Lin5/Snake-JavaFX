package root.model;

import root.model.Point.RectPoint;
import root.view.Color;

import java.util.LinkedList;

import static root.model.Board.SIZE;

public final class Snake {
    private final byte serialNum;
    private final String name;
    private final Color color;
    private float speed;
    private int score;
    private final LinkedList<RectPoint> body;

    private RectPoint head, tail;
    private int distance;
    private int xVelocity;
    private int yVelocity;

    private boolean turned;
    private boolean dead;
    private boolean moving;
    private boolean spawn;
    private boolean growing;
    private boolean molting;

    public Snake(RectPoint SpawnPoint, Byte num, String name, Color cl) {
        short length = 9;

        this.name = name;
        this.serialNum = num;
        this.color = cl;
        score = 0;
        xVelocity = 0;
        yVelocity = 0;
        speed = 20f; // from 0 to 50, number of blocks traverse in 1s
        turned = false;
        dead = false;
        moving = false;
        spawn = true;
        growing = false;
        molting = false;

        head = SpawnPoint;
        tail = SpawnPoint;
        body = new LinkedList<>();
        for(int i=0; i<length; i++){
            body.add(i, SpawnPoint);
        }
    }
    public void grow() {
        int x, y, xNew, yNew;
        growing = true;
        x = head.getX();
        y = head.getY();
        xNew = x + xVelocity;
        yNew = y + yVelocity;
        body.addFirst(head);
        head = new RectPoint(xNew, yNew);
    }
    public void move(int width, int height) {
        int x, y, xNew, yNew;
//        int distance;
//        int head2body0, tail2bodyr1;
        boolean toMove;
        short length = getLength();
        x = head.getX();
        y = head.getY();
        xNew = x + xVelocity;
        yNew = y + yVelocity;
        RectPoint newPoint = new RectPoint(xNew, yNew);

        RectPoint.direction headDirection, tailDirection;
//        headDirection = body.get(1).directionTo(body.get(0));
        tailDirection = body.get(length-1).directionTo(body.get(length-2));
        distance = body.get(0).distanceTo(newPoint);
        newPoint = wrapMove(xNew, yNew, width, height);

        growing = false;
        toMove = !moving & isMoving();
        moving = isMoving();


        // tail part
        if (!isBodyStacked()) { // snake body is not stacked
            spawn = false;
            if (body.get(length-1).isAdjacentTo(body.get(length-2))) {
                tailMove(RectPoint.direction.POS, tailDirection);}
            else {
                tailMove(RectPoint.direction.NEG, tailDirection);}
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

//        head2body0 = body.get(0).distanceTo(head);
//        tail2bodyr1 = body.get(length-1).distanceTo(tail);
    }
    private RectPoint wrapMove(int xNew, int yNew, int width, int height) {
        RectPoint newPoint = new RectPoint(xNew, yNew);
        int stride = SIZE; 
        if (xNew<0) xNew = width-stride;
        if (xNew>width-stride) xNew = 0;
        if (yNew<0) yNew = height-stride;
        if (yNew>height-stride) yNew = 0;
        RectPoint wrappedPoint = new RectPoint(xNew, yNew);
        if (!wrappedPoint.equals(newPoint))
            newPoint = wrappedPoint;
        return newPoint;
    }
    private void headMove(RectPoint newPoint) {
//        int distance = body.get(0).distanceTo(newPoint);

        if (turned) {
            RectPoint.direction front = body.get(0).directionTo(head);
            int factor;
            if (front == RectPoint.direction.POS)
                factor = 1;
            else if (front == RectPoint.direction.NEG)
                factor = -1;
            else factor = 0;

            if (head.slideInXY(body.get(0)) == RectPoint.slide.X)
                head = new RectPoint(body.get(0).getX() + factor*distance,
                        body.get(0).getY());
            else if (head.slideInXY(body.get(0)) == RectPoint.slide.Y)
                head = new RectPoint(body.get(0).getX(),
                        body.get(0).getY() + factor*distance);
            else if (head.slideInXY(body.get(0)) == RectPoint.slide.NONE)
                head = newPoint;
        }
        else head = newPoint;

    }
    private void tailMove(RectPoint.direction split,
                          RectPoint.direction tailDirection) {
        int factorS, factorT;
        int length = getLength();

        if (split == RectPoint.direction.POS)
            factorS = 1;
        else if (split == RectPoint.direction.NEG)
            factorS = -1;
        else factorS = 0;
        if (tailDirection == RectPoint.direction.POS)
            factorT = 1;
        else if (tailDirection == RectPoint.direction.NEG)
            factorT = -1;
        else factorT = 0;

        if (body.get(length-1).slideInXY(body.get(length-2)) == RectPoint.slide.X)
            tail= new RectPoint(body.get(length-1).getX() +
                    factorS*factorT*distance,
                    body.get(length-1).getY());
        if (body.get(length-1).slideInXY(body.get(length-2)) == RectPoint.slide.Y)
            tail = new RectPoint(body.get(length-1).getX(),
                    body.get(length-1).getY() +
                            factorS*factorT*distance);
    }
    public void molt(){

    }
    private boolean isBodyStacked() {
        boolean stacked = false;
        int length = getLength();
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
        if (yVelocity == 1) return;
        xVelocity = 0;
        yVelocity = -1;
    }
    public void setDown() {
        if (yVelocity == -1) return;
        xVelocity = 0;
        yVelocity = 1;
    }
    public void setLeft() {
        if (xVelocity == 1) return;
        xVelocity = -1;
        yVelocity = 0;
    }
    public void setRight() {
        if (xVelocity == -1) return;
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

    public Color getColor() { return color;}
    public String getName() { return name;}
    public boolean isGrowing() { return growing;}
    public byte getSerialNum() { return serialNum;}
    public boolean isSpawned() { return spawn;}
    public void setTurned() { this.turned = true;}
    public void setAlive() { this.dead = false;}
    public boolean isDead() { return dead;}
    public void setScore(int scoreNew) { this.score = scoreNew;}
    public int getScore() { return score;}
    public LinkedList<RectPoint> getBody() { return body;}
    public void setHead(RectPoint point) { head = point;}
    public RectPoint getHead() { return head;}
    public RectPoint getTail() { return tail;}
    public void setTail(RectPoint point) { tail = point;}
    public short getLength() { return (short) body.size();}
    public float getSpeed() { return speed;}
    public boolean isTurned() { return turned;}
    public boolean isMolting() { return molting;}
}

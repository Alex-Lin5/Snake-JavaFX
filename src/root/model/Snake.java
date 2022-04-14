package root.model;

public class Snake {
    private float speed;
    private int length;

    private int xVelocity;
    private int yVelocity;

    private boolean xDirection;
    private boolean yDirection;
    private final boolean UP = false;
    private final boolean DOWN = true;
    private final boolean LEFT = false;
    private final boolean RIGHT = true;

    public Snake() {
        xDirection = RIGHT;
        yDirection = UP;
        xVelocity = 0;
        yVelocity = 0;
        speed = 0.5f;

        length = 3;
    }


    public float getSpeed() {
        return speed;
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

package root.model;

public class DataPacket {
    public Point head; // current point of head
    public Point food;
    public int[] snakeDirection;
    public int length;
    public int score;
    public boolean dead;

    public DataPacket() {
        length = 2;
        score = 0;
        dead = false;
    }
}

package root.model;

public class Food {
    private Point FoodPoint;
    private int Score;
    public Food(Point thePoint){
        this.FoodPoint = thePoint;
        this.Score = 100;
    }
    public Point getPoint() { return FoodPoint;}
    public void setPoint(Point thePoint) { this.FoodPoint = thePoint;}
    public int getScore() { return Score;}
}

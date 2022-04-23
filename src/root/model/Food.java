package root.model;

public class Food {
    private Point FoodPoint;
    private int Score;
    public Food(Point thePoint){
        this.FoodPoint = thePoint;
        this.Score = 100;
    }
    public Point getFoodPoint() { return FoodPoint;}
    public void setFoodPoint(Point thePoint) { this.FoodPoint = thePoint;}
    public int getScore() { return Score;}
}

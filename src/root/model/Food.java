package root.model;

public class Food {
//public class Food extends Point{
//    double x = 10;
//    double y = 20;
    private Point FoodPoint;
    private int Score;
//    @Override
//    public Food(double x, double y){
    public Food(Point thePoint){
//        super(thePoint.getX(), thePoint.getY());
        this.FoodPoint = thePoint;
        this.Score = 100;
    }
//        this.FoodPoint =
//        super(10, 20);}
//        super(FoodPoint.getX(), FoodPoint.getY());}
//        this.x = x; this.y = y;}

//    Food(int x, int y) {
//        super(x, y);
//    }
    public Point getFoodPoint() { return FoodPoint;}
    public void setFoodPoint(Point thePoint) { this.FoodPoint = thePoint;}
    public int getScore() { return Score;}
}

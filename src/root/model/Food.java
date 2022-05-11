package root.model;

public class Food {
    private Point FoodPoint;
    private int Score;
    public Food() { this.Score = 100;}
    public Food(Point thePoint){
        this.FoodPoint = thePoint;
        this.Score = 100;
    }
    public void test() {
        FoodBase food1 =  new FoodBase(new Point(0, 0));
        Point p1 = food1.point;
    }
    public Point getPoint() { return FoodPoint;}
    public void setPoint(Point thePoint) { this.FoodPoint = thePoint;}
    public int getScore() { return Score;}
}

class FoodBase{
    Point point;
    private final int Score;
    public FoodBase() { this.Score = 100;}
    public FoodBase(Point thePoint){
        this.point = thePoint;
        this.Score = 100;
    }
}

class FoodBonus extends FoodBase{
    private Point point;
    private final int Score;
    public FoodBonus() { this.Score = 300;}
    public FoodBonus(Point thePoint){
        this.point = thePoint;
        this.Score = 300;
    }
}

//enum good1{
//
//}
//enum good2 extends good1{
//
//}

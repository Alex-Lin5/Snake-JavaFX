package root.model;

public enum Food {
    FoodBase(0, 100),
    FoodBonus(1, 300);
    private byte serialNum;
    private int score;
    private Point point;
    Food(Number num, int score) {
        serialNum = num.byteValue();
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public void setPoint(Point pt) { point = pt;}
    public Point getPoint() {return point;}
}
//    private Point FoodPoint;
//    private int Score;
//    public Food() { this.Score = 100;}
//    public Food(Point thePoint){
//        this.FoodPoint = thePoint;
//        this.Score = 100;
//    }
//    public void test() {
//        FoodBase food1 =  new FoodBase(new Point(0, 0));
//        Point p1 = food1.point;
//    }
//    public Point getPoint() { return FoodPoint;}
//    public void setPoint(Point thePoint) { this.FoodPoint = thePoint;}
//    public int getScore() { return Score;}
//}

//class FoodBase{
//    Point point;
//    int Score;
//    public FoodBase() { this.Score = 100;}
//    public FoodBase(Point thePoint){
//        this.point = thePoint;
//        this.Score = 100;
//    }
//}
//
//class FoodBonus extends FoodBase{
//    public FoodBonus() {
//        Score = 300;
//    }
//    public FoodBonus(Point thePoint){
//        this.point = thePoint;
//        this.Score = 300;
//    }

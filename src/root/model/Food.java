package root.model;

public class Food extends Point{
//    double x = 10;
//    double y = 20;

//    @Override
//    public Food(double x, double y){
    public Food(Point FoodPoint){
//        super(10, 20);}
        super(FoodPoint.getX(), FoodPoint.getY());}
//        this.x = x; this.y = y;}

//    Food(int x, int y) {
//        super(x, y);
//    }

//    public int getX(){ return x;}
//    public int getY(){ return y;}
}

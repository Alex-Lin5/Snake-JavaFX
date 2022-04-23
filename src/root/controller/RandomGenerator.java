package root.controller;

import root.model.Point;

import java.util.LinkedList;
import java.util.Random;

public class RandomGenerator {
    public RandomGenerator(){}
    public static Point generatePointonGrid (
        LinkedList<Point> body, int width, int height) {
        int x, y;
        boolean stacked;
        Point point;
        Random random = new Random();

        do {
            stacked = false;
            x = random.nextInt(width);
            y = random.nextInt(height);
            point = new Point(x, y);
            point = point.getXYonGrid();
            for (int i=0; i<body.size(); i++){
                if (Point.Equal(point, body.get(i))) {
                    stacked = true;
                    break;
                }
            }
        } while (stacked);
        return point;
    }
}

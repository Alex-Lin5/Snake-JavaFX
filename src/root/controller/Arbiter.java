package root.controller;

import root.model.Point;

import java.util.LinkedList;
import java.util.Random;

public class Arbiter {
    
    public Arbiter(){}
    
    public Point generatePoint (
//            public static Point generatePoint (
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
            point = point.getPointOnGrid();
            if (point.isDuplicateIn(body, 0, body.size()))
                stacked = true;
//            if (body.contains(point))
//                stacked = true;
        } while (stacked);
        return point;
    }
}

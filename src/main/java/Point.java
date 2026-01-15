public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update(double newX, double newY){
        x = newX;
        y = newY;
    }

    public void update(Point newPoint){
        x = newPoint.getX();
        y = newPoint.getY();
    }
}



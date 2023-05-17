package chapter04;

public class Point {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Point p = (Point) obj;

        if ((this.x == p.x) && (this.y == p.y))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }

}

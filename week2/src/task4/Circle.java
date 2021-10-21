package task4;

public class Circle extends Figure{
    public int x;
    public int y;
    public int r;

    public  Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override //переопределение метода класса Object
    public String toString() {
        return String.format("C (%d, %d), %d", this.x, this.y, this.r);
    }
}

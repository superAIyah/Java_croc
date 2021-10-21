package task4;

public class Rectangle extends Figure{
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Rectangle(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override //переопределение метода класса Object
    public String toString() {
        return String.format("R (%d, %d), (%d, %d)", this.x1, this.y1, this.x2, this.y2);
    }
}

package task5;

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

    @Override // переопределение метода интерфейса Movable
    public void move(int dx, int dy) {
        this.x1 += dx;
        this.y1 += dy;
        this.x1 += dx;
        this.y1 += dy;
    }

    @Override // переопределение метода класса Figure
    public boolean contains(int x, int y) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2;
    }
}

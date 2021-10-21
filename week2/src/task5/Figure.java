package task5;

public abstract class Figure implements Movable {
    //базовый класс для геометрических фигур
    public abstract boolean contains(int x, int y);
    public abstract String toString();
    protected static double dist(int x1, int y1, int x2, int y2) {    //метод нужен только для дочерних классов
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}

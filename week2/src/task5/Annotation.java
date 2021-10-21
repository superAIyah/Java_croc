package task5;

public class Annotation {
    private Figure fig;   // любая геом. фигура наследуемая от класса Figure
    private String label; //подпись для фигуры

    public Annotation(Figure fig, String label) {
        this.fig = fig;
        this.label = label;
    }

    public boolean contains(int x, int y) { // проверка содержания точки в области
        return this.fig.contains(x, y);
    }

    public boolean contains(String s) {  // проверка содержания строки s в качестве подстроки
        return this.label.contains(s);
    }

    public void move(int dx, int dy) {
        this.fig.move(dx, dy);
    }

    @Override //переопределение метода класса Object
    public String toString() {
        return fig.toString() + " : " + label;
    }
}

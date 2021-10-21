package task4;

public class Annotation {
    private Figure fig;   // любая геом. фигура наследуемая от класса Figure
    private String label; //подпись для фигуры

    public Annotation(Figure fig, String label) {
        this.fig = fig;
        this.label = label;
    }

    @Override
    public String toString() {
        return fig.toString() + " : " + label;
    }
}

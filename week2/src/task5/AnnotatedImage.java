package task5;

public class AnnotatedImage { //класс хранит все выделенные области и подписи к ним
    private final String imagePath;
    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    Annotation findByPoint(int x, int y) { // поиск по точке
        for (Annotation ann : this.annotations) {
            if (ann.contains(x ,y))
                return ann;
        }
        System.out.println(String.format("(%d, %d) не принадлежит ни одной аннотации", x, y));
        return null;
    }

    Annotation findByLabel(String s) { // поиск по подстроке
        for (Annotation ann : this.annotations) {
            if (ann.contains(s))
                return ann;
        }
        System.out.println(s + " не принадлежит ни одной аннотации");
        return null;
    }


}

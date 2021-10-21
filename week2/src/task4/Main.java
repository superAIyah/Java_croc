package task4;

public class Main {
    public static void main(String[] args) {
        // создадим выделенные области
        Figure fig1 = new Circle(0, 0, 10);
        Figure fig2 = new Circle(5, 5, 1);
        Figure fig3 = new Rectangle(-1, -1, 1, 1);
        Figure fig4 = new Rectangle(5, 0, 7, 2);

        // напишем к ним подписи
        String lab1 = "ball";
        String lab2 = "watermelon";
        String lab3 = "phone";
        String lab4 = "book";

        // создадим разметки
        Annotation an1 = new Annotation(fig1, lab1);
        Annotation an2 = new Annotation(fig2, lab2);
        Annotation an3 = new Annotation(fig3, lab3);
        Annotation an4 = new Annotation(fig4, lab4);

        Annotation[] mas = {an1, an2, an3, an4};
        String path = "/home/fedor/IdeaProjects/week2";
        AnnotatedImage img = new AnnotatedImage(path, mas); // объединим все разметки фотографии

        for (Annotation obj: img.getAnnotations()) { // выведим все разметки фотографии
            System.out.println(obj);
        }
    }
}

package task1;
import java.util.Scanner;

public class Main {

    static class Point { //класс для представления точки
        double x;
        double y;
    }

    public static double dist(Point a, Point b){ // расстояние между двумя точками
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Point[] mas = new Point[3];
        for (int i = 0; i < 3; i++) { //считываем три точки
            mas[i] = new Point();
            System.out.print("Введите координату x вершины №" + (i+1) + ": ");
            mas[i].x = console.nextDouble();
            System.out.print("Введите координату y вершины №" + (i+1) + ": ");
            mas[i].y = console.nextDouble();
        }
        double a = dist(mas[0], mas[1]);
        double b = dist(mas[1], mas[2]);
        double c = dist(mas[2], mas[0]);
        double pp = (a + b + c) / 2; //полупериметр
        double s = Math.sqrt(pp * (pp - a) * (pp - b) * (pp - c)); //искомая площадь
        System.out.print("Площадь треугольника: ");
        System.out.printf("%.1f", s); //выведем один знак после запятой, как показано в примере
    }
}

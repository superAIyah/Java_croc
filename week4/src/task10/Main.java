package task10;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите начало и конец разговоров\n" +
                "\"EXIT\" для выхода из режима ввода");

        StringJoiner allStrings = new StringJoiner(";");
        while (true) { // считываем все строки
            String s = scanner.nextLine();
            if (s.equals("EXIT"))
                break;
            allStrings.add(s);
        }
        String[] masStr = allStrings.toString().split("[,;]"); // сплитим все строки, чтобы получить только числа

        CallPoint[] masPoint = new CallPoint[masStr.length];
        boolean type = false; // начало звонка
        for (int i = 0; i < masStr.length; i++) {
            masPoint[i] = new CallPoint(type, Integer.parseInt(masStr[i]));
            type = !type; // чередование с концом звонка
        }

        Arrays.sort(masPoint); // сортируем события по времени

        int balance = 0; // баланс звонков
        int answer = 0;
        for (CallPoint point : masPoint) {
            if (point.type) // встертили событые: конец звонка -> уменьшаем текущее кол-во звонков
                balance--;
            else
                balance++; // начало звонка -> увеличиваем текущее кол-во звонков
            answer = Math.max(balance, answer); // обновляем ответ
        }
        System.out.println(answer);
    }
}

package task3;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String s = console.nextLine(); //считываем всю стркоу
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) { //считаем кол-во пробелов
            if (s.charAt(i) == ' ')
                cnt++;
        }
        int[] spl = new int[cnt + 2]; //создаем массив разделителей строки
        spl[0] = -1;
        spl[cnt + 1] = s.length();
        int j = 1;
        for (int i = 0; i < s.length(); i++) { //запоминаем позиции пробелов
            if (s.charAt(i) == ' ') {
                spl[j] = i;
                j++;
            }
        }
        int[] mas = new int[cnt + 1];
        j = 0;
        for (int i = 0; i < spl.length - 1; i++) { //разделяем строку на числа, зная разделители
            int le = spl[i];
            int re = spl[i + 1];
            String newstr = s.substring(le + 1, re);
            mas[j] = Integer.parseInt(newstr);
            j++;
        }
        int imax = 0;
        int imin = 0;
        int mi = mas[0];
        int ma = mas[0];
        for (int i = 0; i < mas.length; i++) { //определение минимума и максимума
            if (mas[i] > ma) {
                imax = i;
                ma = mas[i];
            }
            if (mas[i] < mi) {
                imin = i;
                mi = mas[i];
            }
        }
        int c = mas[0]; // 1 замена
        mas[0] = mi;
        mas[imin] = c;

        if (imax == 0 ) //если первой заменой мы поменяли позицию максимума, то максимум встал на место минимума
            imax = imin;
        c = mas[mas.length - 1];
        mas[mas.length - 1] = ma;
        mas[imax] = c;

        for (int a : mas) //вывод ответа
            System.out.print(a + " ");
    }
}

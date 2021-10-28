package task7;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();
        String[] posTemp = s.split(" "); // разбиваем строку
        int n = posTemp.length;

        ChessPosition[] pos = new ChessPosition[n]; // парсим строку
        for (int i = 0; i < n; i++) {
            pos[i] = ChessPosition.parse(posTemp[i]);
        }

        boolean res = ChessPosition.horseReachable(pos); // проверка на досягаемость конем
        if (res) {
            System.out.println("OK");
        }
        scanner.close();
    }
}

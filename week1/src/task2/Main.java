package task2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        double x = console.nextDouble();
        int cnt = 0;
        String[] ans = {"B", "KB", "MB", "GB", "TB"};
        while (x >= 1024) { //будем переходить B->KB->MB->GB->TB пока не встретим нужный тип
            x /= 1024;
            cnt++;
        }
        String res = String.format("%.1f", x);
        System.out.println(res + " " + ans[cnt]);
    }
}

package task8;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner console = new Scanner(System.in);
        System.out.println("Введите путь к файлу относительно текущего пакета");
        String path = console.nextLine();
        String prefix = "src/task8/";  // по умолчанию JVM считает путь от папки корневого модуля
        path = prefix + path;
        try (BufferedReader r = new BufferedReader(new FileReader(path))) { // буферизация потока для ускорения ввода
            String line;
            int cnt = 0; // суммарное количество слов
            while ((line = r.readLine()) != null) {
                if (line.isEmpty())
                    continue;
                cnt += line.split("\\s+").length;
            }
            System.out.println("There are "+cnt+" words in file "+path);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Файл не найден");
        }
    }
}

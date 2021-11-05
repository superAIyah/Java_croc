package task9;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void readNext(BufferedReader thread, Long[] time, String[] message, int pos) { // считывает следующее время и сообщение лога
        try {
            String[] sTempMas = thread.readLine().split(" ");
            time[pos] = Long.parseLong(sTempMas[0]);
            message[pos] = String.join(" ", Arrays.copyOfRange(sTempMas, 1, sTempMas.length));
        }
        catch (Exception e) {
            time[pos] = null;
            message[pos] = null;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название папки с логами");
        String dir = scanner.nextLine();
        String prefix = "src/task9/";  // по умолчанию JVM считает путь от папки корневого модуля
        dir = prefix + dir;
        Path logs = Paths.get(dir);

        LogsFileVisitor logsVisitor = new LogsFileVisitor();
        try {
            Files.walkFileTree(logs, logsVisitor); // обход дерева файлов с сохранением всех логов
        }
        catch (Exception e) {
            System.out.println("Директория " + dir + " не найдена");
            return;
        }
        String[] logsMas = logsVisitor.sj.toString().split("\n");
        int n = logsMas.length; // кол-во файлов с логами

        BufferedReader[] threads = new BufferedReader[n]; // объявим все наши потоки
        for (int i = 0; i < n; i++) // определим все наши потоки
            threads[i] = new BufferedReader(new FileReader(logsMas[i])); // буферизация потока для ускорения ввода
        String[] messageThreads = new String[n]; // следующая строка каждого потока
        Long[] timeThreads = new Long[n]; // следующее время записи каждого лога
        for (int i = 0; i < n; i++) { // начальная инициализация
            readNext(threads[i], timeThreads, messageThreads, i);
        }

        // сортировка слиянием
        while (true) {
            Long earlyTime = null;
            String earlyMessage = null;
            int pos=0;

            for (int i = 0; i < n; i++) { // выберем лог с минимальном временем записи
                if (timeThreads[i] == null)
                    continue;
                if (earlyTime == null) { // начальное присвоение
                    earlyTime = timeThreads[i];
                    earlyMessage = messageThreads[i];
                    pos = i;
                    continue;
                }
                if (timeThreads[i] < earlyTime) { // если встретили время раньше
                    earlyTime = timeThreads[i];
                    earlyMessage = messageThreads[i];
                    pos = i;
                }
            }

            if (earlyTime == null) // логи кончились
                break;
            System.out.println(earlyTime + " " + earlyMessage); // вывод самого раннего лога
            readNext(threads[pos], timeThreads, messageThreads, pos); // передвигаем указатель на следующий лог в потоке
        }
    }
}

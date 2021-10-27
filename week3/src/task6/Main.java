package task6;
import java.io.File;
import java.io.PrintWriter; //вывод в файл
import java.util.Scanner; // ввод из файла
import java.util.StringJoiner; // сложение строк с разделителем

public class Main {

    public static int insideString(int pos, String str, StringBuilder ans) { // выполнить обработку внутри строки
        ans.append(str.charAt(pos - 1));
        while (pos < str.length() && str.charAt(pos) != '\"') { // пока не встретим конец строки
            if (str.charAt(pos) == '\\') // обработка экранирования
                ans.append(str.charAt(pos++));
            ans.append(str.charAt(pos++));
        }
        if (pos < str.length()) // добавляем закрывающую кавычку
            ans.append(str.charAt(pos));
        return pos + 1;
    }

    public static int insideOneRowComment(int pos, String str) { // выполнить обработку внутри однострочного комментария
        while (pos < str.length() && str.charAt(pos) != '\n') // пока не дойдем до конца строки
            pos++;
        return pos;
    }

    public static int insideMultiRowComment(int pos, String str) { // выполнить обработку внутри многострочного комментария
        while (pos < str.length() && (str.charAt(pos) != '/' || str.charAt(pos - 1) != '*'))
            pos++;
        return pos + 1;
    }

    public static void main(String[] args) {
        File fileIn = new File("src/task6/inputSample.txt"); // определние пути к файлу inputSample.txt
        Scanner scanner = new Scanner(System.in); // инициализация по умолчанию

        // открываем файл/* на чтение, если путь найден
        try {
            scanner = new Scanner(fileIn);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Файл ввода не найден, можете использовать командную строку для ввода");
        }

        // считываем весь файл в одну строку с разделителем "\n"
        StringJoiner joiner = new StringJoiner("\n");
        while(scanner.hasNextLine()) {
            joiner.add(scanner.nextLine());
        }
        String file_str = joiner.toString();
        int pos = 0; // начальная позиция
        StringBuilder ans = new StringBuilder();
        while (pos < file_str.length()) {
            if (file_str.charAt(pos) == '\"') // встретили строку -> символы в ней не являются комментариями
                pos = insideString(pos + 1, file_str, ans);
            else if (file_str.substring(pos, pos + 2).equals("//")) // встретили однострочный комментарий
                pos = insideOneRowComment(pos + 2, file_str);
            else if (file_str.substring(pos, pos + 2).equals("/*")) // встретили многострочный комментарий
                pos = insideMultiRowComment(pos + 2, file_str);
            else
                ans.append(file_str.charAt(pos++)); // обычный код
        }

        File fileOut = new File("src/task6/outputSample.txt"); // определние пути к файлу otput.txt
        PrintWriter writer = new PrintWriter(System.out); // инициализация по умолчанию
        try {
            writer = new PrintWriter(fileOut);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Файл вывода не найден, будет использоваться командная строка для вывода");
        }
        writer.print(ans);

        writer.close();
        scanner.close();
    }
}

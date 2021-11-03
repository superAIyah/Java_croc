package task6;
import java.io.File;
import java.io.PrintWriter; //вывод в файл
import java.io.Writer;
import java.util.Scanner; // ввод из файла
import java.util.StringJoiner; // сложение строк с разделителем

public class Main {
    static final boolean TEST_FLAG = true; // поставить значение true для выполнения теста

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

        // открываем файл на чтение, если путь найден
        try {
            scanner = new Scanner(fileIn);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Файл ввода не найден, можете использовать командную строку для ввода) \n" +
                    "Введите \"EXIT\" чтобы закончить ввод\n");
            scanner = new Scanner(System.in);
        }

        // считываем весь файл в одну строку с разделителем "\n"
        StringJoiner joiner = new StringJoiner("\n");
        while(scanner.hasNextLine()) {
            String nextString = scanner.nextLine();
            if (nextString.equals("EXIT"))
                break;
            joiner.add(nextString);
        }
        String fileStr = joiner.toString();

        String testStr = "/*\n" + // строка для теста
                " * My first ever program /in Java!\n" +
                " */\n" +
                "class Hello { // class body starts here \n" +
                "\n" +
                "    /* main method */\n"  +
                "    public static void main(String[] args/* we put command line arguments here*/) {\n" +
                "        String test = \"ddd//checking /*checking2*/qqq\";\n" +
                "        String test2 = \"qqq/*checking2*/asdasd\";\n" +
                "        // this line prints my first greeting to the screen\n" +
                "        System.out.println(\"Hi!\"); // :)\n" +
                "    }\n" +
                "} // the end\n" +
                "// to be continued...\n";
        if (TEST_FLAG)
            fileStr = testStr;

        int pos = 0; // начальная позиция
        StringBuilder ans = new StringBuilder();
        int len = fileStr.length();
        while (pos < fileStr.length()) {
            if (fileStr.charAt(pos) == '\"') // встретили строку -> символы в ней не являются комментариями
                pos = insideString(pos + 1, fileStr, ans);
            else if (pos + 1 < len && fileStr.substring(pos, pos + 2).equals("//")) // встретили однострочный комментарий
                pos = insideOneRowComment(pos + 2, fileStr);
            else if (pos + 1 < len && fileStr.substring(pos, pos + 2).equals("/*")) // встретили многострочный комментарий
                pos = insideMultiRowComment(pos + 2, fileStr);
            else
                ans.append(fileStr.charAt(pos++)); // обычный код
        }

        String fileNameOut = "src/task6/outputSample.txt";
        File fileOut = new File(fileNameOut); // определние пути к файлу output.txt
        PrintWriter writer = new PrintWriter(System.out); // инициализация по умолчанию
        try {
            if (fileOut.exists() && !TEST_FLAG)
                writer = new PrintWriter(fileOut);
            else
                throw new java.nio.file.NoSuchFileException(fileNameOut);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Файл вывода не найден, будет использоваться командная строка для вывода");
            writer = new PrintWriter(System.out);
        }
        finally {
            if (TEST_FLAG) // в случае выполнения теста, вывод производится в консоль
                writer = new PrintWriter(System.out);
        }
        writer.print(ans);

        writer.close();
        scanner.close();
    }
}
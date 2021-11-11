package task12;

public class BruteForceRunnable implements Runnable{
    private final static int N = 7; // длина изначального пароля
    private final static String HASH = "40682260CC011947FC2D0B1A927138C5"; // хеш пароля который мы забыли
    private final static char[] SYMBOLS = "abcdefghijklmnopqrstuvwxyz".toCharArray(); // 26 латинских букв
    private volatile static boolean cancelled = false; // гарантирует happens-before

    private int start; // индекс первого символа с которого начинать перебор
    private char[] passw; // перебираемые пароли

    public BruteForceRunnable(int start) {
        this.start = start;
        this.passw = new char[N];
    }

    private void bf(int len) {
        if (cancelled)
            return;
        if (len == N) { // получили какой-то пароль
            String result = HashMD5.hashPassword(String.valueOf(passw)); // получил его хеш
            if (result.equals(HASH)) { // сесли хеш совпал - ответ найден
                cancelled = true;
                System.out.println("password = " + String.valueOf(passw));
            }
            return;
        }
        int startTmp = 0;
        if (len == 0) // у каждого потока свой начальный символ для перебора
            startTmp = this.start;
        for (int i = startTmp; i < 26; i++) {
            passw[len] = SYMBOLS[i];
            bf(len + 1); // переберем следующий символ
            if (cancelled) // если нашелся ответ
                return;
        }
    }

    public void run() {
        char[] passw = new char[N];
        bf(0);
    }
}

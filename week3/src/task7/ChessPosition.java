package task7;
import java.lang.Math;

/* Поле имеет вид:
   a . . . h
 8
 .    .
 .      .
 .        .
 1
*/

public class ChessPosition {

    private int x;
    private int y;

    public ChessPosition() { } // инициализация полей по умолчанию

    private ChessPosition(int x, int y) { // для фабричного метода
        this.x = x;
        this.y = y;
    }

    static ChessPosition parse(String position) throws Exception{ // парсинг строки
        if (position.length() != 2) // введено неправильное кол-во символов
            throw new IllegalPositionException("Position must have 2 symbols: " + position);

        char xChar = position.charAt(0);
        char yChar = position.charAt(1);

        if (xChar < 'a' || xChar > 'h' || yChar < '1' || yChar > '8') // позиция выходит за границы поля
            throw new IllegalPositionException("This position doesn't exist on desk: " + position);

        //перевод строки в координаты с центром в a1
        int x = xChar - 'a' + 1; // 1..8
        int y = yChar - '0'; // 1..8

        return new ChessPosition(x, y);
    }

    private static boolean horseHit(ChessPosition pos1, ChessPosition pos2) throws Exception{
        int x1=pos1.x, y1=pos1.y, x2=pos2.x, y2=pos2.y;
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        boolean ok = (dx == 1 && dy == 2 || dx == 2 && dy == 1); // конь может пойти из pos1 в pos2
        if (ok)
            return ok;
        else
            throw new IllegalMoveException(pos1.toString(), pos2.toString()); // исключение неправильного хода
    }

    public static boolean horseReachable(ChessPosition[] positions) { // проверка посл-ти на проходимость конем
        boolean allOk = true;
        for (int i = 0; i < positions.length - 1; i++) {
            try {
                horseHit(positions[i], positions[i + 1]);
            }
            catch (Exception e) {
                allOk = false;
                System.out.println(e.getMessage()); // вывод сообщения об ошибке хода конем
            }
        }
        return allOk;
    }

    @Override
    public String toString() { // метод приведение к строке
        char xChar = (char)('a' + this.x - 1);
        char yChar = (char)('1' + this.y - 1);
        return String.format("%c%c", xChar, yChar);
    }
}

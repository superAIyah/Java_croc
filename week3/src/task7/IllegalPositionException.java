package task7;

public class IllegalPositionException extends Exception{ // вызывается в случае неправильного ввода пользователем

    public IllegalPositionException(String message) {
        super(message);
    }
}

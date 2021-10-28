package task7;

public class IllegalMoveException extends Exception{
    private final String pos1; // начальная позиция хода
    private final String pos2; // конечная позиция хода

    public IllegalMoveException(String pos1, String pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    @Override
    public String getMessage() {
        return String.format("Horse doesn't go like that: %s -> %s", pos1, pos2);
    }
}

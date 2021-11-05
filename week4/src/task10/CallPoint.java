package task10;

public class CallPoint implements Comparable<CallPoint>{
    public boolean type; // вид событыя {true : конец звонка, false : начало звонка}
    public int time;

    public CallPoint(boolean type, int time) {
        this.type = type;
        this.time = time;
    }

    @Override
    public int compareTo(CallPoint cp) { // переопределяем метод для сортировки
        if (this.time == cp.time) // если время одинаково то позже идет конец звонка
            if (this.type)
                return 1;
            else
                return -1;
        return this.time - cp.time; // сортировка по времени события
    }
}

package task11;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AuctionLot {
    private volatile BigDecimal price; // Критическая секция
    private volatile String name;      // доступ из разных потоков производится к общим ресурсам
    public volatile Calendar endTime;  // не кешировать окончание лота, для проверки в главном потоке

    private void setEndTime() { // функция изменения времени конца аукицона
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, 3); // аукцион кончается спустя три секунды, если ставка не перебита
        this.endTime = now;
    }

    public AuctionLot(String name, BigDecimal startPrice) {
        this.name = name;
        this.price = startPrice;
        setEndTime();
    }

    private synchronized void bet(String name, BigDecimal newPrice) { // т.к. из не synchronised перешли в synchronised
        if (this.price.compareTo(newPrice) < 0 && GregorianCalendar.getInstance().before(endTime)) { // совершаем доп. проверку
            this.name = name;
            this.price = newPrice;
            setEndTime();
        }
    }

    public void setLot(String name, BigDecimal startPrice) { //happens-before
        // если старая ставка меньше и мы ставим на лот то окончания дедлайна
        if (this.price.compareTo(startPrice) < 0 && GregorianCalendar.getInstance().before(endTime))
            bet(name, startPrice);
    }

    public String getWinner() {
        if (GregorianCalendar.getInstance().before(endTime)) // если аукцион еще не кончился
            return  null; // победителя еще нет
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}

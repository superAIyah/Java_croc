package task11;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AuctionLot {
    private BigDecimal price;
    private String name;
    public volatile Calendar endTime; // не кешировать окончание лота, для проверки в главном потоке

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

    public synchronized void SetLot(String name, BigDecimal startPrice) { //happens-before
        // если старая ставка меньше и мы ставим на лот то окончания дедлайна
        if (this.price.compareTo(startPrice) < 0 && GregorianCalendar.getInstance().before(endTime)) {
            this.name = name;
            this.price = startPrice;
            setEndTime();
        }
    }

    public String getWinner() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}

package task11;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) throws Exception {
        // создаем лот
        AuctionLot lot = new AuctionLot("car", new BigDecimal(50));

        // создаем участников
        Participant a = new Participant(new BigDecimal("100"), "Ben", lot);
        Participant b = new Participant(new BigDecimal("95"), "Ten", lot);
        Participant c = new Participant(new BigDecimal("105.9"), "Leroy", lot);
        Participant d = new Participant(new BigDecimal("75"), "Muller", lot);

        // создаем потоки
        Thread t1 = new Thread(new ParticipantRunnable(a));
        Thread t2 = new Thread(new ParticipantRunnable(b));
        Thread t3 = new Thread(new ParticipantRunnable(c));
        Thread t4 = new Thread(new ParticipantRunnable(d));

        // запускаем потоки
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // ждем окончание аукциона
        while (Calendar.getInstance().before(lot.endTime)) {
            continue;
        }

        // останавливаем ставки
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
        t4.interrupt();

        // печать победителя и финальную ставку
        System.out.println(lot.getWinner());
        System.out.println(lot.getPrice().setScale(2, RoundingMode.HALF_UP));
    }
}

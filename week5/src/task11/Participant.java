package task11;

import java.math.BigDecimal;

public class Participant {
    private BigDecimal budget;
    private String name;
    private AuctionLot lot;

    public Participant(BigDecimal budget, String name, AuctionLot lot) {
        this.budget = budget;
        this.name = name;
        this.lot = lot;
    }

    public void randomBet() { // участник делает случайную ставку от 0 до его бюджета
        BigDecimal rand = new BigDecimal(Math.random());
        BigDecimal value = rand.multiply(budget);
        lot.SetLot(name, value); // попытка перебить ставку
    }

    public String getName() {
        return this.name;
    }
}

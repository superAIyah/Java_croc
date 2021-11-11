package task11;

public class ParticipantRunnable implements Runnable {
    private Participant person;

    public ParticipantRunnable(Participant person) {
        this.person = person;
    }

    public void run() {
        while (true) { // каждую секунду участник будет пытаться перебить ставку
            try {
                person.randomBet();
                Thread.sleep(1000);
            }
            catch (Exception e) {
                System.out.println(person.getName() + " finished auction");
            }
        }
    }
}

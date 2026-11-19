// Drill 03, solved: book is synchronized, so the check and the act are one step.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Flight {

    static void checkPayment() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    int seatsLeft;
    final List<String> passengers = Collections.synchronizedList(new ArrayList<>());

    Flight(int seats) {
        this.seatsLeft = seats;
    }

    synchronized boolean book(String passenger) {
        if (seatsLeft > 0) {
            checkPayment();
            seatsLeft--;
            passengers.add(passenger);
            return true;
        }
        return false;
    }
}

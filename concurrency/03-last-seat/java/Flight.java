// Drill 03: the last seat. Make FlightTest pass. Keep the call to checkPayment().

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Flight {

    /** Stands in for the payment call every real booking makes. */
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

    boolean book(String passenger) {
        if (seatsLeft > 0) {
            checkPayment();
            seatsLeft--;
            passengers.add(passenger);
            return true;
        }
        return false;
    }
}

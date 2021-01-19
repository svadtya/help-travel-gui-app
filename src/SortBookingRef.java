/**
 * A comparator class to sort Passenger object by booking ref
 * @author Siva Aditya (E1800176)
 */

import java.util.Comparator;
import java.io.Serializable;

public class SortBookingRef implements Comparator<Passenger>, Serializable {

    @Override
    public int compare(Passenger passengerA, Passenger passengerB) {
        return passengerA.getBookingRef() - passengerB.getBookingRef();
    }
}
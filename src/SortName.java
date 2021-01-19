/**
 * A comparator class to sort Passenger object by name
 * @author Siva Aditya (E1800176)
 */

import java.util.Comparator;
import java.io.Serializable;

public class SortName implements Comparator<Passenger>, Serializable {

    @Override
    public int compare(Passenger passengerA, Passenger passengerB) {
        return passengerA.getName().compareTo(passengerB.getName());
    }
}

/**
 * A comparator class to sort Flight object by type, then by date
 * @author Siva Aditya (E1800176)
 */

import java.util.Comparator;
import java.io.Serializable;

public class SortType implements Comparator<Flight>, Serializable {

    @Override
    public int compare(Flight flightA, Flight flightB) {
        String typeFlightA = flightA.getClass().getName();
        String typeFlightB = flightB.getClass().getName();

        if (!typeFlightA.equals(typeFlightB)) // compare type
            return typeFlightA.compareTo(typeFlightB);
        else // if same type, compare date
            return flightA.getDate().compareTo(flightB.getDate());
    }
}

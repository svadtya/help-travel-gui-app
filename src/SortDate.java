/**
 * A comparator class to sort Flight object according to its date
 * @author Siva Aditya (E1800176)
 */

import java.util.Comparator;
import java.io.Serializable;

public class SortDate implements Comparator<Flight>, Serializable {

    @Override
    public int compare(Flight flightA, Flight flightB) {
        return flightA.getDate().compareTo(flightB.getDate());
    }
}

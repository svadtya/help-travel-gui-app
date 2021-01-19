/**
 * A comparator class to sort Flight object by its Flight No
 * @author Siva Aditya (E1800176)
 */

import java.util.Comparator;
import java.io.Serializable;

public class SortFlightNo implements Comparator<Flight>, Serializable {

    @Override
    public int compare(Flight flightA, Flight flightB) {
        return flightA.getFlightNo() - flightB.getFlightNo();
    }
}

/**
 * A comparator class to sort Movie object by its title
 * @author Siva Aditya (E1800176)
 */

import java.util.Comparator;
import java.io.Serializable;

public class SortTitle implements Comparator<Movie>, Serializable {

    @Override
    public int compare(Movie movieA, Movie movieB) {
        return movieA.getTitle().compareTo(movieB.getTitle());
    }
}

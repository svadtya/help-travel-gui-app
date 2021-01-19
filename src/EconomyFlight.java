/**
 * A class to create Economy Flight object
 * inherits from class Flight
 * @author Siva Aditya (E1800176)
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class EconomyFlight extends Flight implements Serializable
{
    // default constructor
    public EconomyFlight() {super();}

    // constructor with arguments
    public EconomyFlight(String ori, String dest, LocalDate date, String deparr,
                         double price, int childPerc)
    {
        super(ori, dest, date, deparr, price, childPerc);
        super.setAdultPrice(calcAdultPrice(price));
        super.setChildPrice(calcChildPrice(price, childPerc));
    }

     // FLIGHT PRICING
    public double calcAdultPrice(double price) {
        return price;
    }

    public double calcChildPrice(double price, double childPerc) {
        return price * ((double)childPerc/100); 
    }

    /**
     * A method to add calculate and return flight booking price
     * based on passenger data
     * @param ps Passenger object, to get data
     * @return booking price (double)
     */
    @Override
    public double calcPrice(Passenger ps) {
        return (super.getAdultPrice()*ps.getNumAdults()) +
                (super.getChildPrice()*ps.getNumChildren());
    }

    /**
     * A method to add this flight to passenger booking list
     * @param ps Passenger object that will book this flight
     * @return Flight object
     */
    @Override
    public Flight book(Passenger ps) {
        // check wether flight with the same date already exist
        ArrayList<Flight> flights = ps.getFlights();
        for (Flight fl: flights) {
            // flight is already booked
            if (this.equals(fl))
                return null;
            // flight with the same date is already booked
            if (fl.getDate().equals(super.getDate()))
                return fl;
        }

        // add this flight to passenger booking list
        ps.addFlight(this);
        // set totalPrice & numFlights
        ps.setTotalPrice(ps.getTotalPrice()+calcPrice(ps));
        ps.setNumFlights(flights.size());

        return this;
    }

    /**
     * A method to remove this flight from passenger booking list
     * @param ps Passenger object that will unbook this flight
     * @return boolean
     */
    @Override
    public boolean unbook(Passenger ps) {
        if (!ps.getFlights().contains(this))
            return false;

        ps.setTotalPrice(ps.getTotalPrice()-calcPrice(ps));
        return true;
    }

    /**A method to view flight details*/
    @Override
    public String toString() {
        // info about available movies in this flight
        String movies;
        if (super.getNumMovies() == 0)
            movies = "No movies are currently available in this flight";
        else
            movies = "Available movie\t: "+super.getMovieList().get(0);

        // return flight details
        return String.format("Flight <%s> || Type: Economy"+
                        "\n   * %s -> %s on %s (%s)"+
                        "\n   * %s"+
                        "\n   * Adult Price\t\t: RM %.2f"+
                        "\n   * Child Price\t\t: RM %.2f",
                super.getFlightNo(),
                super.getOrigin(),
                super.getDestination(),
                super.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                super.getDeparr(),
                movies, 
                super.getAdultPrice(), 
                super.getChildPrice());
    }
}

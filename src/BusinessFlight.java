/**
 * A class to create Business Flight object
 * inherits from class Flight
 * @author Siva Aditya (E1800176)
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class BusinessFlight extends Flight implements Serializable
{
    private double rate; // instance variables

    // default constructor
    public BusinessFlight() {super(); rate = 0.0;}

    // constructor with arguments
    public BusinessFlight(String ori, String dest, LocalDate date, String deparr,
                          double price, int childPerc, double rate)
    {
        super(ori, dest, date, deparr, price, childPerc); 
        super.setAdultPrice(calcAdultPrice(price, rate));
        super.setChildPrice(calcChildPrice(price, rate, childPerc));
        this.rate = rate;
    }

    // getter setter
    public double getRate() {return rate;}
    public void setRate(double rate) {this.rate = rate;}

    // FLIGHT PRICING
    public double calcAdultPrice(double price, double rate) {
        return price * rate;
    }

    public double calcChildPrice(double price, double rate, double childPerc) {
        return price * rate * ((double)childPerc/100); 
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

        // update passenger total booking price
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
            movies = "Number of movies available\t: "+super.getNumMovies();

        // return flight details
        return String.format("Flight <%s> || Type: Business"+
                        "\n   * %s -> %s on %s (%s)"+
                        "\n   * %s"+
                        "\n   * Adult Price (with rate)\t: RM %.2f"+
                        "\n   * Child Price (with rate)\t: RM %.2f",
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

/**
 * An abstract class to create Flight object
 * @author Siva Aditya (E1800176)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.io.Serializable;

public abstract class Flight implements Comparable<Flight>, Cloneable, Serializable
{
    // static variable to generate flightNo
    private static int nextFlightNo = 0;

    // instance variables
    private int flightNo;
    private String origin;
    private String destination;
    private LocalDate date;
    private String deparr;
    private double price;
    private double adultPrice;
    private double childPrice;
    private int childPerc;
    private ArrayList<Movie> movieList;
    private int numMovies;

    // default constructor
    public Flight() {
        this("unknown", "unknown", LocalDate.now(), "-", 0.0, 0);
    }

    // constructor with arguments
    public Flight(String origin, String destination, LocalDate date, String deparr,
                  double price, int childPerc)
    {
        // flightNo is auto-generated
        setFlightNo(++nextFlightNo);
        // origin & destination is capitalized
        this.origin = origin.toUpperCase();
        this.destination = destination.toUpperCase();
        this.date = date;
        this.deparr = deparr;
        this.price = price;
        this.childPerc = childPerc;
        adultPrice = 0.0;
        childPrice = 0.0;
        movieList = new ArrayList<Movie>();
        numMovies = 0;
    }

    // getter method
    public int getFlightNo() {return flightNo;}

    public String getOrigin() {return origin;}

    public String getDestination() {return destination;}

    public LocalDate getDate() {return date;}

    public String getDeparr() {return deparr;}

    public double getPrice() {return price;}

    public int getChildPerc() {return childPerc;}

    public ArrayList<Movie> getMovieList() {return movieList;}

    public int getNumMovies() {return numMovies;}

    public double getAdultPrice() {return adultPrice;}

    public double getChildPrice() {return childPrice;}

    // setter method
    public void setNumMovies(int numMovies) {this.numMovies = numMovies;}

    public void setFlightNo(int flightNo) {this.flightNo = flightNo;}

    public void setOrigin(String origin) {
        this.origin = origin.toUpperCase();
    }

    public void setDestination(String destination){
        this.destination = destination.toUpperCase();
    }

    public void setDate(LocalDate date) {this.date = date;}

    public void setDeparr(String deparr) {this.deparr = deparr;}

    public void setPrice(double price) {this.price = price;}

    public void setChildPerc(int childPerc) {this.childPerc = childPerc;}

    public void setAdultPrice(double price) {adultPrice = price;}

    public void setChildPrice(double price) {childPrice = price;}

    // MOVIE METHOD

     /**A method to whether a movie exists in this flight movie list*/
    public boolean checkMovie(Movie newMv) {
        for (Movie mv: movieList)
            if (mv.equals(newMv)){
                return true;
            }

        return false;
    }

    // Sorting method
    public void sortMovieByTitle() {
        Collections.sort(movieList, new SortTitle());
    }

    public void sortMovieByLength() {
        Collections.sort(movieList, new SortLength());
    }

    // PASSENGER METHOD

    /**A method to calculate passenger booking price*/
    public abstract double calcPrice(Passenger ps);

    /**A method to add flight to passenger booking list*/
    public abstract Flight book(Passenger ps);

    /**A method to remove flight from passenger booking list*/
    public abstract boolean unbook(Passenger ps);

    public int hashCode() {return this.flightNo;}

    public boolean equals(Object obj) {
        if (this == obj) // same object
            return true;

        if (!(obj instanceof Flight)) // not the same class
            return false;

        Flight fl = (Flight) obj;
        if (this.flightNo == fl.getFlightNo()) // unique flightNo
            return true;

            // same type with same flight details (except price)
        else if (this.getClass().getName().equals(fl.getClass().getName()) &&
                this.origin.equals(fl.getOrigin()) &&
                this.destination.equals(fl.getDestination()) &&
                this.date.equals(fl.getDate()) &&
                this.deparr.equals(fl.getDeparr()))
            return true;

        else
            return false;
    }

    @Override
    public int compareTo(Flight obj) {
        if (this == obj)
            return 0;
        if (this.equals(obj))
            return 0;

        // compare by flight no
        return flightNo - (obj.getFlightNo());
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public abstract String toString();

    /**
     * A static method to reset static variable nextFlightNo value
     * @param reset value (int)
     */
    public static void resetFlightNo(int reset) {nextFlightNo = reset;}
}

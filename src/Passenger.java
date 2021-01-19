/**
 * A class to create Passenger object
 * @author Siva Aditya (E1800176)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;

public class Passenger implements Comparable<Passenger>, Cloneable, Serializable
{
    // static variable to generate bookingRef
    private static int nextBookingRef = 0;

    // instance variables
    private int bookingRef;
    private String name;
    private int numAdults;
    private int numChildren;
    private ArrayList<Flight> flights;
    private int numFlights;
    private double totalPrice;

    // default constructor
    public Passenger() {this("unknown", 0, 0);}

    // constructor with arguments
    public Passenger(String name, int numAdults, int numChildren) {
        setBookingRef(++nextBookingRef); // bookingRef is auto-generated
        this.name = name;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        flights = new ArrayList<Flight>();
        numFlights = 0;
        totalPrice = 0;
    }

    // getter method
    public int getBookingRef() {return bookingRef;}

    public String getName() {return name;}

    public int getNumAdults() {return numAdults;}

    public int getNumChildren() {return numChildren;}

    public double getTotalPrice() {return totalPrice;}

    public ArrayList<Flight> getFlights() {return flights;}

    public int getNumFlights() {return numFlights;}

    // setter method
    public void setBookingRef(int bookingRef) {this.bookingRef = bookingRef;}

    public void setName(String name) {this.name = name;}

    public void setNumAdults(int numAdults) {this.numAdults = numAdults;}

    public void setNumChildren(int numChildren) {this.numChildren = numChildren;}

    public void setNumFlights(int numFlights) {this.numFlights = numFlights;}

    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}

    /**
     * A method to add Flight object to ArrayList flights
     * This method is only invoked through method book in class Flight
     */
    public Flight addFlight(Flight newFlight) {
        for (Flight fl: flights) {
            if (fl.equals(newFlight))
                return null;
        }
        flights.add(newFlight);
        return newFlight;
    }

    /**A method to remove Flight object from ArrayList flights*/
    public boolean removeFlight(int flightNo) {
        for (Flight fl: flights){
            if (fl.getFlightNo() == flightNo){
                // invoke Flight's unbook method
                // to update passenger total price
                fl.unbook(this);
                flights.remove(fl);
                setNumFlights(flights.size());
                return true;
            }
        }
        return false;
    }

    /**A method to delete all Flight object*/
    public void clearFlights() {
        flights.clear(); setTotalPrice(0); setNumFlights(0);
    }

    public void sortItinerary() {
        Collections.sort(flights, new SortDate());
    }

    /**A method to calculate total booking price of this passenger*/
    public double calcBookingPrice() {
        int totalBookingPrice = 0;
        for (Flight f: flights) {
            totalBookingPrice += f.calcPrice(this);
        }

        totalPrice = totalBookingPrice;
        return totalPrice;
    }


    public int hashCode() {return this.bookingRef;}

    public boolean equals(Object obj) {
        if (this == obj) // same object
            return true;

        if (!(obj instanceof Passenger)) // not the same class
            return false;

        Passenger ps = (Passenger) obj;
        if (this.getBookingRef() == ps.getBookingRef()) // unique bookingRef
            return true;

        return false;
    }

    @Override
    public int compareTo(Passenger obj) {
        if (this == obj)
            return 0;
        if (this.equals(obj))
            return 0;

        // compare by bookingRef
        return bookingRef - (obj.getBookingRef());
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

    /**A method to view passenger details*/
    public String toString() {
        return String.format("Passenger <Booking Ref: %s>"+
                        "\n   - Name\t\t\t: %s"+
                        "\n   - Total Adults\t: %s"+
                        "\n   - Total Children\t: %s"+
                        "\n   - Booked Flight\t: %s"+
                        "\n   ----------------------------------------"+
                        "\n   - Total Price\t: RM %.2f",
                bookingRef, name, numAdults, numChildren, numFlights, totalPrice);
    }

    /**
     * A static method to reset static variable nextFlightNo value
     * @param reset value (int)
     */
    public static void resetBookingRef(int reset) {nextBookingRef = reset;}
}

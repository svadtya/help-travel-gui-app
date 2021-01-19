/**
 * A class to create TravelAgent object
 * a container class for Flight and Passenger object
 * @author Siva Aditya (E1800176)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class TravelAgent implements Serializable
{
    // instance variables
    private String name;
    private ArrayList<Flight> flightList;
    private int numOfFlight;
    private ArrayList<Passenger> passengerList;
    private int numOfPassenger;

    // default constructor
    public TravelAgent() {this("unknown");}

    // constructor with arguments
    public TravelAgent(String name) {
        this.name = name;
        // initialize array
        flightList = new ArrayList<Flight>();
        numOfFlight = 0;
        passengerList = new ArrayList<Passenger>();
        numOfPassenger = 0;
    }

    // getter method
    public String getName() {return name;}

    public ArrayList<Flight> getFlightList() {return flightList;}

    public int getNumOfFlight() {return numOfFlight;}

    public ArrayList<Passenger> getPassengerList() {return passengerList;}

    public int getNumOfPassenger() {return numOfPassenger;}

    // setter method
    public void setName(String name) {this.name = name;}

    public void setNumOfFlight(int numOfFlight) {this.numOfFlight = numOfFlight;}

    public void setNumOfPassenger(int numOfPassenger) {this.numOfPassenger = numOfPassenger;}

    // FLIGHT METHOD

    /**
     * A method to search Flight object in flightList
     * @param flightNo is used to search the object
     * @return Flight object
     */
    public Flight findFlight(int flightNo) {
        for(Flight fl: flightList)
            if (fl.getFlightNo() == flightNo)
                return fl;

        return null;
    }

    /**
     * A method to seach Flight object in flightList
     * @param flight the object to be searched
     * @return boolean
     */
    public boolean findFlight(Flight flight) {
        for(Flight fl: flightList)
            if (fl.equals(flight))
                return true;

        return false;
    }

    /**A method to delete all flight in flightList*/
    public void clearFlightList() {
        flightList.clear(); setNumOfFlight(0);
        for (Passenger ps: passengerList)
            // also delete all flight from pasenger flight list
            ps.clearFlights();
        // reset the auto-generated flight no
        Flight.resetFlightNo(0);
    }

    // Sorting method
    public void sortFlightByNo() {
        Collections.sort(flightList, new SortFlightNo());
    }

    public void sortFlightByType() {
        Collections.sort(flightList, new SortType());
    }

    public void sortFlightByDate() {
        Collections.sort(flightList, new SortDate());
    }

    /**A method to sort movie collection 
      *of every flight by its title*/
    public void reSortFlightMovie() {
        for (Flight flight: flightList)
            flight.sortMovieByTitle();
    }


    // PASSENGER METHOD

    /**
     * A method to search Passenger object in passengerList
     * @param bookingRef is used to search the object
     * @return Passenger object
     */
    public Passenger findPassenger(int bookingRef) {
        for(Passenger ps: passengerList)
            if (ps.getBookingRef() == bookingRef)
                return ps;
        return null;
    }

    /**A method to delete all passenger in passengerList*/
    public void clearPassengerList() {
        passengerList.clear(); setNumOfPassenger(0);
        // reset the auto generated booking ref
        Passenger.resetBookingRef(0);
    }

    // Sorting method
    public void sortPassengerBookRef() {
        Collections.sort(passengerList, new SortBookingRef());
    }

    public void sortPassengerName() {
        Collections.sort(passengerList, new SortName());
    }


    /**A method to reset Flight and Passenger's static
     * variable after loading data from a file*/
    public void reset() {
        if (numOfFlight > 0) 
            Flight.resetFlightNo(resetFlight());
        else
            Flight.resetFlightNo(numOfFlight);

        if (numOfPassenger > 0)
            Passenger.resetBookingRef(resetPassenger());
        else 
            Passenger.resetBookingRef(numOfPassenger);
        
    }

    /**A method to reset Flight class auto-generated Flight No*/
    public int resetFlight() {
        int latestFlightNo = flightList.get(0).getFlightNo();
        for (Flight f: flightList) {
            if (latestFlightNo < f.getFlightNo()) {
                latestFlightNo = f.getFlightNo();
            }
        }

        return latestFlightNo;
    }

    /**A method to reset Passenger class auto-generated Booking ref*/
    public int resetPassenger() {
        int latestBookingRef = passengerList.get(0).getBookingRef();
        for (Passenger p: passengerList) {
            if (latestBookingRef < p.getBookingRef()) {
                latestBookingRef = p.getBookingRef();
            }
        }

        return latestBookingRef;
    }

    /**
     * A method to swap flight in flightList and in passenger's
     * flightlIst to the passed flight object
     * @param f is the flight that will swapped the flight object in the
     *         arraylist
     */
    public void swapFlight(Flight f) {
        // get the want-to-swap flight's flight no
        int swapFligthNo = f.getFlightNo();
        int index = -1;
        // loop for every flight in travel agent
        for (int i = 0; i<numOfFlight; i++) {
            if (flightList.get(i).getFlightNo() == swapFligthNo) {
                // swap flight
                flightList.set(i, f);
                index = i;
            }
        }

        // if a flight is swapped
        if (index != -1) {
            // get the swapped flight's flight no
            int swappedFlNo = flightList.get(index).getFlightNo();
            // loop for every passenger in travel agent
            for (int i = 0; i<numOfPassenger; i++) {
                Passenger p = passengerList.get(i);
                // loop the passenger itinerary list
                for (int n = 0; n<p.getNumFlights(); n++) {
                    if (p.getFlights().get(n).getFlightNo() == swappedFlNo) {
                        // swap flight
                        p.getFlights().set(n, flightList.get(index));
                    }
                }
            }
        }

    }

    @Override
    public String toString() {
        return "--------------------------------------\n"+
                "Agent : "+name+"\n"+
                "--------------------------------------\n"+
                "Number of Flights\t\t: "+numOfFlight+
                "\n"+
                "Number of Passengers\t: "+numOfPassenger+
                "\n"+
                "--------------------------------------";
    }


}

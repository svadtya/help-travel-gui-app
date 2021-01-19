/**
 * A class to create table model for
 * ArrayList Flight in Passenger
 * @author Siva Aditya (E1800176)
 */

import java.util.*;
import java.time.format.*;
import javax.swing.table.*;
import java.io.Serializable;

public class ItineraryTableModel extends AbstractTableModel
		implements Serializable
{
	private static final String[] colHeader = { "Date",
												"Flight No", 
												"Type", 
												"Origin", 
												"Destination", 
												"DEP - ARR",
												"Number of Movies", 
												"Booking Price (RM)"};
	private Passenger passenger;
	private ArrayList<Flight> bookingList;
	
	// constructor
	public ItineraryTableModel()
	{	
		passenger = null;
		setBookingList(new ArrayList<Flight>());
	}

	// constructor with args
	public ItineraryTableModel(Passenger passenger)
	{	
		this.passenger = passenger;
		setBookingList(passenger.getFlights());
	}

	// getter setter
	public ArrayList<Flight> getBookingList() {return bookingList;}
	public void setBookingList(ArrayList<Flight> bookingList) {
		this.bookingList = bookingList;
	}

	@Override
	public int getRowCount() {return getBookingList().size();}

	@Override
	public int getColumnCount() {return colHeader.length;}

	public Object getValueAt(int row, int column)
	{
		Flight f = getBookingList().get(row);
		switch (column)
		{	
			case 0:
				return f.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyy"));
			case 1:
				return f.getFlightNo();
			case 2:
				return (f instanceof BusinessFlight) ? "Business" : "Economy";
			case 3:
				return f.getOrigin();
			case 4:
				return f.getDestination();
			case 5:
				return f.getDeparr();
			case 6:
				return f.getNumMovies();
			case 7:
				return String.format("%.2f", f.calcPrice(passenger));
			default:
				return "";
		}
	}

	@Override
	public String getColumnName(int column) {return colHeader[column];}

	public void addRow()
	{
		int row = getBookingList().size();
		fireTableRowsInserted(row, row);
	}

	public Flight getElementAt(int row)
	{
		Flight f = getBookingList().get(row);
		return f;
	}

	public Flight removeRow(int row)
	{
		Flight f = (Flight) getBookingList().remove(row);
		// unbook the flight from passenger
		f.unbook(passenger);
		passenger.setNumFlights(getBookingList().size());
		fireTableRowsDeleted(row, row);
		return f;
	}

	public void clear()
	{
		int row = getBookingList().size() - 1;
		getBookingList().clear();
		passenger.setNumFlights(0);
		passenger.setTotalPrice(0);
		if (row >= 0)
			fireTableRowsDeleted(0, row);
	}

	public boolean contains(Flight flight)
	{
		return getBookingList().contains(flight);
	}

	public boolean isEmpty() {return getBookingList().size() == 0;}

}

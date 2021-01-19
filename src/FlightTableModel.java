/**
 * A class to create table model for
 * ArrayList Flight in Travel Agent
 * @author Siva Aditya (E1800176)
 */

import java.util.*;
import java.time.format.*;
import javax.swing.table.*;
import java.io.Serializable;

public class FlightTableModel extends AbstractTableModel
		implements Serializable
{
	private static final String[] colHeader = { "Flight No",
												"Date", 
												"Type", 
												"Origin", 
												"Destination", 
												"DEP - ARR",
												"Number of Movies", 
												"Adult Price (RM)", 
												"Child Price (RM)",
												};
	private TravelAgent tva;
	private ArrayList<Flight> flightList;
	
	// constructor
	public FlightTableModel()
	{	
		tva = null;
		setFlightList(new ArrayList<Flight>());
	}

	// constructor with args
	public FlightTableModel(TravelAgent travelAgent)
	{	
		tva = travelAgent;
		setFlightList(tva.getFlightList());
	}

	// getter setter
	public ArrayList<Flight> getFlightList() {return flightList;}
	public void setFlightList(ArrayList<Flight> flightList) {
		this.flightList = flightList;
	}

	@Override
	public int getRowCount()
	{
		return getFlightList().size();
	}

	@Override
	public int getColumnCount()
	{
		return colHeader.length;
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		Flight f = getFlightList().get(row);
		switch (column)
		{
			case 0:
				return f.getFlightNo();
			case 1:
				return f.getDate().
						format(DateTimeFormatter.ofPattern("dd/MM/yyy"));
			case 2:
				return (f instanceof BusinessFlight)? "Business" : "Economy";
			case 3:
				return f.getOrigin();
			case 4:
				return f.getDestination();
			case 5:
				return f.getDeparr();
			case 6:
				return f.getNumMovies();
			case 7:
				return String.format("%.2f", f.getAdultPrice());
			case 8:
				return String.format("%.2f", f.getChildPrice());
			default:
				return "";
		}
	}

	@Override
	public String getColumnName(int column) {return colHeader[column];}

	public void addRow(Flight flight)
	{
		int row = getFlightList().size();
		getFlightList().add(flight);
		tva.setNumOfFlight(tva.getNumOfFlight()+1);
		fireTableRowsInserted(row, row);
	}

	public Flight getElementAt(int row)
	{
		Flight f = (Flight) getFlightList().get(row);
		return f;
	}

	public Flight removeRow(int row)
	{
		Flight f = (Flight) getFlightList().remove(row);
		getFlightList().remove(f);
		tva.setNumOfFlight(tva.getNumOfFlight()-1);
		fireTableRowsDeleted(row, row);
		return f;
	}

	public void clear()
	{
		int row = getFlightList().size() - 1;
		tva.clearFlightList();
		if (row >= 0)
			fireTableRowsDeleted(0, row);
	}

	public boolean contains(Flight flight)
	{
		return getFlightList().contains(flight);
	}

	public boolean isEmpty() {return getFlightList().size() == 0;}
}

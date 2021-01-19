/**
 * A class to create table model for
 * ArrayList Passenger in Travel Agent
 * @author Siva Aditya (E1800176)
 */

import java.util.*;
import javax.swing.table.*;
import java.io.Serializable;

public class PassengerTableModel extends AbstractTableModel
		implements Serializable
{
	private static final String[] colHeader = { "Booking Ref",
												"Passenger Name", 
												"Number of Adults", 
												"Number of Children", 
												"Booked Flight", 
												"Total Booking Price (RM)"};

	private TravelAgent tva;
	private ArrayList<Passenger> passengerList;

	// constructor
	public PassengerTableModel()
	{	
		tva = null;
		setPassengerList(new ArrayList<Passenger>());
	}

	// constructor with args
	public PassengerTableModel(TravelAgent travelAgent)
	{	
		tva = travelAgent;
		setPassengerList(tva.getPassengerList());
	}

	// getter setter
	public ArrayList<Passenger> getPassengerList() {return passengerList;}
	public void setPassengerList(ArrayList<Passenger> passengerList) {
		this.passengerList = passengerList;
	}

	@Override
	public int getRowCount() {return getPassengerList().size();}

	@Override
	public int getColumnCount() {return colHeader.length;}

	@Override
	public Object getValueAt(int row, int column)
	{
		Passenger p = (Passenger) getPassengerList().get(row);
		switch (column)
		{
			case 0:
				return p.getBookingRef();
			case 1:
				return p.getName();
			case 2:
				return p.getNumAdults();
			case 3:
				return p.getNumChildren();
			case 4:
				return p.getNumFlights();
			case 5:
				return String.format("%.2f", p.getTotalPrice());
			default:
				return "";
		}
	}

	@Override
	public String getColumnName(int column) {return colHeader[column];}

	public void addRow(Passenger passenger)
	{
		int row = getPassengerList().size();
		getPassengerList().add(passenger);
		tva.setNumOfPassenger(tva.getNumOfPassenger()+1);
		fireTableRowsInserted(row, row);
	}

	public Passenger getElementAt(int row)
	{
		Passenger p = (Passenger) getPassengerList().get(row);
		return p;
	}

	public Passenger removeRow(int row)
	{
		Passenger p = (Passenger) getPassengerList().remove(row);
		tva.setNumOfPassenger(tva.getNumOfPassenger()-1);
		fireTableRowsDeleted(row, row);
		return p;
	}

	public void clear()
	{
		int row = getPassengerList().size() - 1;
		tva.clearPassengerList();
		if (row >= 0)
			fireTableRowsDeleted(0, row);
	}

	public boolean contains(Passenger passenger)
	{
		return getPassengerList().contains(passenger);
	}

	public boolean isEmpty()
	{
		return getPassengerList().size() == 0;
	}
}
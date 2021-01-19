/**
 * A class to create Menu Book & Itinerary panel
 * @author Siva Aditya (E1800176)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class BookItineraryMenuPanel extends JPanel implements ActionListener
{	
	// parent frame component
	private TravelAgent tva;
	private int passBookedFlight;
	private TravelGUI gui;
	private JFrame mainFrame;
	// last condition
	private Passenger passenger;

	// panel component
	private JLabel passFound;
	private JTextField totalBookPrc, bookedFlight;
	private JButton bookFlightBtn, showFlightBtn,
			editPassBtn, removeFlightBtn, clearItiBtn;

	// passenger
	private JTextField bookingRef;
	private JButton okBtn, clearBtn;

	// table
	private JTable itineraryTable;
	private ItineraryTableModel itineraryTabMod;

	// FORMAT & FONT PACK
	final DefaultTableCellRenderer CENTERCELL;
	final DefaultTableCellRenderer RIGHTCELL;
	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);
	final Font CALIBRI18 = new Font("Calibri", Font.PLAIN, 18);
	final Font CALIBRI22 = new Font("Calibri", Font.PLAIN, 22);

	public BookItineraryMenuPanel(TravelAgent travelAgent,
								  TravelGUI gui, Passenger tvaPass)
	{	
		// parent frame component
		tva = travelAgent;
		this.gui = gui;
		mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

		// check whether passenger still exist collection
		// if not, passenger is set to null
		if (tvaPass != null) 
			passenger = tva.findPassenger(tvaPass.getBookingRef());
			
		// TITLE
		JPanel title = new JPanel(new FlowLayout());
		JLabel menuTitle = new JLabel("Book & Itinerary");
		menuTitle.setFont(CALIBRI22);
		title.add(menuTitle);

		// PASSENGER SECTION

		// passenger input button
		JPanel passButton;
		passButton = new JPanel(new FlowLayout(FlowLayout.CENTER
			,15,0));
		okBtn = new JButton("OK");
		okBtn.setFont(CALIBRI14);
		okBtn.setBackground(Color.WHITE);
		clearBtn = new JButton("Clear");
		clearBtn.setFont(CALIBRI14);
		clearBtn.setBackground(Color.WHITE);
		passButton.add(okBtn);
		passButton.add(clearBtn);
		okBtn.addActionListener(this);
		clearBtn.addActionListener(this);

		// passenger input text panel
		JPanel passInpTextPn;
		passInpTextPn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel passInpText = new JLabel("Passenger Booking Ref");
		passInpText.setFont(CALIBRI18);
		passInpTextPn.add(passInpText);

		// passenger input field panel
		JPanel passInpFieldPn = new JPanel(new FlowLayout());
		bookingRef = new JTextField(18);
		bookingRef.setFont(CALIBRI18);
		bookingRef.setHorizontalAlignment(JTextField.CENTER);
		passInpFieldPn.add(bookingRef);

		// passenger detail panel
		JPanel passDetailPn;
		passDetailPn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		passFound = new JLabel("Please select a passenger!");
		passFound.setFont(CALIBRI18);
		passFound.setForeground(Color.RED);
		passDetailPn.add(passFound);

		// passenger top panel
		JPanel passTopPn;
		passTopPn = new JPanel(new GridLayout(0,3));
		passTopPn.add(passInpTextPn);
		passTopPn.add(passInpFieldPn);
		passTopPn.add(passDetailPn);

		// passenger main panel
		JPanel passPanel;
		passPanel = new JPanel(new GridLayout(2,0,0,2));
		passPanel.add(passTopPn);
		passPanel.add(passButton);

		// passenger component
		JPanel pass = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pass.add(passPanel);

		// NORTH PANEL
		JPanel northPanel = new JPanel(new GridLayout(0,1));
		northPanel.add(title);
		northPanel.add(pass);

		// TOTAL PRICE SECTION
		JPanel totalPricePn;
		totalPricePn = new JPanel(new FlowLayout(FlowLayout.LEFT
			,15,0));
		JLabel totalBookLbl = new JLabel("Total Booking Price: ");
		totalBookLbl.setFont(CALIBRI18);
		JLabel currencyLbl = new JLabel("RM");
		currencyLbl.setFont(CALIBRI18);
		JTextField totalBookPrc = new JTextField("-",8);
		totalBookPrc.setFont(CALIBRI18);
		totalBookPrc.setHorizontalAlignment(JTextField.CENTER);
		totalBookPrc.setEditable(false);
		totalPricePn.add(totalBookLbl);
		totalPricePn.add(currencyLbl);
		totalPricePn.add(totalBookPrc);

		// NUMBER OF BOOKED FLIGHT SECTION
		JPanel boookedFlPn;
		boookedFlPn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel bookLbl = new JLabel("Booked Flight");
		bookLbl.setFont(CALIBRI18);
		bookedFlight = new JTextField("-",4);
		bookedFlight.setFont(CALIBRI18);
		bookedFlight.setHorizontalAlignment(JTextField.CENTER);
		bookedFlight.setEditable(false);
		boookedFlPn.add(bookLbl);
		boookedFlPn.add(bookedFlight);

		// ITINERARY DETAIL PANEL
		JPanel itineraryDetailPn = new JPanel(new FlowLayout(
				FlowLayout.CENTER,20,0));
		itineraryDetailPn.add(totalPricePn);
		itineraryDetailPn.add(boookedFlPn);

		// setting up table
		if (passenger == null)
			itineraryTabMod = new ItineraryTableModel();
		else {
			// check whether flight collection is updated &
			// update passenger itinerary list accordingly
			for (Flight f: passenger.getFlights()) {
				if (!tva.findFlight(f)) {
					passenger.removeFlight(f.getFlightNo());
					break;
				}
			}
			// sort the data
			passenger.sortItinerary();
			itineraryTabMod = new ItineraryTableModel(passenger);
		}
		itineraryTable = new JTable(itineraryTabMod);
		itineraryTable.setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		itineraryTable.setFont(CALIBRI18);
		CENTERCELL = new DefaultTableCellRenderer();
		CENTERCELL.setHorizontalAlignment(JLabel.CENTER);
		RIGHTCELL = new DefaultTableCellRenderer();
		RIGHTCELL.setHorizontalAlignment(JLabel.RIGHT);
		itineraryTable.getColumnModel().
				getColumn(0).setCellRenderer(CENTERCELL);
		itineraryTable.getColumnModel().
				getColumn(1).setCellRenderer(CENTERCELL);
		itineraryTable.getColumnModel().
				getColumn(2).setCellRenderer(CENTERCELL);
		itineraryTable.getColumnModel().
				getColumn(3).setCellRenderer(CENTERCELL);
		itineraryTable.getColumnModel().
				getColumn(4).setCellRenderer(CENTERCELL);
		itineraryTable.getColumnModel().
				getColumn(5).setCellRenderer(CENTERCELL);
		itineraryTable.getColumnModel().
				getColumn(6).setCellRenderer(CENTERCELL);
		itineraryTable.getColumnModel().
				getColumn(7).setCellRenderer(RIGHTCELL);
		JScrollPane tabPane = new JScrollPane(itineraryTable);		

		// CENTER PANEL
		JPanel midPanel = new JPanel(new BorderLayout());
		midPanel.add(new JSeparator(
				JSeparator.HORIZONTAL), BorderLayout.NORTH);
		midPanel.add(tabPane, BorderLayout.CENTER);
		midPanel.add(itineraryDetailPn, BorderLayout.SOUTH);

		// BUTTON PANEL
		JPanel buttonPanel;
		buttonPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER,20,0));
		bookFlightBtn = new JButton("Book Flight to Itinerary");
		bookFlightBtn.setFont(CALIBRI14);
		bookFlightBtn.setBackground(Color.WHITE);
		showFlightBtn = new JButton("Show Flight Details");
		showFlightBtn.setFont(CALIBRI14);
		showFlightBtn.setBackground(Color.WHITE);
		editPassBtn = new JButton("Show/Edit Passenger Details");
		editPassBtn.setFont(CALIBRI14);
		editPassBtn.setBackground(Color.WHITE);
		removeFlightBtn = new JButton("Remove Flight From Itinerary");
		removeFlightBtn.setFont(CALIBRI14);
		removeFlightBtn.setBackground(Color.WHITE);
		clearItiBtn = new JButton("Clear Itinerary");
		clearItiBtn.setFont(CALIBRI14);
		clearItiBtn.setBackground(Color.WHITE);
		buttonPanel.add(bookFlightBtn);
		buttonPanel.add(showFlightBtn);
		buttonPanel.add(editPassBtn);
		buttonPanel.add(removeFlightBtn);
		buttonPanel.add(clearItiBtn);
		bookFlightBtn.addActionListener(this);
		showFlightBtn.addActionListener(this);
		editPassBtn.addActionListener(this);
		removeFlightBtn.addActionListener(this);
		clearItiBtn.addActionListener(this);

		// setting up data
		if (passenger != null) {
			bookingRef.setText(
					String.valueOf(passenger.getBookingRef()));
			passFound.setText(passenger.getName());
			passFound.setForeground(Color.BLACK);
			totalBookPrc.setText(
					String.format("%.2f", passenger.calcBookingPrice()));
			totalBookPrc.setHorizontalAlignment(JTextField.LEFT);
			bookedFlight.setText(String.valueOf(
					passenger.getNumFlights()));
		}

		// SOUTH PANEL
		JPanel southPanel = new JPanel(new GridLayout(0,1));
		southPanel.add(new Label(""));
		southPanel.add(buttonPanel);
		southPanel.add(new Label(""));

		// MENU PANEL LAYOUT
		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		add(new Label(""), BorderLayout.WEST);
		add(midPanel, BorderLayout.CENTER);
		add(new Label(""), BorderLayout.EAST);
		add(southPanel, BorderLayout.SOUTH);
		setSize(800,550);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Select a passenger
		if (ae.getSource() == okBtn) {
			String bookRefVal = bookingRef.getText().trim();

			// Booking ref validation
			boolean validBookRefVal = true;
			try {
				int bookRef = Integer.parseInt(bookRefVal);
			} catch(NumberFormatException e) {
				passFound.setText("ERROR: Invalid Booking Ref!");
				passFound.setForeground(Color.RED);
				validBookRefVal = false;
			}

			if (validBookRefVal) {
				passFound.setText("");
				passFound.setForeground(Color.BLACK);

				Passenger p = tva.findPassenger(
						Integer.parseInt(bookRefVal));
				if (p != null) {
					JOptionPane.showMessageDialog(this, 
						"Passenger ["+p.getBookingRef()+"] " +
								"selected!");
					gui.refreshBookItiMenu(p);
				} else  {
					JOptionPane.showMessageDialog(this,
							"Passenger not found!",
						"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		// Clear passenger selection
		else if (ae.getSource() == clearBtn) {
			// refresh panel
			gui.refreshBookItiMenu(passenger);
		}

		// Book flight to itinerary
		else if (ae.getSource() == bookFlightBtn) {
			// selection validation
			if (passenger == null) {
				JOptionPane.showMessageDialog(this,
						"Please enter valid booking ref first!",
					"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				// prompt to input flight no
				String flNo = JOptionPane.showInputDialog("Enter " +
						"Flight No :");

				// Flight no validation
				int flightNo;
				try {
					flightNo = Integer.parseInt(flNo.trim());

					Flight f = tva.findFlight(flightNo);
					if (f == null) {
						JOptionPane.showMessageDialog(this, 
							"Flight not found!",
						"Error", JOptionPane.ERROR_MESSAGE);
					} else
						processBookingFlight(f); // book the flight

				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(this,
							"Canceled!");

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this,
							"Invalid Flight No!",
					"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		// Show flight details
		else if (ae.getSource() == showFlightBtn) {
			// action is allowed
			if (isValidToEditItineraryList('F')) {
				int rowIndex = itineraryTable.getSelectedRow();
				if (rowIndex != -1) {
					Flight f = itineraryTabMod.getElementAt(rowIndex);
					// display flight details dialog
					// (with booking price)
					FlightDetailsDialog flDetail; 
					flDetail = new FlightDetailsDialog(mainFrame,f,
							passenger);
					flDetail.setLocationRelativeTo(this);
					flDetail.setVisible(true);

				} else 
					JOptionPane.showMessageDialog(this,
							"Please select a flight");
			}
		}

		// Show/edit passenger details
		else if (ae.getSource() == editPassBtn) {
			// action is allowed
			if (isValidToEditItineraryList('P')) {
				// display passenger form dialog
				PassengerFormDialog editDialog;
				editDialog = new PassengerFormDialog(mainFrame);
				// set passenger dialog
				editDialog.setPassenger(passenger);
				// fill the dialog's textfield with
				// the passenger's details
				editDialog.passName.setText(passenger.getName());
				editDialog.numAdults.setText(
						String.valueOf(passenger.getNumAdults()));
				editDialog.numChildren.setText(
						String.valueOf(passenger.getNumChildren()));
				editDialog.setLocationRelativeTo(this);
				editDialog.setVisible(true);
				// refresh panel
				gui.refreshBookItiMenu(passenger);
			}
		}

		// Remove flight from itinerary
		else if (ae.getSource() == removeFlightBtn) {
			// action is allowed
			if (isValidToEditItineraryList('R')) {
				int rowIndex = itineraryTable.getSelectedRow();
				if (rowIndex != -1) {
					Flight f = itineraryTabMod.getElementAt(rowIndex);
					int remove = JOptionPane.showConfirmDialog(this,
						"Are you sure " +
								"you want to remove " +
								"Flight ["+f.getFlightNo()+"] " +
								"from Itinerary List?",
						"Confirm Remove", JOptionPane.YES_NO_OPTION);
					if (remove == JOptionPane.YES_OPTION){
						itineraryTabMod.removeRow(rowIndex);
						// refresh panel
						gui.refreshBookItiMenu(passenger);
					}
				} else
					JOptionPane.showMessageDialog(this,
							"Please select a flight");
			}
		}

		// Clear itinerary
		else if (ae.getSource() == clearItiBtn) {
			// action is allowed
			if (isValidToEditItineraryList('C')) {
				int remove = JOptionPane.showConfirmDialog(this,
						"Are you sure " +
								"you want to clear your Itinerary List?",
						"Confirm Clear Itinerary List",
						JOptionPane.YES_NO_OPTION);
				if (remove == JOptionPane.YES_OPTION){
					itineraryTabMod.clear();
					// refresh panel
					gui.refreshBookItiMenu(passenger);
				}
			}
		}

	}

	/**
	 * A method to validate whether user action directed to modify
	 * the content of itinerary list is allowed
	 * @param act indicates the user action to the list
	 * @return boolean indicating if it is allowed or not
	 */
	public boolean isValidToEditItineraryList(char act) {
		// passenger is not selected
		if (passenger == null) {
			JOptionPane.showMessageDialog(this,
					"Please enter a valid booking ref first!",
				"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// itinerary list is empty
		// (validates for every action except show/edit passenger details)
		else if (passenger.getNumFlights() == 0 && act != 'P') {
			JOptionPane.showMessageDialog(this,
					"Your Itinerary List is empty!",
			"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} 

		else
			return true;
	}

	/**
	 * A method to process the booking of a chosen flight
	 * @param f is the chosen flight
	 */
	public void processBookingFlight(Flight f) {
		// create custom JDialog for booking event
		Object[] options = {"Book Flight",
							"Cancel",
							"Show Flight ["+f.getFlightNo()+"] " +
									"Details"};

		// display the dialog
		int opt = JOptionPane.showOptionDialog(this,
				"Do you want to book " +
						"Flight ["+f.getFlightNo()+"]?",
				"Booking Flight",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, options, options[2]);

		// proceed to book
		if (opt == 0)  {
			// book the flight
			Flight bookFl = f.book(passenger);

			// flight is already booked, display error message
			if (bookFl == null) {
				JOptionPane.showMessageDialog(this,
						"You have booked this flight!",
				"Error", JOptionPane.ERROR_MESSAGE);
			
			}
			// flight is successfully booked
			else if (bookFl.equals(f)) {
				JOptionPane.showMessageDialog(this, 
					"Flight ["+f.getFlightNo()+"] " +
							"successfully added to itinerary!");
				// refresh panel
				gui.refreshBookItiMenu(passenger);
			
			}
			// flight is conflict with other flight in itinerary
			else
				conflictBookingFlight(f,bookFl); // handle the conflict

		}

		// show flight details
		else if (opt == 2){
			// display chosen flight details dialog
			FlightDetailsDialog flDetail; 
			flDetail = new FlightDetailsDialog(mainFrame,f,passenger);
			flDetail.setLocationRelativeTo(this);
			flDetail.setVisible(true);
			// display booking dialog again
			// after closing details dialog
			processBookingFlight(f);
		}

		// cancel book
		 else 
			JOptionPane.showMessageDialog(this,
					"Booking Canceled!");
	}

	/**
	 * A method to handle and resolve booking conflict event
	 * @param fl1 is the selected flight to book
	 * @param fl2 is the flight in the itinerary that conflicts
	 */
	public void conflictBookingFlight(Flight fl1, Flight fl2) {
		int fl1No = fl1.getFlightNo(); // trying-to-book flight
		int fl2No = fl2.getFlightNo(); // conflict flight

		// create custom JDialog for booking conflict event
		Object[] options = {"Remove Flight ["+fl2No+"] from Itinerary",
							"Cancel on Booking Flight ["+fl1No+"]",
							"Show Flight ["+fl1No+"] Details",
							"Show Flight ["+fl2No+"] Details"};

		// display the dialog
		int opt = JOptionPane.showOptionDialog(this,
				"Flight ["+fl1No+"]'s date conflicts " +
						"with Flight ["+fl2No+"] in your Itinerary. "+
				"What do you want to do?", 
				"Booking Flight Conflict",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[3]);

		// remove the conflicting flight from itinerary
		// and continue booking
		if (opt == 0) {
			// confirmation
			int remove = JOptionPane.showConfirmDialog(this,
						"Are you sure " +
								"you want to remove Flight ["+fl2No+"] "+
						"from your itinerary?",
						"Confirm Delete",
					JOptionPane.YES_NO_OPTION);
			if (remove == JOptionPane.NO_OPTION) {
				conflictBookingFlight(fl1,fl2); // loop
			}

			else {
				// remove the conflicting flight
				passenger.removeFlight(fl2No);
				// book the chosen flight again
				fl1.book(passenger);
				JOptionPane.showMessageDialog(this, 
					"Flight ["+fl1No+"] " +
							"successfully added to itinerary!");
				// refresh the panel
				gui.refreshBookItiMenu(passenger);
			}
		}

		// show the chosen flight details
		else if (opt == 2) {
			// display trying-to-book flight details dialog
			FlightDetailsDialog fl1Detail; 
			fl1Detail = new FlightDetailsDialog(mainFrame,fl1,passenger);
			fl1Detail.setLocationRelativeTo(this);
			fl1Detail.setVisible(true);
			// display booking conflict dialog again
			// after closing details dialog
			conflictBookingFlight(fl1, fl2);
		}

		// show the conflicting flight details
		else if (opt == 3){
			// display conflict flight details dialog
			FlightDetailsDialog fl2Detail;
			fl2Detail = new FlightDetailsDialog(mainFrame,fl2,passenger);
			fl2Detail.setLocationRelativeTo(this);
			fl2Detail.setVisible(true);
			// display booking conflict dialog again
			// after closing details dialog
			conflictBookingFlight(fl1, fl2);
		}

		// cancel on booking
		else 
			JOptionPane.showMessageDialog(this,
					"Booking Canceled!");
	}

	// getter setter
	public Passenger getPassenger() {return passenger;}
	public void setPassenger(Passenger p) {passenger = p;}
}
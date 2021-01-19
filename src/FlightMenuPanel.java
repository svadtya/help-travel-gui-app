/**
 * A class to create Menu Flight panel
 * @author Siva Aditya (E1800176)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.time.format.*;

public class FlightMenuPanel extends JPanel
		implements ItemListener, ActionListener
{
	// parent frame component
	private TravelAgent tva;
	private int tvaFlight;
	private TravelGUI gui;
	private JFrame mainFrame;
	// last condition
	private char sort;

	// panel component
	private JTextField numOfFlight;
	private JButton addFlightBtn, showFlightBtn,
			editFlightBtn, removeFlightBtn, clearBtn;
	private String[] sortChoice = {"Date", "Flight No", "Type"};
	private JComboBox sortList;

	// table
	private JTable flightTable;
	private FlightTableModel flightTableMod;

	// FORMAT & FONT PACK
	final DateTimeFormatter DATEFORMAT;
	final DefaultTableCellRenderer CENTERCELL;
	final DefaultTableCellRenderer RIGHTCELL;
	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);
	final Font CALIBRI18 = new Font("Calibri", Font.PLAIN, 18);
	final Font CALIBRI22 = new Font("Calibri", Font.PLAIN, 22);

	public FlightMenuPanel(TravelAgent travelAgent,
						   TravelGUI gui, char sort)
	{	
		// parent frame component
		tva = travelAgent;
		this.gui = gui;
		mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.sort = sort;
		DATEFORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// TITLE
		JPanel title = new JPanel(new FlowLayout());
		JLabel menuTitle = new JLabel("Flight in Collection");
		menuTitle.setFont(CALIBRI22);
		title.add(menuTitle);

		// NORTH PANEL
		JPanel northPanel = new JPanel(new GridLayout(0,1));
		northPanel.add(title);
		northPanel.add(new JLabel(""));

		// SORT SECTION
		JPanel sortSect = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel sortLbl = new JLabel("Sort");
		sortLbl.setFont(CALIBRI14);
		sortList = new JComboBox(sortChoice);
		sortList.setEditable(false);
		sortList.setPrototypeDisplayValue("travel agent flight");
		// set sort according to panel last recorded sort value
		switch (sort)
		{
			case 'D': sortList.setSelectedIndex(0); break;
			case 'F': sortList.setSelectedIndex(1); break;
			case 'T': sortList.setSelectedIndex(2); break;
		}
		sortList.setFont(CALIBRI14);
		sortSect.add(sortLbl);
		sortSect.add(sortList);
		sortList.addItemListener(this);

		// NUMBER OF FLIGHT SECTION
		JPanel numFlightSect;
		numFlightSect = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel numFlight = new JLabel("Number of Flight");
		numFlight.setFont(CALIBRI14);
		numFlightSect.add(numFlight);
		tvaFlight = tva.getNumOfFlight();
		numOfFlight = new JTextField(String.format("%s",tvaFlight),4);
		numOfFlight.setHorizontalAlignment(JTextField.CENTER);
		numOfFlight.setEditable(false);
		numOfFlight.setFont(CALIBRI14);
		numFlightSect.add(numOfFlight);

		// SORT PANEL
		JPanel midTopPanel;
		midTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		midTopPanel.add(sortSect);
		midTopPanel.add(new Label("     "));
		midTopPanel.add(numFlightSect);

		// setting up table
		flightTableMod = new FlightTableModel(tva);
		flightTable = new JTable(flightTableMod);
		flightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		flightTable.setFont(CALIBRI14);
		CENTERCELL = new DefaultTableCellRenderer();
		CENTERCELL.setHorizontalAlignment(JLabel.CENTER);
		RIGHTCELL = new DefaultTableCellRenderer();
		RIGHTCELL.setHorizontalAlignment(JLabel.RIGHT);
		flightTable.getColumnModel().
				getColumn(0).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().
				getColumn(1).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().
				getColumn(2).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().
				getColumn(3).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().
				getColumn(4).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().
				getColumn(5).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().
				getColumn(6).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().
				getColumn(7).setCellRenderer(RIGHTCELL);
		flightTable.getColumnModel().
				getColumn(8).setCellRenderer(RIGHTCELL);
		JScrollPane tabPane = new JScrollPane(flightTable);

		// CENTER PANEL
		JPanel midPanel = new JPanel(new BorderLayout());
		midPanel.add(midTopPanel, BorderLayout.NORTH);
		midPanel.add(tabPane, BorderLayout.CENTER);

		// MENU BUTTON
		JPanel buttonPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER,15,0));
		addFlightBtn = new JButton("Add New Flight");
		addFlightBtn.setFont(CALIBRI18);
		addFlightBtn.setBackground(Color.WHITE);
		showFlightBtn = new JButton("Show Flight Details");
		showFlightBtn.setFont(CALIBRI18);
		showFlightBtn.setBackground(Color.WHITE);
		editFlightBtn = new JButton("Edit Flight Details");
		editFlightBtn.setFont(CALIBRI18);
		editFlightBtn.setBackground(Color.WHITE);
		removeFlightBtn = new JButton("Remove Flight");
		removeFlightBtn.setFont(CALIBRI18);
		removeFlightBtn.setBackground(Color.WHITE);
		clearBtn = new JButton("Clear Collection");
		clearBtn.setFont(CALIBRI18);
		clearBtn.setBackground(Color.WHITE);
		buttonPanel.add(addFlightBtn);
		buttonPanel.add(showFlightBtn);
		buttonPanel.add(editFlightBtn);
		buttonPanel.add(removeFlightBtn);
		buttonPanel.add(clearBtn);
		addFlightBtn.addActionListener(this);
		showFlightBtn.addActionListener(this);
		editFlightBtn.addActionListener(this);
		removeFlightBtn.addActionListener(this);
		clearBtn.addActionListener(this);

		// SOUTH PANEL
		JPanel southPanel = new JPanel (new GridLayout(3,0,12,0));
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
		setSize(new Dimension(800,550));
	}

	// Sort ComboBox Listener
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource() == sortList) {
			 if (sortList.getSelectedItem() == "Date") {
			 	// sort flight collection
				tva.sortFlightByDate();
				// refresh panel
				gui.refreshFlightMenu('D');
			
			} 

			else if (sortList.getSelectedItem() == "Flight No") {
				// sort flight collection
				tva.sortFlightByNo();
				// refresh panel
				gui.refreshFlightMenu('F');
			
			}

			else if (sortList.getSelectedItem() == "Type") {
				// sort flight collection
				tva.sortFlightByType();
				// refresh panel
				gui.refreshFlightMenu('T');
			}
		} 
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Add flight to collection
		if (ae.getSource() == addFlightBtn)
		{
			// prompt to choose flight type
			FlightTypeDialog inpFlType = new FlightTypeDialog(mainFrame);
			inpFlType.setLocationRelativeTo(this);
			inpFlType.setVisible(true);

			char type = inpFlType.getFlightType();
			if (type == 'B') {
				addBusinessFlight();
			
			} else if (type == 'E') {
				addEconomyFlight();
			
			} else 
				JOptionPane.showMessageDialog(this,
						"Canceled!");
		}

		// Show flight details
		else if (ae.getSource() == showFlightBtn)
		{
			if (flightTable.getRowCount() == 0)
				JOptionPane.showMessageDialog(this,
						"Flight is not added");
			else {
				int rowIndex = flightTable.getSelectedRow();
				if (rowIndex != -1) {
					Flight f = flightTableMod.getElementAt(rowIndex);
					// display flight details dialog
					// (without booking price)
					FlightDetailsDialog flDetail; 
					flDetail = new FlightDetailsDialog(mainFrame,f,
							null);
					flDetail.setLocationRelativeTo(this);
					flDetail.setVisible(true);

				} else 
					JOptionPane.showMessageDialog(this,
							"Please select a row");
			}
		}

		// Edit flight details
		else if (ae.getSource() == editFlightBtn)
		{
			if (flightTable.getRowCount() == 0)
				JOptionPane.showMessageDialog(this,
						"Flight is not added");
			else {
				int rowIndex = flightTable.getSelectedRow();
				if (rowIndex != -1) {
					Flight f = flightTableMod.getElementAt(rowIndex);
					if (f instanceof BusinessFlight)
						editBusinessFlight(f);
					else editEconomyFlight(f);

				} else 
					JOptionPane.showMessageDialog(this,
							"Please select a row");
			}
		}

		// Remove flight
		else if (ae.getSource() == removeFlightBtn)
		{
			if (flightTable.getRowCount() == 0)
				JOptionPane.showMessageDialog(this,
						"Flight is not added");
			else {
				int rowIndex = flightTable.getSelectedRow();
				if (rowIndex != -1) {
					Flight f = flightTableMod.getElementAt(rowIndex);
					JOptionPane.showMessageDialog(this,
						"Deleting this flight will " +
								"also remove this flight "+
						"from every passenger's booking list",
							"Delete Warning",
						JOptionPane.WARNING_MESSAGE);
					int remove = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to delete " +
								"Flight ["+f.getFlightNo()+"]?",
						"Confirm Delete", JOptionPane.YES_NO_OPTION);
					if (remove == JOptionPane.YES_OPTION){
						flightTableMod.removeRow(rowIndex);
						// refresh panel
						gui.refreshFlightMenu(sort);
					}
				} else
					JOptionPane.showMessageDialog(this,
							"Please select a row");
			}
		}

		// Clear collection
		else if (ae.getSource() == clearBtn)
		{
			if (flightTable.getRowCount() == 0)
				JOptionPane.showMessageDialog(this,
						"Flight is not added");
			else {
				// warn message
				JOptionPane.showMessageDialog(this,
						"Clearing Flight Collection " +
								"will also clear "+
						"every passenger's itinerary list subsequently",
						"Clear Collection Warning",
						JOptionPane.WARNING_MESSAGE);
				int remove = JOptionPane.showConfirmDialog(this,
					"Are you sure you want to " +
							"clear Flight Collection?",
					"Confirm Clear Collection",
						JOptionPane.YES_NO_OPTION);
				if (remove == JOptionPane.YES_OPTION){
					flightTableMod.clear();
					// refresh panel
					gui.refreshFlightMenu(sort);
				}
			}
		}
	}

	/**A method to add Business Flight to Collection*/
	public void addBusinessFlight()
	{
		// display business flight form dialog
		BsFlightFormDialog inputDialog;
		inputDialog = new BsFlightFormDialog(mainFrame);
		inputDialog.setLocationRelativeTo(this);
		inputDialog.setVisible(true);

		BusinessFlight newBsFlight = inputDialog.getFlight();

		// check whether flight has been added to collection
		if (newBsFlight != null && !(tva.findFlight(newBsFlight))) {

			// option to add movie
			int add = JOptionPane.showConfirmDialog(this,
					"Do you want to add movie to this Flight?",
					"Add Movie", JOptionPane.YES_NO_OPTION);

			if (add == JOptionPane.YES_OPTION) {
				// adding movie loop since business flight can have
				// multiple movies
				boolean stop = false;
				do {
					// display movie form dialog
					MovieFormDialog inpMvDialog;
					inpMvDialog = new MovieFormDialog(mainFrame);
					inpMvDialog.setLocationRelativeTo(this);
					inpMvDialog.setVisible(true);

					Movie newMv = inpMvDialog.getMovie();

					if (newMv != null) {
						int again;
						// validation
						if (newBsFlight.checkMovie(newMv)) {
							again = JOptionPane.showConfirmDialog(
									this,
							"Sorry, movie with the same title " +
									"has been added to your movie list. "+
							"Do you want to add another movie?",
							"Problem adding movie",
									JOptionPane.YES_NO_OPTION);
						} else {
							// choice to loop
							newBsFlight.getMovieList().add(newMv);
							newBsFlight.setNumMovies(
									newBsFlight.getNumMovies()+1);
							again = JOptionPane.showConfirmDialog(
									this,
							newMv.getTitle()+" added! " +
									"Do you want to add another?",
							"Add Movie",
									JOptionPane.YES_NO_OPTION);
						}
						
						stop = !(again == JOptionPane.YES_OPTION);

					} else {stop = true;}

					// if loop stop,
					// show how many movie has been added to flight
					if (stop && newBsFlight.getNumMovies() > 0) {
						JOptionPane.showMessageDialog(this, 
						"This flight now has "+
								newBsFlight.getNumMovies()+" "+
						"available movie(s)!");
					}

				} while (!stop);
			}
			
			JOptionPane.showMessageDialog(this, 
					"Flight added to collection! Flight No: "
							+newBsFlight.getFlightNo());
			flightTableMod.addRow(newBsFlight);
			// sort table data
			if (sort == 'D') {tva.sortFlightByDate();}
			else if (sort == 'F') {tva.sortFlightByNo();}
			else if (sort == 'T') {tva.sortFlightByType();}
			// refresh panel
			gui.refreshFlightMenu(sort);
		}

		else if (newBsFlight == null)
			JOptionPane.showMessageDialog(this,
					"Canceled!");
		else{
			// flight with mostly similar details has been added to
			// collection, display error message
			JOptionPane.showMessageDialog(this,
					"Flight with the same type " +
							"and details has been added to collection!",
			"Error in Adding Flight", JOptionPane.ERROR_MESSAGE);
			// reset the auto-generated flight no
			Flight.resetFlightNo(tvaFlight);
		}

		
	}

	/**A method to add Economy Flight to Collection*/
	public void addEconomyFlight()
	{
		// display business flight form dialog
		EcoFlightFormDialog inputDialog;
		inputDialog = new EcoFlightFormDialog(mainFrame);
		inputDialog.setLocationRelativeTo(this);
		inputDialog.setVisible(true);

		EconomyFlight newEcoFlight = inputDialog.getFlight();

		// check whether flight has been added to collection
		if (newEcoFlight != null && !(tva.findFlight(newEcoFlight))) {

			// option to add movie
			int add = JOptionPane.showConfirmDialog(this,
					"Do you want to add movie to this Flight?",
					"Add Movie", JOptionPane.YES_NO_OPTION);
			
			if (add == JOptionPane.YES_OPTION) {
				// display movie form dialog
				MovieFormDialog inpMvDialog;
				inpMvDialog = new MovieFormDialog(mainFrame);
				inpMvDialog.setLocationRelativeTo(this);
				inpMvDialog.setVisible(true);

				Movie newMv = inpMvDialog.getMovie();
				if (newMv != null) {
					newEcoFlight.getMovieList().add(newMv);
					newEcoFlight.setNumMovies(
							newEcoFlight.getNumMovies()+1);
					JOptionPane.showMessageDialog(this, 
					newMv.getTitle()+
							" is now available in this flight!");
				}	
			}
			
			JOptionPane.showMessageDialog(this, 
					"Flight added to collection! " +
							"Flight No: "+newEcoFlight.getFlightNo());
			flightTableMod.addRow(newEcoFlight);
			// sort table data
			if (sort == 'D') {tva.sortFlightByDate();}
			else if (sort == 'F') {tva.sortFlightByNo();}
			else if (sort == 'T') {tva.sortFlightByType();}
			// refresh panel
			gui.refreshFlightMenu(sort);
		}

		else if (newEcoFlight == null)
			JOptionPane.showMessageDialog(this,
					"Canceled!");

		else{
			JOptionPane.showMessageDialog(this,
					"Flight with the same type and details " +
							"has been added to collection!",
			"Error in Adding Flight", JOptionPane.ERROR_MESSAGE);
			Flight.resetFlightNo(tvaFlight);
		}
	}

	/**
	 * A method to edit Business Flight details
	 * @param fl is chosen flight
	 */
	public void editBusinessFlight(Flight fl)
	{
		// display business flight form dialog
		BsFlightFormDialog editDialog = new BsFlightFormDialog(mainFrame);
		BusinessFlight f = (BusinessFlight) fl;
		// set passed flight to dialog flight
		editDialog.setFlight(f);
		// fill the dialog's textfield with the flight's details
		editDialog.ori.setText(f.getOrigin());
		editDialog.dest.setText(f.getDestination());
		editDialog.date.setText(f.getDate().format(DATEFORMAT));
		editDialog.dep.setText(f.getDeparr().substring(0,5));
		editDialog.arr.setText(f.getDeparr().substring(8));
		editDialog.price.setText(String.valueOf(f.getPrice()));
		editDialog.childPerc.setText(String.valueOf(f.getChildPerc()));
		editDialog.rate.setText(String.valueOf(f.getRate()));
		editDialog.setLocationRelativeTo(this);
		editDialog.setVisible(true);
		// swap flight in collection with the edited flight details
		tva.swapFlight(f);
		// refresh panel
		gui.refreshFlightMenu(sort);
	}

	/**
	 * A method to edit Economy Flight details
	 * @param fl is chosen flight
	 */
	public void editEconomyFlight(Flight fl)
	{
		// display business flight form dialog
		EcoFlightFormDialog editDialog;
		editDialog = new EcoFlightFormDialog(mainFrame);
		// set passed flight to dialog flight
		editDialog.setFlight((EconomyFlight) fl);
		// fill the dialog's textfield with the flight's details
		editDialog.ori.setText(fl.getOrigin());
		editDialog.dest.setText(fl.getDestination());
		editDialog.date.setText(fl.getDate().format(DATEFORMAT));
		editDialog.dep.setText(fl.getDeparr().substring(0,5));
		editDialog.arr.setText(fl.getDeparr().substring(8));
		editDialog.price.setText(String.valueOf(fl.getPrice()));
		editDialog.childPerc.setText(String.valueOf(fl.getChildPerc()));
		editDialog.setLocationRelativeTo(this);
		editDialog.setVisible(true);
		// swap flight in collection with the edited flight details
		tva.swapFlight(fl);
		// refresh panel
		gui.refreshFlightMenu(sort);
	}

	// getter setter
	public char getSort() {return sort;}
	public void setSort(char sort) {this.sort = sort;}
}
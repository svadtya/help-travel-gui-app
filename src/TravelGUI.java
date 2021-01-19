/**
 * Name			: I Made Siva Aditya Surya
 * Student ID	: E1800176
 *
 * The TravelGUI class serves as the main interface of the program.
 * The main method of this class creates a JFrame instance that is used to
 * display the menu and sub-menu interface of the program for which the
 * user can interact with.
 *
 * @author Siva Aditya (E1800176)
 * @version 2.0.6
 * @since 30 April 2020
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class TravelGUI extends JFrame implements ActionListener
{
	// frame attributes
	private TravelAgent tva;
	private String tvaName;

	// menu bar
	private JMenuBar menuBar;
	// file menu bar item
	private JMenuItem newItem, openItem, saveItem, saveAsItem, exitItem;
	// edit menu bar item
	private JMenuItem nameItem;
	// help menu bar item
	private JMenuItem helpItem;
	// about menu bar item
	private JMenuItem authorItem;
	private JMenuItem versionItem;

	// file bar attributes
	final JFileChooser fc = new JFileChooser();
	File file = null;
	private TravelAgentFileHandler fileHandler;

	// main menu button
	private JButton homeMenuBtn, flMenuBtn,
			mvMenuBtn, passMenuBtn, bookItiMenuBtn;

	// main menu layout
	private JPanel menuCards, homeMenu, flightMenu,
			movieMenu, passMenu, bookItiMenu;
	private CardLayout menuCardsLayout;

	// main menu last condition
	private FlightMenuPanel flightMenuCond;
	private MovieMenuPanel movieMenuCond;
	private PassengerMenuPanel passMenuCond;
	private BookItineraryMenuPanel bookItiMenuCond;
	
	// ICON PACK
	final Icon icon1 = new ImageIcon(this.getClass().getResource("/resource/home.png"));
	final Icon icon2 = new ImageIcon(this.getClass().getResource("/resource/flight.png"));
	final Icon icon3 = new ImageIcon(this.getClass().getResource("/resource/movie.png"));
	final Icon icon4 = new ImageIcon(this.getClass().getResource("/resource/passenger.png"));
	final Icon icon5 = new ImageIcon(this.getClass().getResource("/resource/bookItinerary.png"));

	// FONT PACK
	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);
	final Font CALIBRI18 = new Font("Calibri", Font.PLAIN, 18);

	public TravelGUI(TravelAgent travelAgent)
	{
		// initialize TravelAgent object
		tva = travelAgent;

		// START DIALOG
		TravelGUIStartDialog programStart;
		programStart= new TravelGUIStartDialog(this);
		programStart.setLocationRelativeTo(this);
		// display TravelGUI start dialog
		programStart.setVisible(true);

		if (programStart.getTravelAgent() != null) {
			// set TravelAgent to the loaded TravelAgent data from the
			// start dialog
			tva = programStart.getTravelAgent();
			// reset class auto-generated id
			tva.reset();
			// re-sort every arraylist to default sorting method
			tva.sortFlightByNo();
			tva.sortPassengerBookRef();
			tva.reSortFlightMovie();
		} 

		// MENU BAR
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Menu bar File
		JMenu fileBar = new JMenu("File");
		fileBar.setFont(CALIBRI14);
		menuBar.add(fileBar);

		// File bar item
		newItem = new JMenuItem("New");
		newItem.setFont(CALIBRI14);
		openItem = new JMenuItem("Open");
		openItem.setFont(CALIBRI14);
		saveItem = new JMenuItem("Save");
		saveItem.setFont(CALIBRI14);
		saveAsItem = new JMenuItem("Save As...");
		saveAsItem.setFont(CALIBRI14);
		exitItem = new JMenuItem("Exit");
		exitItem.setFont(CALIBRI14);

		fileBar.add(newItem);
		fileBar.add(openItem);
		fileBar.addSeparator();
		fileBar.add(saveItem);
		fileBar.add(saveAsItem);
		fileBar.addSeparator();
		fileBar.add(exitItem);

		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		saveAsItem.addActionListener(this);
		exitItem.addActionListener(this);

		// Menu bar Edit
		JMenu editBar = new JMenu("Edit");
		editBar.setFont(CALIBRI14);
		menuBar.add(editBar);

		// Edit bar item
		nameItem = new JMenuItem("Travel Agent Name");
		nameItem.setFont(CALIBRI14);
		editBar.add(nameItem);

		nameItem.addActionListener(this);

		// Menu bar Help
		JMenu helpBar = new JMenu("Help");
		helpBar.setFont(CALIBRI14);
		menuBar.add(helpBar);

		// Help bar item
		helpItem = new JMenuItem("Help Me");
		helpItem.setFont(CALIBRI14);
		helpBar.add(helpItem);

		helpItem.addActionListener(this);

		// Menu bar About
		JMenu aboutBar = new JMenu("About");
		aboutBar.setFont(CALIBRI14);
		menuBar.add(aboutBar);

		// About bar item
		authorItem = new JMenuItem("Author");
		authorItem.setFont(CALIBRI14);
		versionItem = new JMenuItem("Version");
		versionItem.setFont(CALIBRI14);

		// About bar sub-menu Theme
		JMenu themeMenu = new JMenu("Theme");
		themeMenu.setFont(CALIBRI14);

		// Theme sub-menu item
		JCheckBoxMenuItem industrialThm;
		industrialThm= new JCheckBoxMenuItem("Industrial");
		industrialThm.setSelected(true);
		industrialThm.setFont(CALIBRI14);
		themeMenu.add(industrialThm);

		aboutBar.add(themeMenu);
		aboutBar.addSeparator();
		aboutBar.add(authorItem);
		aboutBar.add(versionItem);

		authorItem.addActionListener(this);
		versionItem.addActionListener(this);

		// MAIN MENU BUTTON
		JPanel mainMenuButton = new JPanel(new GridLayout(0,1));
		homeMenuBtn = new JButton("    Home    ",icon1);
		homeMenuBtn.setFont(CALIBRI18);
		homeMenuBtn.setBackground(new JButton().getBackground());
		flMenuBtn = new JButton("    Flight    ",icon2);
		flMenuBtn.setFont(CALIBRI18);
		flMenuBtn.setBackground(Color.WHITE);
		mvMenuBtn = new JButton("  In-Flight Movie  ",icon3);
		mvMenuBtn.setFont(CALIBRI18);
		mvMenuBtn.setBackground(Color.WHITE);
		passMenuBtn = new JButton("   Passenger   ",icon4);
		passMenuBtn.setFont(CALIBRI18);
		passMenuBtn.setBackground(Color.WHITE);
		bookItiMenuBtn = new JButton(" Book & Itinerary ",icon5);
		bookItiMenuBtn.setFont(CALIBRI18);
		bookItiMenuBtn.setBackground(Color.WHITE);
		mainMenuButton.add(homeMenuBtn);
		mainMenuButton.add(flMenuBtn);
		mainMenuButton.add(mvMenuBtn);
		mainMenuButton.add(passMenuBtn);
		mainMenuButton.add(bookItiMenuBtn);

		homeMenuBtn.addActionListener(this);
		flMenuBtn.addActionListener(this);
		mvMenuBtn.addActionListener(this);
		passMenuBtn.addActionListener(this);
		bookItiMenuBtn.addActionListener(this);

		// MAIN MENU PANEL
		homeMenu = new HomeMenuPanel(tva);
		flightMenu = new FlightMenuPanel(tva,this,'F');
		movieMenu = new MovieMenuPanel(tva,this,null,'T');
		passMenu = new PassengerMenuPanel(tva,this,'B');
		bookItiMenu = new BookItineraryMenuPanel(tva, this, null);

		// record main menu last condition
		flightMenuCond = new FlightMenuPanel(tva,this,'F');
		movieMenuCond = new MovieMenuPanel(tva,this,null,'T');
		passMenuCond = new PassengerMenuPanel(tva,this,'B');
		bookItiMenuCond = new BookItineraryMenuPanel(tva,this,null);
		
		// MAIN MENU LAYOUT
		menuCards = new JPanel(new CardLayout());
		menuCards.add(homeMenu, "Menu Home");
		menuCards.add(flightMenu, "Menu Flight");
		menuCards.add(movieMenu, "Menu In-Flight Movie");
		menuCards.add(passMenu, "Menu Passenger");
		menuCards.add(bookItiMenu, "Menu Book & Itinerary");

		// MAIN FRAME LAYOUT
		setTitle("HELP TRAVEL AGENCY PROGRAM");
		setLayout(new BorderLayout());
		getContentPane().add(mainMenuButton, BorderLayout.WEST);
		getContentPane().add(menuCards, BorderLayout.CENTER);
		setSize(1200,600);
		setLocation(50,50);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
        {
        	// add window listener to warn user to save the data before
			// closing the program
			@Override
            public void windowClosing(WindowEvent we)
            {
                int close = JOptionPane.showConfirmDialog(null,
						"Unsaved data will be lost. " +
								"Do you want to exit program?",
						"Program Close",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
                if (close == JOptionPane.YES_OPTION){
                	dispose();
                }
            }
        });

	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{	
		// MENU BAR LISTENER

		// File Bar Listener
		try {
			// initialize TravelAgentFileHandler to handle
			// saving/loading to file
			fileHandler = new TravelAgentFileHandler(this);

			// open a file to load data
			if (ae.getSource() == openItem) {
				// option to save data before opening a file
				int n = JOptionPane.showConfirmDialog(this,
		              "Would you like to save " +
							  "your changes first?",
		              "Open File", JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.YES_OPTION) // want to save
				{
					int nSave = JOptionPane.showConfirmDialog(this,
		              "Save as a file name?",
		              "Saving File", JOptionPane.YES_NO_OPTION);

					if (nSave == JOptionPane.NO_OPTION) { // auto save
						tvaName = tva.getName();
						fileHandler.saveToFile(tvaName,tva);
						JOptionPane.showMessageDialog(this, 
							"Saved as "+tvaName+".TravelGUI.txt "
									+"in program directory folder!");
					
					} else { // save as
						fileHandler.showSaveFileChooser(tva);
						if (fileHandler.getFile() != null) {
							JOptionPane.showMessageDialog(this, 
							"File successfully saved!");
						}
					}
					
				} else { // open file
					int fcVal = fc.showOpenDialog(this);
					if (fcVal == JFileChooser.APPROVE_OPTION) {
						file = fc.getSelectedFile();
						tva = fileHandler.loadFromFile(file);
						tva.reset();
						System.out.println("--INFO: File loaded from "+
								file.getPath());
						JOptionPane.showMessageDialog(this, 
							"File successfully loaded");
						JOptionPane.showMessageDialog(this, 
							"Good to see you again!");
						refreshHomeMenu();
					}
				}
			}

			// auto save data to a file
			else if (ae.getSource() == saveItem) {
				tvaName = tva.getName();
				fileHandler.saveToFile(tvaName,tva);
				JOptionPane.showMessageDialog(this, 
					"Saved as "+tvaName+".TravelGUI.txt " +
							"in program directory folder!");
			}

			// save as data with a specified file name
			else if (ae.getSource() == saveAsItem) {
				fileHandler.showSaveFileChooser(tva);
				if (fileHandler.getFile() != null) {
					JOptionPane.showMessageDialog(this, 
					"File successfully saved!");
				}
			}

		} catch (IOException ioe) {
			System.out.println("--ERROR: File Handling TravelGUI");

		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, 
				"Can't load file!",
				"Unexpected Error", JOptionPane.ERROR_MESSAGE);
			System.out.println("--ERROR: Loading file TravelGUI");
		}

		// create new data
		if (ae.getSource() == newItem) {
			// warn user to save the current data
			// before creating a new data
			int newDt = JOptionPane.showConfirmDialog(this,
		              "Unsaved data will be lost. " +
							  "Are you sure you want to create new data?",
		              "Warning", JOptionPane.YES_NO_CANCEL_OPTION, 
		              JOptionPane.WARNING_MESSAGE);

			if (newDt == JOptionPane.YES_OPTION) {
				// prompt to input travel agent name
				String tvaName;
				try {
				 tvaName = JOptionPane.showInputDialog("Enter " +
						 "Travel Agent Name :");
				 	// validation
					 if (tvaName.length() == 0) {
						JOptionPane.showMessageDialog(this,
								"Empty Field!",
							"Input Name Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// create new object with the entered name
						tva = new TravelAgent(tvaName);
						 // reset class auto-generated id
						tva.reset();
						JOptionPane.showMessageDialog(this,
								"Hello there!");
						refreshHomeMenu();
					}

				} catch (NullPointerException e){
					JOptionPane.showMessageDialog(this,
							"Canceled!");
				}
			} else 
				JOptionPane.showMessageDialog(this,
						"Canceled!");
		}

		// exit program from exit item in file bar
		else if (ae.getSource() == exitItem) 
		{
			// warn user to save the data before closing the program
			int close = JOptionPane.showConfirmDialog(null,
						"Unsaved data will be lost. " +
								"Do you want to exit program?",
						"Program Close", 
						JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.WARNING_MESSAGE);
            if (close == JOptionPane.YES_OPTION){
            	dispose();
            }
		} 

		// edit current travel agent name
		else if (ae.getSource() == nameItem)
		{
			// prompt to input new travel agent name
			String name;
			try {
			 	name = JOptionPane.showInputDialog("Enter " +
						"New Travel Agent Name :");
			 	// validation
				 if (name.length() == 0) {
					JOptionPane.showMessageDialog(this,
							"Empty Field!",
						"Input Name Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
				 	// set travel agent name
					tva.setName(name);
					JOptionPane.showMessageDialog(this, 
						"Travel Agent " +
								"name successfully updated!");
					refreshHomeMenu();
				}

			} catch (NullPointerException e){
				JOptionPane.showMessageDialog(this,
						"Canceled!");
			}
		} 

		// Help Bar Listener
		else if (ae.getSource() == helpItem) 
		{
			JOptionPane.showMessageDialog(this, 
				"Please help yourself.... " +
						"You are a HELP student....");
		
		} 

		// Author Bar Listener
		else if (ae.getSource() == authorItem) 
		{
			JOptionPane.showMessageDialog(this, 
				"I Made Siva Aditya Surya\n"+
				"Student ID : E1800176");
		
		} 

		else if (ae.getSource() == versionItem) 
		{
			JOptionPane.showMessageDialog(this, 
				"@Version 2.0.6; @Since April 2020");
		}

		// MENU BUTTON LISTENER
		// if the menu button is clicked, the panel of the corresponding
		// menu will refresh (update its content) according to its
		// recorded last condition

		if (ae.getSource() == homeMenuBtn) {
			refreshHomeMenu();
		
		} else if (ae.getSource() == flMenuBtn) {
			refreshFlightMenu(flightMenuCond.getSort());
		
		} else if (ae.getSource() == mvMenuBtn) {
			refreshMovieMenu(movieMenuCond.getFlight(),
							movieMenuCond.getSort());
		
		} else if (ae.getSource() == passMenuBtn) {
			refreshPassMenu(passMenuCond.getSort());
		
		} else if (ae.getSource() == bookItiMenuBtn) {
			refreshBookItiMenu(bookItiMenuCond.getPassenger());
		}
	}

	/**A method to refresh Menu Home content*/
	public void refreshHomeMenu()
	{
		// update button background
		homeMenuBtn.setBackground(new JButton().getBackground());
		flMenuBtn.setBackground(Color.WHITE);
		mvMenuBtn.setBackground(Color.WHITE);
		passMenuBtn.setBackground(Color.WHITE);
		bookItiMenuBtn.setBackground(Color.WHITE);

		// update content and re-display panel
		homeMenu = new HomeMenuPanel(tva);
		menuCards.add(homeMenu, "Menu Home");
		menuCardsLayout = (CardLayout) menuCards.getLayout();
		menuCardsLayout.show(menuCards, "Menu Home");
	}

	/**
	 * A method to refresh Menu Flight content
	 * @param sort is to record how the table data is currently sorted
	 */
	public void refreshFlightMenu(char sort)
	{
		// update button background
		homeMenuBtn.setBackground(Color.WHITE);
		flMenuBtn.setBackground(new JButton().getBackground());
		mvMenuBtn.setBackground(Color.WHITE);
		passMenuBtn.setBackground(Color.WHITE);
		bookItiMenuBtn.setBackground(Color.WHITE);

		// record condition
		flightMenuCond.setSort(sort);
		// update content and re-display panel
		flightMenu = new FlightMenuPanel(tva,this,sort);
		menuCards.add(flightMenu, "Menu Flight");
		menuCardsLayout = (CardLayout) menuCards.getLayout();
		menuCardsLayout.show(menuCards, "Menu Flight");
	}

	/**
	 * A method to refresh Menu In-Flight Movie content
	 * @param flight is to record which flight is currently displayed in
	 *                  the menu
	 * @param sort is to record how the list data is currently sorted
	 */
	public void refreshMovieMenu(Flight flight, char sort)
	{
		// update button background
		homeMenuBtn.setBackground(Color.WHITE);
		flMenuBtn.setBackground(Color.WHITE);
		mvMenuBtn.setBackground(new JButton().getBackground());
		passMenuBtn.setBackground(Color.WHITE);
		bookItiMenuBtn.setBackground(Color.WHITE);

		// record condition
		movieMenuCond.setFlight(flight);
		movieMenuCond.setSort(sort);
		// update content and re-display panel
		movieMenu = new MovieMenuPanel(tva,this,flight,sort);
		menuCards.add(movieMenu, "Menu In-Flight Movie");
		menuCardsLayout = (CardLayout) menuCards.getLayout();
		menuCardsLayout.show(menuCards, "Menu In-Flight Movie");

		// display warning message when user is trying to access the
		// menu while there is still no flight added to collection yet
		if (tva.getNumOfFlight() == 0) {
			JOptionPane.showMessageDialog(this,
					"Flight is not added!",
					"Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * A method to refresh Menu Passenger content
	 * @param sort is to record how the table data is currently sorted
	 */
	public void refreshPassMenu(char sort)
	{
		// update button background
		homeMenuBtn.setBackground(Color.WHITE);
		flMenuBtn.setBackground(Color.WHITE);
		mvMenuBtn.setBackground(Color.WHITE);
		passMenuBtn.setBackground(new JButton().getBackground());
		bookItiMenuBtn.setBackground(Color.WHITE);

		// record condition
		passMenuCond.setSort(sort);
		// update content and re-display panel
		passMenu = new PassengerMenuPanel(tva,this,sort);
		menuCards.add(passMenu, "Menu Passenger");
		menuCardsLayout = (CardLayout) menuCards.getLayout();
		menuCardsLayout.show(menuCards, "Menu Passenger");
	}

	/**
	 *
	 * A method to refresh Menu Book & Itinerary content
	 * @param passenger is to record which passenger is currently
	 *                     displayed in the menu
	 */
	public void refreshBookItiMenu(Passenger passenger)
	{
		// update button background
		homeMenuBtn.setBackground(Color.WHITE);
		flMenuBtn.setBackground(Color.WHITE);
		mvMenuBtn.setBackground(Color.WHITE);
		passMenuBtn.setBackground(Color.WHITE);
		bookItiMenuBtn.setBackground(new JButton().getBackground());

		// record condition
		bookItiMenuCond.setPassenger(passenger);
		// update content and re-display panel
		bookItiMenu = new BookItineraryMenuPanel(tva,this,passenger);
		menuCards.add(bookItiMenu, "Menu Book & Itinerary");
		menuCardsLayout = (CardLayout) menuCards.getLayout();
		menuCardsLayout.show(menuCards, "Menu Book & Itinerary");

		// display warning message when user is trying to access the
		// menu while there is still no passenger added to collection yet
		if (tva.getNumOfPassenger() == 0) {
			JOptionPane.showMessageDialog(this,
					"Passenger is not added!",
					"Warning", JOptionPane.WARNING_MESSAGE);

		}

		// or if passenger exists in collection but no flight is added yet
		else if (tva.getNumOfFlight() == 0) {
			JOptionPane.showMessageDialog(this,
					"Flight is not added!",
					"Warning", JOptionPane.WARNING_MESSAGE);
			
		}
	}

	/**Main method*/
	public static void main(String[] args)
	{
		// initialize TravelAgent object
		TravelAgent tva = new TravelAgent();
		// display TravelGUI
		JFrame travelGui = new TravelGUI(tva);
		travelGui.setVisible(true);
	}
}
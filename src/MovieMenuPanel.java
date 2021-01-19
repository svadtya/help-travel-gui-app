/**
 * A class to create Menu In-Flight Movie panel
 * @author Siva Aditya (E1800176)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.format.*;

public class MovieMenuPanel extends JPanel implements ActionListener
{	
	// parent frame component
	private TravelAgent tva;
	private int flightNumOfMovies;
	private TravelGUI gui;
	private JFrame mainFrame;
	// last condition
	private Flight flight;
	private char sort;

	// panel component
	private JButton addMvBtn, editMvBtn, removeMvBtn, clearMvBtn, sortBtn;
	private JRadioButton sortTitle, sortLength;
	private JTextField numMovies;

	// flight
	private JLabel flightInd;
	private JTextField flightNo;
	private JButton okBtn, clearBtn;
	private JLabel errInd, errText;
	private JLabel flightTypeLbl, flightDateLbl, flightOriDestLbl;
	private JLabel flightTypeVal, flightDateVal, flightOriDestVal;

	// movie list component
	private JList movieList;
	private MovieListModel movieListMod;

	// FORMAT & FONT PACK
	final DateTimeFormatter DATEFORMAT;
	final Font CALIBRI12 = new Font("Calibri", Font.PLAIN, 12);
	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);
	final Font CALIBRI16 = new Font("Calibri", Font.PLAIN, 16);
	final Font CALIBRI18 = new Font("Calibri", Font.PLAIN, 18);
	final Font CALIBRI20 = new Font("Calibri", Font.PLAIN, 20);
	final Font CALIBRI22 = new Font("Calibri", Font.PLAIN, 22);

	public MovieMenuPanel(TravelAgent tva, TravelGUI gui,
						  Flight fl, char sort)
	{	
		// parent frame component
		this.tva = tva;
		this.gui = gui;
		mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.sort = sort;
		DATEFORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// check whether flight still exist in collection
		// if not, flight will be set to null
		if (fl != null) 
			flight = tva.findFlight(fl.getFlightNo());
			
		// TITLE
		JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel menuTitle = new JLabel("In-Flight Movie");
		menuTitle.setFont(CALIBRI22);
		title.add(menuTitle);

		// NORTH PANEL
		JPanel northPanel = new JPanel(new GridLayout(2,0));
		northPanel.add(title);
		northPanel.add(new Label(""));

		// LEFT PANEL
		JPanel leftPanel = new JPanel(new BorderLayout());

		// INPUT FLIGHT SECTION
		JPanel leftTopPanel = new JPanel(new FlowLayout());

		// input text section
		JPanel inputTextPn;
		inputTextPn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel inputTextLbl = new JLabel("Flight No");
		inputTextLbl.setFont(CALIBRI18);
		inputTextPn.add(inputTextLbl);

		// input field section
		JPanel inputFieldPn;
		inputFieldPn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		flightNo = new JTextField(10);
		flightNo.setFont(CALIBRI18);
		flightNo.setHorizontalAlignment(JTextField.CENTER);
		inputFieldPn.add(flightNo);

		// error input text section
		JPanel errInputTextPn;
		errInputTextPn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		errInd = new JLabel(""); errInd.setFont(CALIBRI18); 
		errInd.setForeground(Color.RED);
		errInputTextPn.add(errInd);

		// error input field section
		JPanel errInputFieldPn;
		errInputFieldPn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		errText = new JLabel(""); errText.setFont(CALIBRI18); 
		errText.setForeground(Color.RED);
		errInputFieldPn.add(errText);

		// input button section
		JPanel inputButtonPn;
		inputButtonPn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		okBtn = new JButton("OK"); 
		okBtn.setFont(CALIBRI18); 
		okBtn.setBackground(Color.WHITE);
		clearBtn = new JButton("Clear"); 
		clearBtn.setFont(CALIBRI18);
		clearBtn.setBackground(Color.WHITE);
		inputButtonPn.add(okBtn);
		inputButtonPn.add(clearBtn);
		okBtn.addActionListener(this);
		clearBtn.addActionListener(this);

		// left panel of the input flight section
		JPanel leftTopLeftPanel;
		leftTopLeftPanel = new JPanel(new GridLayout(3,0,0,18));
		leftTopLeftPanel.add(inputTextPn);
		leftTopLeftPanel.add(errInputTextPn);
		leftTopLeftPanel.add(new Label(""));

		// right panel of the input flight section
		JPanel leftTopRightPanel;
		leftTopRightPanel = new JPanel(new GridLayout(3,0,0,4));
		leftTopRightPanel.add(inputFieldPn);
		leftTopRightPanel.add(errInputFieldPn);
		leftTopRightPanel.add(inputButtonPn);

		// left top panel
		leftTopPanel.add(leftTopLeftPanel);
		leftTopPanel.add(leftTopRightPanel);

		// flight chosen indicator panel
		JPanel flightIndPanel = new JPanel(new BorderLayout());
		flightInd = new JLabel("");
		flightInd.setFont(CALIBRI18);
		flightInd.setForeground(Color.RED);
		JPanel flightIndPn;
		flightIndPn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		flightIndPn.add(flightInd);
		flightIndPanel.add(flightIndPn, "North");
		flightIndPanel.add(new JSeparator(
				JSeparator.HORIZONTAL), "Center");
		
		// left top true panel
		JPanel leftTopTruePanel = new JPanel(new BorderLayout());
		leftTopTruePanel.add(leftTopPanel, "Center");
		leftTopTruePanel.add(flightIndPanel, "South");

		// FLIGHT DETAILS SECTION
		JPanel leftBottomPanel = new JPanel(new FlowLayout());

		// detail label section
		// flight type label
		JPanel flightTypePn;
		flightTypePn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flightTypeLbl = new JLabel("");
		flightTypeLbl.setFont(CALIBRI16);
		flightTypePn.add(flightTypeLbl);

		// flight date label
		JPanel flightDatePn;
		flightDatePn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flightDateLbl = new JLabel("");
		flightDateLbl.setFont(CALIBRI16);
		flightDatePn.add(flightDateLbl);

		// flight ori dest label
		JPanel flightOriDestPn;
		flightOriDestPn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flightOriDestLbl = new JLabel("");
		flightOriDestLbl.setFont(CALIBRI16);
		flightOriDestPn.add(flightOriDestLbl);

		// detail value section
		// flight type value
		JPanel flightTypeValPn;
		flightTypeValPn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flightTypeVal = new JLabel("");
		flightTypeVal.setFont(CALIBRI16);
		flightTypeValPn.add(flightTypeVal);

		// flight date value
		JPanel flightDateValPn;
		flightDateValPn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flightDateVal = new JLabel("");
		flightDateVal.setFont(CALIBRI16);
		flightDateValPn.add(flightDateVal);

		// flight ori dest value
		JPanel flightOriDestValPn;
		flightOriDestValPn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flightOriDestVal = new JLabel("");
		flightOriDestVal.setFont(CALIBRI16);
		flightOriDestValPn.add(flightOriDestVal);

		// left panel of the flight details section
		JPanel leftBottomLeftPanel = new JPanel(new GridLayout(3,0));
		leftBottomLeftPanel.add(flightTypePn);
		leftBottomLeftPanel.add(flightDatePn);
		leftBottomLeftPanel.add(flightOriDestPn);

		// right panel of the flight details section
		JPanel leftBottomRightPanel = new JPanel(new GridLayout(3,0));
		leftBottomRightPanel.add(flightTypeValPn);
		leftBottomRightPanel.add(flightDateValPn);
		leftBottomRightPanel.add(flightOriDestValPn);

		leftBottomPanel.add(leftBottomLeftPanel);
		leftBottomPanel.add(leftBottomRightPanel);

		// left panel
		leftPanel.add(new Label(""), "North");
		leftPanel.add(leftTopTruePanel, "Center");
		leftPanel.add(leftBottomPanel, "South");

		// MOVIE SECTION
		JPanel rightPanel = new JPanel(new BorderLayout());

		// SORT SECTION
		ButtonGroup sortBtnGroup = new ButtonGroup();
		// set sort according to panel last recorded sort value
		switch (sort)
		{
			case 'T':
				if (flight != null)
					flight.sortMovieByTitle();
				sortTitle = new JRadioButton("Title", true);
				sortLength = new JRadioButton("Length", false);
				break;
			case 'L':
				if (flight != null)
					flight.sortMovieByLength();
				sortTitle = new JRadioButton("Title", false);
				sortLength = new JRadioButton("Length", true);
				break;

			default: break;
		}
		
		sortTitle.setFont(CALIBRI18);
		sortLength.setFont(CALIBRI18);
		sortBtnGroup.add(sortTitle);
		sortBtnGroup.add(sortLength);

		JPanel sortSect = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sortBtn = new JButton("Sort");
		sortBtn.setFont(CALIBRI18);
		sortBtn.setBackground(Color.WHITE);
		sortSect.add(sortTitle);
		sortSect.add(sortLength);
		sortSect.add(sortBtn);
		sortBtn.addActionListener(this);
		sortTitle.addActionListener(this);
		sortLength.addActionListener(this);

		// MOVIE LIST SECTION
		JScrollPane moviePane;
		if (flight != null) {
			// setting up movie list
			movieListMod = new MovieListModel(flight);
		
		} else {
			movieListMod = new MovieListModel();
		}

		movieList = new JList(movieListMod);
		movieList.setFont(CALIBRI18);
		movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		moviePane = new JScrollPane(movieList);

		// NUMBER OF MOVIES SECTION
		JPanel numMoviesSect;
		numMoviesSect = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel numMoviesLbl = new JLabel("Movie(s) Available");
		numMoviesLbl.setFont(CALIBRI18);
		numMovies = new JTextField("-",4);
		numMovies.setFont(CALIBRI18);
		numMovies.setHorizontalAlignment(JTextField.CENTER); 
		numMovies.setEditable(false);
		numMoviesSect.add(numMoviesLbl);
		numMoviesSect.add(numMovies);

		rightPanel.add(sortSect, BorderLayout.NORTH);
		rightPanel.add(new Label(""), "West");
		rightPanel.add(moviePane, BorderLayout.CENTER);
		rightPanel.add(new Label(""), "East");
		rightPanel.add(numMoviesSect, BorderLayout.SOUTH);

		// BUTTON SECTION
		JPanel buttonPanel;
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,15,0));
		addMvBtn = new JButton("Add New Movie to Flight");
		addMvBtn.setFont(CALIBRI18);
		addMvBtn.setBackground(Color.WHITE);
		editMvBtn = new JButton("Edit Movie Details");
		editMvBtn.setFont(CALIBRI18);
		editMvBtn.setBackground(Color.WHITE);
		removeMvBtn = new JButton("Remove Movie from Flight");
		removeMvBtn.setFont(CALIBRI18);
		removeMvBtn.setBackground(Color.WHITE);
		clearMvBtn = new JButton("Clear Flight Movie List");
		clearMvBtn.setFont(CALIBRI18);
		clearMvBtn.setBackground(Color.WHITE);
		addMvBtn.addActionListener(this);
		editMvBtn.addActionListener(this);
		removeMvBtn.addActionListener(this);
		clearMvBtn.addActionListener(this);
		buttonPanel.add(addMvBtn);
		buttonPanel.add(editMvBtn);
		buttonPanel.add(removeMvBtn);
		buttonPanel.add(clearMvBtn);

		// MAIN PANEL
		JPanel mainPanel = new JPanel(new GridLayout());
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);

		// SOUTH PANEL 
		JPanel southPanel = new JPanel(new GridLayout(3,1));
		southPanel.add(new Label(""));
		southPanel.add(buttonPanel);
		southPanel.add(new Label(""));

		// setting up data
		if (flight != null){
			flightNo.setText(String.valueOf(flight.getFlightNo()));
			String type = (flight instanceof BusinessFlight)
					? "Business" : "Economy";
			flightTypeLbl.setText("Type");
			flightTypeVal.setText(": "+type);
			flightDateLbl.setText("Flight Date");
			flightDateVal.setText(": "+
					flight.getDate().format(DATEFORMAT));
			String oriDest = flight.getOrigin()+
					"->"+flight.getDestination();
			flightOriDestLbl.setText("Origin->Destination");
			flightOriDestVal.setText(": "+oriDest);
			numMovies.setText(String.valueOf(flight.getNumMovies()));
		} else {
			flightInd.setText("Please select a flight!");
		}

		// MENU PANEL LAYOUT
		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		add(new Label(""), BorderLayout.WEST);
		add(mainPanel, BorderLayout.CENTER);
		add(new Label(""), BorderLayout.EAST);
		add(southPanel, BorderLayout.SOUTH);
		setSize(new Dimension(800,550));
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Sort title
		if (ae.getSource() == sortBtn && sortTitle.isSelected()) {
			// refresh panel
			gui.refreshMovieMenu(flight,'T');
		
		}
		// Sort length
		else if (ae.getSource() == sortBtn && sortLength.isSelected()) {
			// refresh panel
			gui.refreshMovieMenu(flight,'L');
		}

		// Select a flight
		if (ae.getSource() == okBtn) {
			String flightNoVal = flightNo.getText().trim();

			// Flight No validation
			boolean validFlNoVal = true;
			try {
				int flNo = Integer.parseInt(flightNoVal);
			} catch (NumberFormatException e) {
				errText.setText("ERROR: Invalid Flight No!");
				validFlNoVal = false;
			}

			if (validFlNoVal) {
				errInd.setText("");
				errText.setText("");

				Flight f = tva.findFlight(Integer.parseInt(flightNoVal));
				if (f != null){
					JOptionPane.showMessageDialog(this, 
						"Flight ["+f.getFlightNo()+"] selected!");
					gui.refreshMovieMenu(f,sort);
				} else {
					JOptionPane.showMessageDialog(this,
							"Flight not found!",
					"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		// Clear flight selection
		else if (ae.getSource() == clearBtn) {
			// refresh panel
			gui.refreshMovieMenu(flight,sort);
		} 

		// Add new movie to flight
		else if (ae.getSource() == addMvBtn) {
			if (flight == null) {
				JOptionPane.showMessageDialog(this,
						"Please enter valid flight no first!",
					"Error", JOptionPane.ERROR_MESSAGE);
			} 

			else if (flight instanceof BusinessFlight)
				addMovieBsFlight();
			else 
				addMovieEcoFlight();
			
		}

		// Edit movie details
		else if (ae.getSource() == editMvBtn) {
			// action is allowed
			if (isValidToEditMovieList('E'))  {
				Movie selectedMv = (Movie) movieList.getSelectedValue();
				// display movie form dialog
				MovieFormDialog editDialog;
				editDialog = new MovieFormDialog(mainFrame);
				// set selected movie to dialog
				editDialog.setMovie(selectedMv);
				// fill the dialog's textfield with the movie's details
				editDialog.movieTitle.setText(selectedMv.getTitle());
				editDialog.movieLength.setText(
						String.valueOf(selectedMv.getLength()));
				editDialog.setLocationRelativeTo(this);
				editDialog.setVisible(true);
				// refresh panel
				gui.refreshMovieMenu(flight,sort);		
			}
		}

		// Remove movie from flight
		else if (ae.getSource() == removeMvBtn) {
			// action is allowed
			if (isValidToEditMovieList('R')) {
				Movie selectedMv = (Movie) movieList.getSelectedValue();
				int remove = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to delete "
								+selectedMv.getTitle()+"?",
						"Confirm Delete", JOptionPane.YES_NO_OPTION);
				if (remove == JOptionPane.YES_OPTION) {
					movieListMod.removeElement(selectedMv);
					// refresh panel
					gui.refreshMovieMenu(flight,sort);
				}
			}
		}

		// Clear flight movie list
		else if (ae.getSource() == clearMvBtn) {
			// action is allowed
			if (isValidToEditMovieList('C')) {
				int remove = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to clear Flight <"
								+flight.getFlightNo()+"> movie list?",
						"Confirm Delete",
						JOptionPane.YES_NO_OPTION);
				if (remove == JOptionPane.YES_OPTION) {
					movieListMod.clear();
					// refresh panel
					gui.refreshMovieMenu(flight,sort);
				}
			}
		}
	}

	/**A method to add Movie(s) to Business Flight*/
	public void addMovieBsFlight() {
		// adding movie loop since business flight can have
		// multiple movies
		boolean stop = false;
		do {
			MovieFormDialog inpMvDialog = new MovieFormDialog(mainFrame);
			inpMvDialog.setLocationRelativeTo(this);
			inpMvDialog.setVisible(true);

			Movie newMv = inpMvDialog.getMovie();

			if (newMv != null) {
				int again;
				// movie title validation
				if (movieListMod.contains(newMv)) {
					again = JOptionPane.showConfirmDialog(this,
					"Sorry, movie with the same " +
							"title has been added to your movie list. "+
					"Do you want to add another movie?",
					"Problem adding movie", JOptionPane.YES_NO_OPTION);
				} else {
					// choice to loop
					movieListMod.addElement(newMv);
					again = JOptionPane.showConfirmDialog(this,
					newMv.getTitle()+
							" added! Do you want to add another?",
					"Add Movie", JOptionPane.YES_NO_OPTION);
				}
				
				stop = !(again == JOptionPane.YES_OPTION);

			} else {
				JOptionPane.showMessageDialog(this,
						"Canceled!");
				stop = true;
			}

			// if loop stop,
			// show how many movie has been added to flight
			if (stop && flight.getNumMovies() > 0) {
				JOptionPane.showMessageDialog(this, 
				"This flight now has "+flight.getNumMovies()+" "+
				"available movie(s)!");
			}

		} while (!stop);
		// refresh panel
		gui.refreshMovieMenu(flight,sort);
	}

	/**A method to add Movie(s) to Economy Flight*/
	public void addMovieEcoFlight() {
		// validate that economy flight can only have 1 movie
		if (flight.getNumMovies() > 0) {
			JOptionPane.showMessageDialog(this,
					"Sorry, Economy Flight can only have 1 movie",
				"Error", JOptionPane.ERROR_MESSAGE);
		} else {
			MovieFormDialog inpMvDialog = new MovieFormDialog(mainFrame);
			inpMvDialog.setLocationRelativeTo(this);
			inpMvDialog.setVisible(true);

			Movie newMv = inpMvDialog.getMovie();
			if (newMv != null) {
				movieListMod.addElement(newMv);
				JOptionPane.showMessageDialog(this, 
				newMv.getTitle()+
						" is now available in this flight!");
			} else {
				JOptionPane.showMessageDialog(this,
						"Canceled!");
			}
		}
		// refresh panel
		gui.refreshMovieMenu(flight,sort);
	}

	/**
	 * A method to validate whether user action directed to modify
	 * the content of movie list is allowed
	 * @param act indicates the user action to the list
	 * @return boolean indicating if it is allowed or not
	 */
	public boolean isValidToEditMovieList(char act) {
		// flight is not selected
		if (flight == null) {
			JOptionPane.showMessageDialog(this,
					"Please enter a valid flight no first!",
				"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// movie list is empty
		else if (flight.getNumMovies() == 0) {
			JOptionPane.showMessageDialog(this,
					"This flight has no movie added!",
			"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// row in movie list is not selected
		// (validates for every action except clearing the movie list)
		else if (movieList.isSelectionEmpty() && act != 'C') {
			JOptionPane.showMessageDialog(this,
					"Please select a movie!",
			"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} 

		else 
			return true;	
	}

	// getter setter
	public Flight getFlight() {return flight;}
	public char getSort() {return sort;}
	public void setFlight(Flight f) {flight = f;}
	public void setSort(char sort) {this.sort = sort;}
}
/**
 * A class to create a dialog that is to be used
 * as a form to display Flight's details
 * @author Siva Aditya (E1800176)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.format.*;

public class FlightDetailsDialog extends JDialog 
{	
	// attributes
	private Flight flight;
	private Passenger passenger;

	// movie list component
	private JList movieList;
	private MovieListModel movieListMod;

	// frame component
	private JTextField flightNo, flightDate, type, ori, dest,
			dep, arr, adlPrc, chlPrc, numMv, bookPrc;
	private JButton closeBtn;

	// FORMAT & FONT PACK
	final DateTimeFormatter DATEFORMAT;
	final Font CALIBRI12 = new Font("Calibri", Font.PLAIN, 12);
	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);
	final Font CALIBRI16 = new Font("Calibri", Font.PLAIN, 16);
	final Font CALIBRI20 = new Font("Calibri", Font.PLAIN, 20);
	final Font CALIBRI22 = new Font("Calibri", Font.PLAIN, 22);

	public FlightDetailsDialog(JFrame parentMenu,
							   Flight flight, Passenger passenger)
	{
		// attributes
		super(parentMenu, true);
		this.flight = flight;
		this.passenger = passenger;
		DATEFORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// HEADER
		JPanel title = new JPanel(new FlowLayout());
		JLabel titleLbl = new JLabel("Flight Details");
		titleLbl.setFont(CALIBRI20);
		title.add(titleLbl);

		// SUBHEADER
		JPanel subHeader = new JPanel(new GridLayout(0,2));

		// Flight No Details
		JLabel flNoLbl = new JLabel("Flight No");
		flNoLbl.setFont(CALIBRI14);
		flightNo = new JTextField(4); 
		flightNo.setFont(CALIBRI14);
		flightNo.setHorizontalAlignment(JTextField.CENTER); 
		flightNo.setEditable(false);
		JPanel flightNoArea = new JPanel(new FlowLayout());
		flightNoArea.add(flNoLbl);
		flightNoArea.add(flightNo);

		// Flight Date Details
		JLabel dateLbl = new JLabel("Flight Date");
		dateLbl.setFont(CALIBRI14);
		flightDate = new JTextField(10); 
		flightDate.setFont(CALIBRI14);
		flightDate.setHorizontalAlignment(JTextField.CENTER); 
		flightDate.setEditable(false);
		JPanel flightDateArea = new JPanel(new FlowLayout());
		flightDateArea.add(dateLbl);
		flightDateArea.add(flightDate);
		
		subHeader.add(flightNoArea);
		subHeader.add(flightDateArea);

		// TOP PANEL
		JPanel topPanel = new JPanel(new GridLayout(3,1));
		topPanel.add(title);
		topPanel.add(subHeader);
		topPanel.add(new Label(""));

		// DETAIL TEXT LABEL
		JPanel leftDetailText;
		leftDetailText = new JPanel(new GridLayout(0,1,0,15));
		JLabel typeLbl = new JLabel("Flight Type");
		typeLbl.setFont(CALIBRI12);
		JLabel oriLbl = new JLabel("Origin");
		oriLbl.setFont(CALIBRI12);
		JLabel destLbl = new JLabel("Destination");
		destLbl.setFont(CALIBRI12);
		JLabel depLbl = new JLabel("Departure Time");
		depLbl.setFont(CALIBRI12);
		JLabel arrLbl = new JLabel("Arrival Time");
		arrLbl.setFont(CALIBRI12);
		JLabel adlPrcLbl = new JLabel("Adult Price");
		adlPrcLbl.setFont(CALIBRI12);
		JLabel chlPrcLbl = new JLabel("Child Price");
		chlPrcLbl.setFont(CALIBRI12);
		leftDetailText.add(typeLbl);
		leftDetailText.add(oriLbl);
		leftDetailText.add(destLbl);
		leftDetailText.add(depLbl);
		leftDetailText.add(arrLbl);
		leftDetailText.add(adlPrcLbl);
		leftDetailText.add(chlPrcLbl);

		// DETAIL TEXT FIELD
		JPanel leftDetailField;
		leftDetailField = new JPanel(new GridLayout(0,1,0,11));
		type = new JTextField(15);
		type.setFont(CALIBRI12); type.setEditable(false);
		ori = new JTextField(15);
		ori.setFont(CALIBRI12); ori.setEditable(false);
		dest = new JTextField(15);
		dest.setFont(CALIBRI12); dest.setEditable(false);
		dep = new JTextField(15);
		dep.setFont(CALIBRI12); dep.setEditable(false);
		arr = new JTextField(15);
		arr.setFont(CALIBRI12); arr.setEditable(false);
		adlPrc = new JTextField(15);
		adlPrc.setFont(CALIBRI12); adlPrc.setEditable(false);
		chlPrc = new JTextField(15);
		chlPrc.setFont(CALIBRI12); chlPrc.setEditable(false);
		leftDetailField.add(type);
		leftDetailField.add(ori);
		leftDetailField.add(dest);
		leftDetailField.add(dep);
		leftDetailField.add(arr);
		leftDetailField.add(adlPrc);
		leftDetailField.add(chlPrc);

		// LEFT PANEL
		JPanel leftPanel;
		leftPanel= new JPanel(new FlowLayout(FlowLayout.LEFT,15,0));
		leftPanel.add(leftDetailText);
		leftPanel.add(leftDetailField);

		// MOVIE SECTION
		JPanel movieSect = new JPanel(new BorderLayout());

		// movie section heeader
		JPanel movieHeader = new JPanel(new FlowLayout());
		JLabel numMvLbl = new JLabel("Movie(s) Available");
		numMvLbl.setFont(CALIBRI12);
		numMv = new JTextField(4);
		numMv.setFont(CALIBRI12);
		numMv.setHorizontalAlignment(JTextField.CENTER); 
		numMv.setEditable(false);
		movieHeader.add(numMvLbl);
		movieHeader.add(numMv);

		// setting up movie list
		flight.sortMovieByTitle();
		movieListMod = new MovieListModel(flight);
		movieList = new JList(movieListMod);
		movieList.setFont(CALIBRI12);
		JScrollPane moviePane = new JScrollPane(movieList);

		movieSect.add(movieHeader, BorderLayout.NORTH);
		movieSect.add(moviePane, BorderLayout.CENTER);
		
		// BOOKING PRICE SECTION
		JPanel bookPrcSect = new JPanel(new FlowLayout());
		JLabel bookPrcLbl = new JLabel("Booking Price");
		bookPrcLbl.setFont(CALIBRI16);
		bookPrc = new JTextField(10);
		bookPrc.setFont(CALIBRI16); bookPrc.setEditable(false);
		bookPrcSect.add(bookPrcLbl);
		bookPrcSect.add(bookPrc);

		// RIGHT PANEL
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(movieSect, BorderLayout.NORTH);
		rightPanel.add(bookPrcSect, BorderLayout.SOUTH);

		// DETAIL PANEL
		JPanel detailPanel = new JPanel(new GridLayout(0,2));
		detailPanel.add(leftPanel);
		detailPanel.add(rightPanel);

		// BUTTON PANEL
		JPanel buttonPanel = new JPanel(new FlowLayout());
		closeBtn = new JButton("Close View");
		closeBtn.setFont(CALIBRI14);
		//closeBtn.setBackground(Color.WHITE);
		buttonPanel.add(closeBtn);
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		// BOTTOM PANEL
		JPanel bottomPanel;
		bottomPanel= new JPanel(new GridLayout(2,0,0,0));
		bottomPanel.add(new Label(""));
		bottomPanel.add(buttonPanel);

		// setting up data
		flightNo.setText(String.valueOf(flight.getFlightNo()));
		flightDate.setText(flight.getDate().format(DATEFORMAT));
		String flightType;
		flightType = (flight instanceof BusinessFlight)?"Business" : "Economy";
		type.setText(flightType);
		ori.setText(flight.getOrigin());
		dest.setText(flight.getDestination());
		dep.setText(flight.getDeparr().substring(0,5));
		arr.setText(flight.getDeparr().substring(8));
		adlPrc.setText(String.format("RM %.2f", flight.getAdultPrice()));
		chlPrc.setText(String.format("RM %.2f", flight.getChildPrice()));
		numMv.setText(String.valueOf(flight.getNumMovies()));
		if (passenger != null)
			bookPrc.setText(
					String.format("RM %.2f", flight.calcPrice(passenger)));
		else{
			bookPrc.setHorizontalAlignment(JTextField.CENTER);
			bookPrc.setText("-");
		}

		// DIALOG LAYOUT
		setTitle("Flight Details");
		setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(new Label(""), BorderLayout.WEST);
		getContentPane().add(detailPanel, BorderLayout.CENTER);
		getContentPane().add(new Label(""), BorderLayout.EAST);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		setSize(new Dimension(700,460));
		setLocation(350,130);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
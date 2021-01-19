/**
 * A class to create Menu Home panel
 * @author Siva Aditya (E1800176)
 */

import java.awt.*;
import javax.swing.*;

public class HomeMenuPanel extends JPanel
{
	// attribute
	private TravelAgent tva;

	// FONT PACK
	final Font CALIBRI18 = new Font("Calibri", Font.PLAIN, 18);
	final Font CALIBRI22 = new Font("Calibri", Font.PLAIN, 22);

	public HomeMenuPanel(TravelAgent travelAgent)
	{	
		tva = travelAgent;

		// TITLE
		JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel menuTitle = new JLabel("Welcome to " +
				"HELP Travel Agency Program, "+tva.getName()+"!");
		menuTitle.setFont(CALIBRI22);
		title.add(menuTitle);

		// INFO TEXT
		JPanel textPn = new JPanel(new GridLayout(0,1,0,8));
		JLabel numFlightLbl = new JLabel("Number of Flight");
		numFlightLbl.setFont(CALIBRI18);
		JLabel numPassLbl = new JLabel("Number of Passenger");
		numPassLbl.setFont(CALIBRI18);
		textPn.add(numFlightLbl);
		textPn.add(numPassLbl);

		// INFO FIELD
		JPanel numberPn = new JPanel(new GridLayout(0,1,0,8));
		// get number of flight in collection
		int tvaFlight = tva.getNumOfFlight();
		JTextField numFlight = new JTextField(
				String.format("%s",tvaFlight),5);
		numFlight.setHorizontalAlignment(JTextField.CENTER);
		numFlight.setEditable(false);
		numFlight.setFont(CALIBRI18);
		// get number of passenger in collection
		int tvaPass = tva.getNumOfPassenger();
		JTextField numPass = new JTextField(
				String.format("%s",tvaPass),5);
		numPass.setHorizontalAlignment(JTextField.CENTER);
		numPass.setEditable(false);
		numPass.setFont(CALIBRI18);
		numberPn.add(numFlight);
		numberPn.add(numPass);

		// INFO PANEL
		JPanel infoPn = new JPanel(new GridLayout(1,0));
		infoPn.add(textPn);
		infoPn.add(numberPn);

		// CENTER INFO PANEL
		JPanel centerPn = new JPanel(new GridLayout(0,1));
		centerPn.add(title);
		centerPn.add(infoPn);

		// MAIN PANEL
		JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER));
		main.add(centerPn);

		// MENU PANEL LAYOUT
		setLayout(new GridLayout(3,1));
		add(new Label(""));
		add(main);
		add(new Label(""));
		setSize(new Dimension(800,550));
	}
}
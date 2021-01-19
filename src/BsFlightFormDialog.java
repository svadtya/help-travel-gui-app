/**
 * A class to create a dialog that is to be used
 * as input form to input and edit Business Flight's details
 * @author Siva Aditya (E1800176)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;

public class BsFlightFormDialog extends JDialog
		implements ActionListener
{
	// attributes
	private BusinessFlight bsFlight;
	private boolean valid; // input validation

	// dialog attributes
	JTextField ori, dest, date, dep, arr, price, childPerc, rate;
	private JButton okBtn, resetBtn, cancelBtn;
	private JLabel errInd1, errInd2, errInd3,
			errInd4, errInd5, errInd6, errInd7, errInd8;
	private JLabel errText1, errText2, errText3, errText4,
			errText5, errText6, errText7, errText8;

	// FORMAT & FONT PACK
	final DateTimeFormatter DATEFORMAT;
	final DateTimeFormatter TIMEFORMAT;
	final Font CALIBRI12 = new Font("Calibri", Font.PLAIN, 12);
	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);

	public BsFlightFormDialog(JFrame parentMenu)
	{		
		// attributes 
		super(parentMenu, true);
		bsFlight = null;
		valid = true;
		DATEFORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		TIMEFORMAT = DateTimeFormatter.ofPattern("HH:mm");

		// HEADER
		JPanel title = new JPanel(new FlowLayout());
		JLabel titleLbl = new JLabel("Business Flight Details Form");
		titleLbl.setFont(CALIBRI14);
		title.add(titleLbl);

		// TOP PANEL
		JPanel top = new JPanel(new GridLayout(2,1));
		top.add(title);
		top.add(new Label(""));

		// INPUT TEXT
		JPanel inputText;
		inputText = new JPanel(new GridLayout(0, 1, 0, 15));
		JLabel oriLbl = new JLabel("Origin (IATA Code)");
		oriLbl.setFont(CALIBRI12);
		JLabel destLbl = new JLabel("Destination (IATA Code)");
		destLbl.setFont(CALIBRI12);
		JLabel dateLbl = new JLabel("Date (dd/mm/yyyy)");
		dateLbl.setFont(CALIBRI12);
		JLabel depLbl = new JLabel("Departure Time " +
				"(24-h format, hh:mm)");
		depLbl.setFont(CALIBRI12);
		JLabel arrLbl = new JLabel("Arrival Time " +
				"(24-h format, hh:mm)");
		arrLbl.setFont(CALIBRI12);
		JLabel priceLbl = new JLabel("Flight Price (RM)");
		priceLbl.setFont(CALIBRI12);
		JLabel chPercLbl = new JLabel("Child Price (%)");
		chPercLbl.setFont(CALIBRI12);
		JLabel rateLbl = new JLabel("Business Price Rate (>1)");
		rateLbl.setFont(CALIBRI12);
		errInd1 = new JLabel(""); 
		errInd1.setFont(CALIBRI12); errInd1.setForeground(Color.RED);
		errInd2 = new JLabel(""); 
		errInd2.setFont(CALIBRI12); errInd2.setForeground(Color.RED);
		errInd3 = new JLabel(""); 
		errInd3.setFont(CALIBRI12); errInd3.setForeground(Color.RED);
		errInd4 = new JLabel(""); 
		errInd4.setFont(CALIBRI12); errInd4.setForeground(Color.RED);
		errInd5 = new JLabel(""); 
		errInd5.setFont(CALIBRI12); errInd5.setForeground(Color.RED);
		errInd6 = new JLabel(""); 
		errInd6.setFont(CALIBRI12); errInd6.setForeground(Color.RED);
		errInd7 = new JLabel(""); 
		errInd7.setFont(CALIBRI12); errInd7.setForeground(Color.RED);
		errInd8 = new JLabel(""); 
		errInd8.setFont(CALIBRI12); errInd8.setForeground(Color.RED);
		inputText.add(oriLbl);
		inputText.add(errInd1);
		inputText.add(destLbl);
		inputText.add(errInd2);
		inputText.add(dateLbl);
		inputText.add(errInd3);
		inputText.add(depLbl);
		inputText.add(errInd4);
		inputText.add(arrLbl);
		inputText.add(errInd5);
		inputText.add(priceLbl);
		inputText.add(errInd6);
		inputText.add(chPercLbl);
		inputText.add(errInd7);
		inputText.add(rateLbl);
		inputText.add(errInd8);

		// INPUT FIELD
		JPanel inputField;
		inputField = new JPanel(new GridLayout(0, 1, 0, 11));
		ori = new JTextField(18);
		ori.setFont(CALIBRI12);
		dest = new JTextField(18);
		dest.setFont(CALIBRI12);
		date = new JTextField(18);
		date.setFont(CALIBRI12);
		dep = new JTextField(18);
		dep.setFont(CALIBRI12);
		arr = new JTextField(18);
		arr.setFont(CALIBRI12);
		price = new JTextField(18);
		price.setFont(CALIBRI12);
		childPerc = new JTextField(18);
		childPerc.setFont(CALIBRI12);
		rate = new JTextField(18);
		rate.setFont(CALIBRI12);
		errText1 = new JLabel(""); 
		errText1.setFont(CALIBRI12); errText1.setForeground(Color.RED);
		errText2 = new JLabel(""); 
		errText2.setFont(CALIBRI12); errText2.setForeground(Color.RED);
		errText3 = new JLabel(""); 
		errText3.setFont(CALIBRI12); errText3.setForeground(Color.RED);
		errText4 = new JLabel(""); 
		errText4.setFont(CALIBRI12); errText4.setForeground(Color.RED);
		errText5 = new JLabel(""); 
		errText5.setFont(CALIBRI12); errText5.setForeground(Color.RED);
		errText6 = new JLabel(""); 
		errText6.setFont(CALIBRI12); errText6.setForeground(Color.RED);
		errText7 = new JLabel(""); 
		errText7.setFont(CALIBRI12); errText7.setForeground(Color.RED);
		errText8 = new JLabel(""); 
		errText8.setFont(CALIBRI12); errText8.setForeground(Color.RED);
		inputField.add(ori);
		inputField.add(errText1);
		inputField.add(dest);
		inputField.add(errText2);
		inputField.add(date);
		inputField.add(errText3);
		inputField.add(dep);
		inputField.add(errText4);
		inputField.add(arr);
		inputField.add(errText5);
		inputField.add(price);
		inputField.add(errText6);
		inputField.add(childPerc);
		inputField.add(errText7);
		inputField.add(rate);
		inputField.add(errText8);

		// INPUT PANEL
		JPanel inputPanel;
		inputPanel= new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		inputPanel.add(inputText);
		inputPanel.add(inputField);

		// BUTTON PANEL
		JPanel buttonPanel;
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
		okBtn = new JButton("OK");
		okBtn.setFont(CALIBRI12);
		okBtn.setBackground(Color.WHITE);
		resetBtn = new JButton("Reset");
		resetBtn.setFont(CALIBRI12);
		resetBtn.setBackground(Color.WHITE);
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(CALIBRI12);
		cancelBtn.setBackground(Color.WHITE);
		buttonPanel.add(okBtn);
		buttonPanel.add(resetBtn);
		buttonPanel.add(cancelBtn);
		okBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

		// BOTTOM PANEL
		JPanel bottom = new JPanel(new GridLayout(2,1));
		bottom.add(buttonPanel);
		bottom.add(new Label(""));

		// DIALOG LAYOUT
		setTitle("Business Flight Form");
		setLayout(new BorderLayout());
		getContentPane().add(top, BorderLayout.NORTH);
		getContentPane().add(inputPanel, BorderLayout.CENTER);
		getContentPane().add(bottom, BorderLayout.SOUTH);
		setSize(new Dimension(500,650));
		setLocation(400,20);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == okBtn)
		{
			// get value from textfield
			String oriVal = ori.getText().trim();
			String destVal = dest.getText().trim();
			String dateVal = date.getText().trim();
			String depVal = dep.getText().trim();
			String arrVal = arr.getText().trim();
			String prcVal = price.getText().trim(); // double
			String chlPercVal = childPerc.getText().trim(); // int
			String rateVal = rate.getText().trim(); // double

			// some validation
			valid = true;

			// ORIGIN & DESTINATION VALIDATION
			boolean validOri = true;
			if (oriVal.length() == 3) {
				for (int i=0; i<3; i++) {
					if (!Character.isLetter(oriVal.charAt(i)))
						validOri = false;
				}

			} else {
				validOri = false;
			}

			if(!validOri) {
				errInd1.setText("ERROR");
				errText1.setText("Not a valid three-letter IATA code!");
				valid = false;
			} else {
				errInd1.setText("");
				errText1.setText("");
			}

			boolean validDest = true;
			if (destVal.length() == 3) {
				for (int i=0; i<3; i++) {
					if (!Character.isLetter(destVal.charAt(i)))
						validDest = false;
				}

			} else {
				validDest = false;
			}

			if(!validDest) {
				errInd2.setText("ERROR");
				errText2.setText("Not a valid three-letter IATA code!");
				valid = false;
			} else {
				errInd2.setText("");
				errText2.setText("");
			}

			if (validOri && validDest) {
				if (oriVal.equals(destVal)) {
					errInd2.setText("ERROR");
					errText2.setText("Must be different from origin!");
					valid = false;
				}
			}

			// DATE VALIDATION
			boolean validDate = true;
			LocalDate dateObj = null;
			try {
				dateObj = LocalDate.parse(dateVal, DATEFORMAT);
			} catch (DateTimeParseException e) {
				errInd3.setText("ERROR");
				errText3.setText("Invalid date format!");
				dateObj = null;
				validDate = false;
			}

			if (validDate) {
				errInd3.setText("");
				errText3.setText("");
			} else {
				valid = false;
			}

			// DEPARTURE & ARRIVAL VALIDATION
			boolean validDep, validArr;
			validDep = validArr = true;
			LocalTime depTime, arrTime;
			depTime = arrTime = null;

			try {
				depTime = LocalTime.parse(depVal, TIMEFORMAT);
			} catch (DateTimeParseException e) {
				errInd4.setText("ERROR");
				errText4.setText("Invalid time format!");
				validDep = false;
			}

			if (validDep) {
				errInd4.setText("");
				errText4.setText("");
			} else {
				valid = false;
			}

			try {
				arrTime = LocalTime.parse(arrVal, TIMEFORMAT);
			} catch (DateTimeParseException e) {
				errInd5.setText("ERROR");
				errText5.setText("Invalid time format!");
				validArr = false;
			}

			if (validArr) {
				errInd5.setText("");
				errText5.setText("");
			} else {
				valid = false;
			}

			if (validDep && validArr) {
				if (!arrTime.isAfter(depTime)) {
					errInd5.setText("ERROR");
					errText5.setText("Must be ahead of departure time!");
					valid = false;
				}
			}

			// PRICE VALIDATION
			boolean validPrc = true;
			try {
				double prc = Double.parseDouble(prcVal);
			} catch (NumberFormatException e) {
				errInd6.setText("ERROR");
				errText6.setText("Invalid number!");
				validPrc = false;
			}

			if (validPrc) {
				errInd6.setText("");
				errText6.setText("");

				if (Double.parseDouble(prcVal) <= 0) {
					errInd6.setText("ERROR");
					errText6.setText("Must be a positive number!");
				}
			} else {
				valid = false;
			}

			// CHILD PERC VALIDATION
			boolean validChl = true;
			try {
				int chl = Integer.parseInt(chlPercVal);
				if (chl < 0) {valid = false;}
			} catch (NumberFormatException e) {
				errInd7.setText("ERROR");
				errText7.setText("Invalid number!");
				validChl = false;
			}

			if (validChl) {
				errInd7.setText("");
				errText7.setText("");
			} else {
				valid = false;
			}

			// BUSINESS RATE VALIDATION
			boolean validRt = true;
			try {
				double rt = Double.parseDouble(rateVal);
			} catch (NumberFormatException e) {
				errInd8.setText("ERROR");
				errText8.setText("Invalid number!");
				validRt = false;
			}

			if (validRt) {
				errInd8.setText("");
				errText8.setText("");

				if (Double.parseDouble(rateVal) <= 1) {
					errInd8.setText("ERROR");
					errText8.setText("Must be greater than 1!");
				}
			} else {
				valid = false;
			}

			// pass validation & create new business flight
			if (valid && validDate && validPrc
					&& validChl && validRt && bsFlight == null) {
				bsFlight = new BusinessFlight(oriVal,
						destVal, dateObj, depVal+" - "+arrVal,
					Double.parseDouble(prcVal),
					Integer.parseInt(chlPercVal),
					Double.parseDouble(rateVal));
				setVisible(false);
			
			// pass validation & edit business flight
			} else if (valid && validDate && validPrc
					&& validChl && validRt && bsFlight != null) {
				double bsPrice = Double.parseDouble(prcVal);
				int bsChlPerc = Integer.parseInt(chlPercVal);
				double bsRate = Double.parseDouble(rateVal);
				bsFlight.setOrigin(oriVal);
				bsFlight.setDestination(destVal);
				bsFlight.setDate(dateObj);
				bsFlight.setDeparr(depVal+" - "+arrVal);
				bsFlight.setPrice(bsPrice);
				bsFlight.setChildPerc(bsChlPerc);
				bsFlight.setRate(bsRate);
				bsFlight.setAdultPrice(
						bsFlight.calcAdultPrice(bsPrice, bsRate));
				bsFlight.setChildPrice(
						bsFlight.calcChildPrice(bsPrice, bsChlPerc, bsRate));
				setVisible(false);
			
			// doesn't pass validation
			} else {
				JOptionPane.showMessageDialog(this,
						"Error encountered!",
					"Input Error", JOptionPane.ERROR_MESSAGE);
			}

		
		} 

		else if (ae.getSource() == resetBtn) 
		{
			// set every textfield and label to empty
			ori.setText("");
			dest.setText("");
			date.setText("");
			dep.setText("");
			arr.setText("");
			price.setText("");
			childPerc.setText("");
			rate.setText("");
			errInd1.setText("");
			errInd2.setText("");
			errInd3.setText("");
			errInd4.setText("");
			errInd5.setText("");
			errInd6.setText("");
			errInd7.setText("");
			errInd8.setText("");
			errText1.setText("");
			errText2.setText("");
			errText3.setText("");
			errText4.setText("");
			errText5.setText("");
			errText6.setText("");
			errText7.setText("");
			errText8.setText("");
		}

		else if (ae.getSource() == cancelBtn) 
		{
			// close dialog
			setVisible(false);
		}
	}

	// getter setter
	public BusinessFlight getFlight() {return bsFlight;}
	public void setFlight(BusinessFlight flight) {bsFlight = flight;}
}
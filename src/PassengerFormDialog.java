/**
 * A class to create a dialog that is to be used
 * as input form to input and edit Passenger's details
 * @author Siva Aditya (E1800176)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PassengerFormDialog extends JDialog implements ActionListener
{
	// attributes
	private Passenger passenger;
	private boolean valid;

	// dialog attributes
	JTextField passName,numAdults, numChildren;
	private JButton okBtn, resetBtn, cancelBtn;
	private JLabel errInd1, errInd2, errInd3;
	private JLabel errText1, errText2, errText3;

	// FONT PACK
	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);
	final Font CALIBRI18 = new Font("Calibri", Font.PLAIN, 18);

	public PassengerFormDialog(JFrame parentMenu)
	{	
		// attributes
		super(parentMenu, true);
		passenger = null;
		valid = true;

		// HEADER
		JPanel title = new JPanel(new FlowLayout());
		JLabel titleLbl = new JLabel("Passenger Details Form");
		titleLbl.setFont(CALIBRI18);
		title.add(titleLbl);

		// TOP PANEL
		JPanel top = new JPanel(new GridLayout(2,1));
		top.add(title);
		top.add(new Label(""));

		// INPUT TEXT
		JPanel inputText;
		inputText = new JPanel(new GridLayout(6,1,0,15));
		JLabel nameLbl = new JLabel("Passenger Name");
		nameLbl.setFont(CALIBRI14);
		JLabel adultLbl = new JLabel("Number of Adults");
		adultLbl.setFont(CALIBRI14);
		JLabel childLbl = new JLabel("Number of Children");
		childLbl.setFont(CALIBRI14);
		errInd1 = new JLabel(""); 
		errInd1.setFont(CALIBRI14); errInd1.setForeground(Color.RED);
		errInd2 = new JLabel(""); 
		errInd2.setFont(CALIBRI14); errInd2.setForeground(Color.RED);
		errInd3 = new JLabel(""); 
		errInd3.setFont(CALIBRI14); errInd3.setForeground(Color.RED);
		inputText.add(nameLbl);
		inputText.add(errInd1);
		inputText.add(adultLbl);
		inputText.add(errInd2);
		inputText.add(childLbl);
		inputText.add(errInd3);

		// INPUT FIELD
		JPanel inputField;
		inputField= new JPanel(new GridLayout(0,1,0,11));
		passName = new JTextField(20); 
		passName.setFont(CALIBRI14);
		numAdults = new JTextField(20); 
		numAdults.setFont(CALIBRI14);
		numChildren = new JTextField(20); 
		numChildren.setFont(CALIBRI14);
		errText1 = new JLabel(""); 
		errText1.setFont(CALIBRI14); errText1.setForeground(Color.RED);
		errText2 = new JLabel(""); 
		errText2.setFont(CALIBRI14); errText2.setForeground(Color.RED);
		errText3 = new JLabel(""); 
		errText3.setFont(CALIBRI14); errText3.setForeground(Color.RED);
		inputField.add(passName);
		inputField.add(errText1);
		inputField.add(numAdults);
		inputField.add(errText2);
		inputField.add(numChildren);
		inputField.add(errText3);

		// INPUT PANEL
		JPanel inputPanel;
		inputPanel= new JPanel(new FlowLayout(FlowLayout.CENTER, 30,0));
		inputPanel.add(inputText);
		inputPanel.add(inputField);

		// BUTTON PANEL
		JPanel buttonPanel;
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15,0));
		okBtn = new JButton("OK");
		okBtn.setFont(CALIBRI14);
		okBtn.setBackground(Color.WHITE);
		resetBtn = new JButton("Reset");
		resetBtn.setFont(CALIBRI14);
		resetBtn.setBackground(Color.WHITE);
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(CALIBRI14);
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
		setTitle("Passenger Form");
		setLayout(new BorderLayout());
		getContentPane().add(top, BorderLayout.NORTH);
		getContentPane().add(inputPanel, BorderLayout.CENTER);
		getContentPane().add(bottom, BorderLayout.SOUTH);
		setSize(new Dimension(500,400));
		setLocation(400,180);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == okBtn)
		{	
			// get value from textfield
			String nameVal = passName.getText().trim();
			String adultsStrVal = numAdults.getText().trim();
			String childStrVal = numChildren.getText().trim();

			// some validation
			valid = true;

			// NUMBER OF ADULT & CHILD VALIDATION
			boolean validAdult, validChild;
			validAdult = true;
			validChild = true;

			if (validAdult && validChild) {
				errInd2.setText("");
				errText2.setText("");
				errInd3.setText("");
				errText3.setText("");
			}

			try {
				int adultsVal = Integer.parseInt(adultsStrVal);
				if (adultsVal < 0) {
					valid = false;
				}

			} catch (NumberFormatException e) {
				errInd2.setText("ERROR");
				errText2.setText("Invalid number!");
				validAdult = false;
			}
			

			try{
				int childVal = Integer.parseInt(childStrVal);
				if (childVal < 0) {
					valid = false;
				} 
			} catch (NumberFormatException e) {
				errInd3.setText("ERROR");
				errText3.setText("Invalid number!");
				validChild = false;
			}

			// PASSENGER NAME VALIDATION
			boolean validName = true;
			if (nameVal.length() == 0) {
				errInd1.setText("ERROR");
				errText1.setText("Empty field!");
				validName = false;
			} else {
				for (int i=0; i<nameVal.length(); i++) {
					if (Character.isDigit(nameVal.charAt(i))) {
						errInd1.setText("ERROR");
						errText1.setText("Name can't contain numbers!");
						validName = false;
						break;
					}
				}
			}

			if (validName) {
				errInd1.setText("");
				errText1.setText("");
			} else {
				valid = false;
			}


			if (validAdult && validChild) {
				if ( Integer.parseInt(adultsStrVal) == 0 
					&& Integer.parseInt(childStrVal) == 0) {
					errInd2.setText("ERROR");
					errText2.setText("Can't be set to zero!");
					errInd3.setText("ERROR");
					errText3.setText("Can't be set to zero!");
					valid = false;
				} else {
					errInd2.setText("");
					errText2.setText("");
					errInd3.setText("");
					errText3.setText("");
				}
			} 
			
			// pass validation & create new passenger
			if (valid && validAdult && validChild && 
				passenger == null) {
				passenger = new Passenger(nameVal, 
					Integer.parseInt(adultsStrVal), 
					Integer.parseInt(childStrVal));
				setVisible(false);

			// pass validation & edit passenger
			} else if (valid && validAdult && validChild &&
				passenger != null) {
				passenger.setName(nameVal);
				passenger.setNumAdults(Integer.parseInt(adultsStrVal));
				passenger.setNumChildren(Integer.parseInt(childStrVal));
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
			passName.setText("");
			numAdults.setText("");
			numChildren.setText("");
			errInd1.setText("");
			errInd2.setText("");
			errInd3.setText("");
			errText1.setText("");
			errText2.setText("");
			errText3.setText("");
		}

		else if (ae.getSource() == cancelBtn)
		{
			// close dialog
			setVisible(false);
		}
	}

	// getter setter
	public Passenger getPassenger() {return passenger;}
	public void setPassenger(Passenger p) {passenger = p;}

}
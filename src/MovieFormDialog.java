/**
 * A class to create a dialog that is to be used
 * as input form to input and edit Movie's details
 * @author Siva Aditya (E1800176)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovieFormDialog extends JDialog implements ActionListener
{
	// attributes
	private Movie movie;
	private boolean valid;

	// dialog attributes
	JTextField movieTitle, movieLength;
	private JButton okBtn, resetBtn, cancelBtn;
	private JLabel errInd1, errInd2;
	private JLabel errText1, errText2;

	// FONT PACK
	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);
	final Font CALIBRI18 = new Font("Calibri", Font.PLAIN, 18);

	public MovieFormDialog(JFrame parentMenu)
	{	
		// attributes
		super(parentMenu, true);
		movie = null;
		valid = true;

		// HEADER
		JPanel title = new JPanel(new FlowLayout());
		JLabel titleLbl = new JLabel("In-Flight Movie Details Form");
		titleLbl.setFont(CALIBRI18);
		title.add(titleLbl);

		// TOP PANEL
		JPanel top = new JPanel(new GridLayout(2,1));
		top.add(title);
		top.add(new Label(""));

		// INPUT TEXT
		JPanel inputText;
		inputText = new JPanel(new GridLayout(0, 1, 0, 15));
		JLabel mvTitleLbl = new JLabel("Movie Title");
		mvTitleLbl.setFont(CALIBRI14);
		JLabel mvLengthLbl = new JLabel("Movie Length (Minutes)");
		mvLengthLbl.setFont(CALIBRI14);
		errInd1 = new JLabel(""); 
		errInd1.setFont(CALIBRI14); errInd1.setForeground(Color.RED);
		errInd2 = new JLabel(""); 
		errInd2.setFont(CALIBRI14); errInd2.setForeground(Color.RED);
		inputText.add(mvTitleLbl);
		inputText.add(errInd1);
		inputText.add(mvLengthLbl);
		inputText.add(errInd2);

		// INPUT FIELD
		JPanel inputField;
		inputField = new JPanel(new GridLayout(0, 1, 0 ,11));
		movieTitle = new JTextField(18);
		movieTitle.setFont(CALIBRI14);
		movieLength = new JTextField(18);
		movieLength.setFont(CALIBRI14);
		errText1 = new JLabel(""); 
		errText1.setFont(CALIBRI14); errText1.setForeground(Color.RED);
		errText2 = new JLabel(""); 
		errText2.setFont(CALIBRI14); errText2.setForeground(Color.RED);
		inputField.add(movieTitle);
		inputField.add(errText1);
		inputField.add(movieLength);
		inputField.add(errText2);

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
		setTitle("Movie Form");
		setLayout(new BorderLayout());
		getContentPane().add(top, BorderLayout.NORTH);
		getContentPane().add(inputPanel, BorderLayout.CENTER);
		getContentPane().add(bottom, BorderLayout.SOUTH);
		setSize(new Dimension(500,300));
		setLocation(400,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == okBtn)
		{
			// get value from textfield
			String titleVal = movieTitle.getText().trim();
			String lengthVal = movieLength.getText().trim();

			// some validation
			valid = true;

			// TITLE VALIDATION
			if (titleVal.length() == 0) {
				errInd1.setText("ERROR");
				errText1.setText("Empty field!");
				valid = false;
			} else {
				errInd1.setText("");
				errText1.setText("");
			}

			// LENGTH VALIDATION
			boolean validLength = true;
			try {
				int length = Integer.parseInt(lengthVal);
			} catch (NumberFormatException e) {
				errInd2.setText("ERROR");
				errText2.setText("Invalid number!");
				validLength = false;
			}

			if (validLength) {
				errInd2.setText("");
				errText2.setText("");
				
				if (Integer.parseInt(lengthVal) <= 0) {
					errInd2.setText("ERROR");
					errText2.setText("Invalid number!");
					valid = false;
				}
			}

			// pass validation & create new movie
			if (valid && validLength && movie == null) {
				movie = new Movie(titleVal, Integer.parseInt(lengthVal));
				setVisible(false);
			
			// pass validation & edit movie
			} else if (valid && validLength && movie != null) {
				movie.setTitle(titleVal);
				movie.setLength(Integer.parseInt(lengthVal));
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
			movieTitle.setText("");
			movieLength.setText("");
			errInd1.setText("");
			errInd2.setText("");
			errText1.setText("");
			errText2.setText("");
		}

		else if (ae.getSource() == cancelBtn)
		{
			// close dialog
			setVisible(false);
		}
	}

	// getter and setter
	public Movie getMovie() {return movie;}
	public void setMovie(Movie m) {movie = m;}
}
/**
 * A class to create a dialog to ask
 * user to choose Flight type before
 * adding new Flight to collection
 * @author Siva Aditya (E1800176)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlightTypeDialog extends JDialog implements ActionListener
{
	// attributes
	private char type;
	private JButton businessBtn, economyBtn;

	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);

	public FlightTypeDialog(JFrame parentMenu)
	{
		// attributes
		super(parentMenu, true);
		type = '?';

		// HEADER
		JPanel title = new JPanel(new FlowLayout());
		JLabel titleLbl = new JLabel("Please choose flight type!");
		titleLbl.setFont(CALIBRI14);
		title.add(titleLbl);

		// TOP PANEL
		JPanel topPanel = new JPanel(new GridLayout(0,1));
		topPanel.add(title);
		topPanel.add(new Label(""));

		// BUTTON
		businessBtn = new JButton("BUSINESS");
		businessBtn.setFont(CALIBRI14);
		businessBtn.setBackground(Color.WHITE);
		economyBtn = new JButton("ECONOMY");
		economyBtn.setFont(CALIBRI14);
		economyBtn.setBackground(Color.WHITE);
		businessBtn.addActionListener(this);
		economyBtn.addActionListener(this);

		// MAIN PANEL
		JPanel mainPanel = new JPanel(new GridLayout(0,1,0,15));
		mainPanel.add(businessBtn);
		mainPanel.add(economyBtn);

		// DIALOG LAYOUT
		setTitle("Flight Type");
		setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(new Label(""), BorderLayout.WEST);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(new Label(""), BorderLayout.EAST);
		getContentPane().add(new Label(""), BorderLayout.SOUTH);
		setSize(new Dimension(300,200));
		setLocation(500,220);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == businessBtn) {
			setType('B');
			setVisible(false);
		
		} else if (ae.getSource() == economyBtn) {
			setType('E');
			setVisible(false);
		}
	}

	// getter setter
	public char getFlightType() {return type;}
	public void setType(char type) {this.type = type;}
}
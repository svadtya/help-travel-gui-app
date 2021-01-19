/**
 * A class to create Menu Passenger panel
 * @author Siva Aditya (E1800176)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class PassengerMenuPanel extends JPanel implements ActionListener
{	
	// parent frame component
	private TravelAgent tva;
	private int tvaPassenger;
	private TravelGUI gui;
	private JFrame mainFrame;
	// last condition
	private char sort;

	// panel component
	private JTextField numOfPass;
	private JButton addPassBtn, editPassBtn, removePassBtn, clearBtn;
	private JRadioButton bookingRef, name;

	// table
	private JTable passTable;
	private PassengerTableModel passTableMod;

	// FORMAT & FONT PACK
	final DefaultTableCellRenderer CENTERCELL;
	final DefaultTableCellRenderer RIGHTCELL;
	final Font CALIBRI14 = new Font("Calibri", Font.PLAIN, 14);
	final Font CALIBRI18 = new Font("Calibri", Font.PLAIN, 18);
	final Font CALIBRI22 = new Font("Calibri", Font.PLAIN, 22);

	public PassengerMenuPanel(TravelAgent travelAgent,
							  TravelGUI gui, char sort)
	{	
		// parent frame component
		tva = travelAgent;
		this.gui = gui;
		mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.sort = sort;

		// TITLE
		JPanel title = new JPanel(new FlowLayout());
		JLabel menuTitle = new JLabel("Passenger in Collection");
		menuTitle.setFont(CALIBRI22);
		title.add(menuTitle);

		// NORTH PANEL
		JPanel northPanel = new JPanel(new GridLayout(0,1));
		northPanel.add(title);
		northPanel.add(new JLabel(""));

		// SORT BUTTON
		ButtonGroup sortBtn = new ButtonGroup();
		// set sort according to panel last recorded sort value
		switch (sort)
		{
			case 'B':
				bookingRef = new JRadioButton("Booking Ref", true);
				name = new JRadioButton("Name", false);
				break;
			case 'N':
				bookingRef = new JRadioButton("Booking Ref", false);
				name = new JRadioButton("Name", true);
				break;
			default: break;

		}
	
		bookingRef.setFont(CALIBRI14);
		name.setFont(CALIBRI14);
		sortBtn.add(bookingRef);
		sortBtn.add(name);
		bookingRef.addActionListener(this);
		name.addActionListener(this);

		// SORT SECTION
		JPanel sortSect = new JPanel(new FlowLayout());
		JLabel sortLbl = new JLabel("Sort");
		sortLbl.setFont(CALIBRI14);
		sortSect.add(sortLbl);
		sortSect.add(bookingRef);
		sortSect.add(name);

		// NUMBER OF PASSENGER SECTION
		JPanel numPassSect = new JPanel(new FlowLayout());
		JLabel numPass = new JLabel("Number of Passenger");
		numPass.setFont(CALIBRI14);
		numPassSect.add(numPass);
		tvaPassenger = tva.getNumOfPassenger();
		numOfPass = new JTextField(String.format("%s",tvaPassenger),4);
		numOfPass.setHorizontalAlignment(JTextField.CENTER);
		numOfPass.setEditable(false);
		numOfPass.setFont(CALIBRI14);
		numPassSect.add(numOfPass);

		// SORT PANEL
		JPanel midTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		midTopPanel.add(sortSect);
		midTopPanel.add(new Label("     "));
		midTopPanel.add(numPassSect);

		// setting up table
		passTableMod = new PassengerTableModel(tva);
		passTable = new JTable(passTableMod);
		passTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		passTable.setFont(CALIBRI14);
		CENTERCELL = new DefaultTableCellRenderer();
		CENTERCELL.setHorizontalAlignment(JLabel.CENTER);
		RIGHTCELL = new DefaultTableCellRenderer();
		RIGHTCELL.setHorizontalAlignment(JLabel.RIGHT);
		passTable.getColumnModel().
				getColumn(0).setCellRenderer(CENTERCELL);
		passTable.getColumnModel().
				getColumn(2).setCellRenderer(CENTERCELL);
		passTable.getColumnModel().
				getColumn(3).setCellRenderer(CENTERCELL);
		passTable.getColumnModel().
				getColumn(4).setCellRenderer(CENTERCELL);
		passTable.getColumnModel().
				getColumn(5).setCellRenderer(RIGHTCELL);
		JScrollPane tabPane = new JScrollPane(passTable);

		// CENTER PANEL
		JPanel midPanel = new JPanel(new BorderLayout());
		midPanel.add(midTopPanel, BorderLayout.NORTH);
		midPanel.add(tabPane, BorderLayout.CENTER);

		// MENU BUTTON
		JPanel buttonPanel;
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15,0));
		addPassBtn = new JButton("Add New Passenger");
		addPassBtn.setFont(CALIBRI18);
		addPassBtn.setBackground(Color.WHITE);
		editPassBtn = new JButton("Edit Passenger Details");
		editPassBtn.setFont(CALIBRI18);
		editPassBtn.setBackground(Color.WHITE);
		removePassBtn = new JButton("Remove Passenger");
		removePassBtn.setFont(CALIBRI18);
		removePassBtn.setBackground(Color.WHITE);
		clearBtn = new JButton("Clear Collection");
		clearBtn.setFont(CALIBRI18);
		clearBtn.setBackground(Color.WHITE);
		buttonPanel.add(addPassBtn);
		buttonPanel.add(editPassBtn);
		buttonPanel.add(removePassBtn);
		buttonPanel.add(clearBtn);
		addPassBtn.addActionListener(this);
		editPassBtn.addActionListener(this);
		removePassBtn.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Sort booking ref
		if (bookingRef.isSelected()) {
			// sort passenger collection
			tva.sortPassengerBookRef();
			gui.refreshPassMenu('B');
		
		}
		// Sort name
		else if (name.isSelected()) {
			// sort passenger collection
			tva.sortPassengerName();
			// refresh panel
			gui.refreshPassMenu('N');
		}

		// Add new passenger
		if (ae.getSource() == addPassBtn)
		{
			// display passenger form dialog
			PassengerFormDialog inputDialog;
			inputDialog = new PassengerFormDialog(mainFrame);
			inputDialog.setLocationRelativeTo(this);
			inputDialog.setVisible(true);

			Passenger newPass = inputDialog.getPassenger();

			if (newPass != null){
				JOptionPane.showMessageDialog(this, 
					"Added to collection! Booking Ref: "+
							newPass.getBookingRef());
				passTableMod.addRow(newPass);
				// sort table data
				if (sort == 'B') {tva.sortPassengerBookRef();}
				else if (sort == 'N') {tva.sortPassengerName();}
				// refresh panel
				gui.refreshPassMenu(sort);
			}

			else
				JOptionPane.showMessageDialog(this,
						"Canceled!");
		
		}  

		// Edit passenger details
		else if (ae.getSource() == editPassBtn) 
		{
			// validate table
			if (passTable.getRowCount() == 0)
				JOptionPane.showMessageDialog(this,
						"Passenger is not added");
			else {
				int rowIndex = passTable.getSelectedRow();
				if (rowIndex != -1) {
					Passenger p = passTableMod.getElementAt(rowIndex);
					PassengerFormDialog editDialog;
					editDialog = new PassengerFormDialog(mainFrame);
					// set selected passenger to dialog
					editDialog.setPassenger(p);
					// fill the dialog's textfield with
					// the passenger's details
					editDialog.passName.setText(p.getName());
					editDialog.numAdults.setText(
							String.valueOf(p.getNumAdults()));
					editDialog.numChildren.setText(
							String.valueOf(p.getNumChildren()));
					editDialog.setLocationRelativeTo(this);
					editDialog.setVisible(true);
					// refresh panel
					gui.refreshPassMenu(sort);
					
				} else 
					JOptionPane.showMessageDialog(this,
							"Please select a row");
			}

		} 

		// Remove Passenger
		else if (ae.getSource() == removePassBtn) 
		{
			if (passTable.getRowCount() == 0)
				JOptionPane.showMessageDialog(this,
						"Passenger is not added");
			else {
				int rowIndex = passTable.getSelectedRow();
				if (rowIndex != -1) {
					Passenger p = passTableMod.getElementAt(rowIndex);
					int remove = JOptionPane.showConfirmDialog(this,
						"Are you sure " +
								"you want to delete Passenger ["
								+p.getBookingRef()+"]?",
						"Confirm Delete",
							JOptionPane.YES_NO_OPTION);
					if (remove == JOptionPane.YES_OPTION){
						passTableMod.removeRow(rowIndex);
						// refresh panel
						gui.refreshPassMenu(sort);
					}
				} else 
					JOptionPane.showMessageDialog(this,
							"Please select a row");
			}

		}

		// Clear collection
		else if (ae.getSource() == clearBtn)
		{
			if (passTable.getRowCount() == 0)
				JOptionPane.showMessageDialog(this,
						"Passenger is not added");
			else {
				int remove = JOptionPane.showConfirmDialog(this,
						"Are you sure " +
								"you want to clear Passenger Collection?",
						"Confirm Clear Collection",
						JOptionPane.YES_NO_OPTION);
				if (remove == JOptionPane.YES_OPTION){
					passTableMod.clear();
					// refresh panel
					gui.refreshPassMenu(sort);
				}
			}
		}
	}

	// getter setter
	public char getSort() {return sort;}
	public void setSort(char sort) {this.sort = sort;}

} 
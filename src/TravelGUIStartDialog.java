/**
 * A class to create a program start dialog for TravelGUI
 * that prompt the user to either create a new data for
 * the program or to load existing data from a file
 * @author Siva Aditya (E1800176)
 */


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class TravelGUIStartDialog extends JDialog implements ActionListener
{
	// attributes
	private TravelAgent tva;
	private JButton createBtn, loadBtn;

	// file attributes
	final JFileChooser fc = new JFileChooser();
	File file = null;
	private final TravelAgentFileHandler fileHandler;

	// ICON & FONT PACK
	final Icon icon = new ImageIcon(this.getClass().getResource("/resource/helpUni.png"));
	final Font CALIBRI18 = new Font("Calibri", Font.PLAIN, 18);
	final Font CALIBRI22 = new Font("Calibri", Font.BOLD, 22);

	public TravelGUIStartDialog(JFrame parentFrame)
	{
		// initialize attribute & parent
		super(parentFrame, true);
		tva = new TravelAgent();
		fileHandler = new TravelAgentFileHandler(parentFrame);

		// HEADER 
		JPanel title = new JPanel(new FlowLayout());
		JLabel titleLbl = new JLabel("HELP Travel Agency Program");
		titleLbl.setFont(CALIBRI22);
		title.add(titleLbl);

		// ICON LABEL
		JPanel iconPn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel iconLbl = new JLabel();
		iconLbl.setIcon(icon);
		iconPn.add(iconLbl);

		// TOP PANEL
		JPanel topPanel = new JPanel(new GridLayout(0,1));
		topPanel.add(new Label(""));
		topPanel.add(iconPn);
		topPanel.add(title);

		// BUTTON PANEL
		JPanel buttonPanel = new JPanel(new GridLayout(0,1,0,15));
		createBtn = new JButton("Create New Data");
		createBtn.setFont(CALIBRI18);
		createBtn.setBackground(Color.WHITE);
		loadBtn = new JButton("Load Data from File");
		loadBtn.setFont(CALIBRI18);
		loadBtn.setBackground(Color.WHITE);
		buttonPanel.add(createBtn);
		buttonPanel.add(loadBtn);
		buttonPanel.add(new Label(""));

		createBtn.addActionListener(this);
		loadBtn.addActionListener(this);

		// SOUTH PANEL
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		southPanel.add(new Label("@author Siva Aditya (E1800176)"));

		// DIALOG LAYOUT
		setTitle("BIT203 Assignment 2");
		setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(new Label(""), BorderLayout.WEST);
		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		getContentPane().add(new Label(""), BorderLayout.EAST);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		setSize(new Dimension(650,500));
		setLocation(350,80);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
        {
			// add window listener to ask for confirmation for closing
			// the program
			@Override
            public void windowClosing(WindowEvent e)
            {
                int close = JOptionPane.showConfirmDialog(getParent(),
						"Exit Program?",
						"Program Close",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
                if (close == JOptionPane.YES_OPTION){
                	// exit dialog and frame, thus, program
                	System.exit(0);
                }
            }
        });

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// create new data
		if (ae.getSource() == createBtn) {
			String tvaName;
			try {
			 	tvaName = JOptionPane.showInputDialog("Enter " +
						"Travel Agent Name :");
				 if (tvaName.length() == 0) {
					JOptionPane.showMessageDialog(this,
							"Empty Field!",
						"Input Name Error", JOptionPane.ERROR_MESSAGE);
				} else 
					tva = new TravelAgent(tvaName);
					JOptionPane.showMessageDialog(this,
							"Hello there!");
					setVisible(false);

			} catch (NullPointerException e){
					JOptionPane.showMessageDialog(this,
							"Canceled!");
			}
		}

		// load data from a file
		try {
			if (ae.getSource() == loadBtn) {
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
					setVisible(false);
				}
			}

		} catch (IOException ioe) {
			System.out.println("--ERROR: " +
					"File handling TravelGUI program start");
		
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, 
				"Can't load file!",
				"Unexpected Error", JOptionPane.ERROR_MESSAGE);
			System.out.println("--ERROR: " +
					"Loading file TravelGUI program start");
		}
		
	}

	// getter
	public TravelAgent getTravelAgent() {return tva;}
}
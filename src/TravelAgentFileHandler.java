/**
 * A class to handle saving/loading file event
 * of TravelGUI program
 * @author Siva Aditya (E1800176)
 */

import javax.swing.*;
import java.io.*;

public class TravelAgentFileHandler {

	// attributes
	private TravelAgent tva;
	JFrame parentFrame;	

	// file loading/saving attributes
	final JFileChooser fileChooser = new JFileChooser();
	private File file;		// the file to be chosen

	// constructor
	public TravelAgentFileHandler(JFrame parentFrame) {
		tva = null;
		file = null;
		this.parentFrame = parentFrame;
	}

	// getter setter
	public TravelAgent getTravelAgent() {return tva;}
	public File getFile() {return file;}
	public void setTravelAgent(TravelAgent tva) {this.tva = tva;}
	public void setFile(File file) {this.file = file;}

	/**
	 * A method to load a data from a file
	 * @param file chosen to load from
	 * @return TravelAgent object from loaded file
	 * @throws IOException loading error
	 */
	public TravelAgent loadFromFile(File file)
			throws IOException
	{	
		this.file = file;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			tva = (TravelAgent) ois.readObject();
			ois.close();
		
		} catch (IOException ioe) {
			System.out.println("--ERROR: "+ioe.getMessage());
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return tva; 

	}

	/**
	 * A method to display JFileChooser to let user
	 * to save data in a chosen directory under a chosen
	 * file name
	 * @param travelAgent object that is to be saved to file
	 */
	public void showSaveFileChooser(TravelAgent travelAgent)
	{
		int saveVal = fileChooser.showSaveDialog(parentFrame);
		if (saveVal == JFileChooser.APPROVE_OPTION)
		{
			file = fileChooser.getSelectedFile();
			try {
				saveAsToFile(travelAgent);
			} catch (IOException ioe) {
				System.out.println("--ERROR: "+ioe.getMessage());

			} finally {
				System.out.println("--INFO: File saved as " +
						file.getPath());
			}

		} else 
			System.out.println("--INFO: Save command cancelled by user");
		
	}

	/**
	 * A method to save TravelObject file to the program directory
	 * folder with filename automatically set to be the
	 * travel agent name + .TravelGUI in a txt format file
	 * @param fileName is the file name
	 * @param travelAgent is the object that is to be saved in file
	 * @throws IOException
	 */
	public void saveToFile(String fileName, TravelAgent travelAgent)
			throws IOException {
		File saveFile = new File(fileName+".TravelGUI.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(saveFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(travelAgent);
			oos.flush();
			oos.close();
		
		} catch (IOException ioe) {
			System.out.println("--ERROR: "+ioe.getMessage());
		
		} finally {
			if (fos != null) fos.close();
			System.out.println("--INFO: File saved as " +
					saveFile.getPath());
		}
	}

	/**
	 * A method to save data to a specified filename and
	 * directory specified in JFileChooser
	 * @param travelAgent is the object that is to be saved in file
	 * @throws IOException saving error
	 */
	public void saveAsToFile(TravelAgent travelAgent)
			throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(travelAgent);
			oos.flush();
			oos.close();
		
		}  catch (IOException ioe) {
			System.out.println("--ERROR: "+ioe.getMessage());

		} finally {
			if (fos != null) fos.close();
		}
	}
}
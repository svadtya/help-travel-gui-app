/**
 * A class to create list model for
 * ArrayList Movie in Flight
 * @author Siva Aditya (E1800176)
 */

import java.util.*;
import javax.swing.*;
import java.io.Serializable;

public class MovieListModel extends AbstractListModel
		implements Serializable
{
	private Flight flight;
	private ArrayList<Movie> movieList;

	// constructor
	public MovieListModel() {
		flight = null;
		setMovieList(new ArrayList<Movie>());
	}

	public MovieListModel(Flight flight) {
		this.flight = flight;
		setMovieList(flight.getMovieList());
	}

	// getter setter
	private ArrayList<Movie> getMovieList() {return movieList;}
	private void setMovieList(ArrayList<Movie> mvList) {
		movieList = mvList;
	}

	@Override
	public Object getElementAt(int index)
	{
		return getMovieList().get(index);
	}

	@Override
	public int getSize() {return getMovieList().size();}

	public void addElement(Movie movie)
	{
		int index = getSize();
		getMovieList().add(movie);
		flight.setNumMovies(flight.getNumMovies()+1);
		fireIntervalAdded(this, index, index);
	}

	public Movie removeElement(Movie movie)
	{
		int index = getMovieList().indexOf(movie);
		if (index != -1)
		{
			getMovieList().remove(movie);
			flight.setNumMovies(flight.getNumMovies()-1);
			fireIntervalRemoved(this, index, index);
		}

		return movie;
	}

	public void clear()
	{
		int index = getSize() - 1;
		getMovieList().clear();
		flight.setNumMovies(0);
		if (index >= 0)
			fireIntervalRemoved(this, 0, index);
	}

	public boolean contains(Movie movie) {return flight.checkMovie(movie);}
}
/**
 * A class to create Movie object
 * @author Siva Aditya (E1800176)
 */

import java.io.Serializable;

public class Movie implements Comparable<Movie>, Cloneable, Serializable
{
    // instance variables
    private String title;
    private int length;

    // default constructor
    public Movie() {this("unknown", 0);}

    // constructor with arguments
    public Movie(String title, int length) {
        // Movie title is capitalized
        this.title = title.toUpperCase();
        this.length = length;
    }

    // getter method
    public String getTitle() {return title;}
    public int getLength() {return length;}

    // setter method
    public void setTitle(String title) {this.title = title.toUpperCase();}
    public void setLength(int length) {this.length = length;}

    public boolean equals(Object obj){
        if (this == obj) // same object
            return true;
        if (!(obj instanceof Movie)) // not the same class
            return false;
        Movie mv = (Movie) obj;
        if (this.getTitle().equals(mv.getTitle())) // same title
            return true;
        else
            return false;
    }

    @Override
    public int compareTo(Movie obj) {
        if (this == obj)
            return 0;
        if (this.equals(obj))
            return 0;

        // compare by title
        return title.compareTo(obj.getTitle());
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        }

        catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String toString(){
        return title+" ("+length+" minutes)";
    }

}

package Service;
import Repository.*;
import Domain.Movie;
import com.sun.jmx.mbeanserver.Repository;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private IRepository <Movie> movieRepository;



    public MovieService (IRepository <Movie> movieRepository){
        this.movieRepository = movieRepository;
    }

    /**
     * adds a movie
     *
     * @param id - movie id
     * @param name- movie name
     * @param year - movie year
     * @param price -  price ticket for this movie
     * @param onScreens - boolean for on screen or not
     */
    public void insert (String id, String name, int year, double price, boolean onScreens){
        Movie movie = new Movie (id, name, year, price, onScreens);
        movieRepository.insert( movie );
    }

    /**
     *
     * to update a movie
     * @param id
     * @param name
     * @param year
     * @param price
     * @param onScreens
     */
    public void update (String id, String name, int year, double price, boolean onScreens){
        Movie movie = new Movie (id, name, year, price, onScreens);
        movieRepository.update( movie );
    }

    /**
     * delete a movie
     * @param id
     */
    public void remove (String id){
        movieRepository.remove( id );
    }

    public List <Movie> getAll(){
        return movieRepository.getAll();
    }

    public List<Movie> fullTextSearch(String text) {
        List<Movie> found = new ArrayList <>( );
        for (Movie m : movieRepository.getAll()){
            if ((m.getId().contains(text))||
                 m.getName().contains(text) ||
                 Integer.toString( m.getYear()).contains( text )||
                 Double.toString( m.getPrice()).contains(text) ||
                 Boolean.toString( m.isOnScreens() ).contains( text ) &&
                 !found.contains( m )){
                 found.add(m);
            }
        }return found;
    }
}

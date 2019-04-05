package Tests;

import Domain.IValidator;
import Domain.Movie;
import Domain.MovieValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.MovieService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


 class MovieTest {
    private IValidator < Movie > validatorMovie = new MovieValidator();
    private IRepository<Movie> movieRepository = new InMemoryRepository <>( validatorMovie );
    private MovieService movieService= new MovieService( movieRepository );


    @Test
     void setNameYearPriceOnScreen() {
        movieService.insert( "1", "Mama", 2000, 32.00, true );
        movieService.getAll().get( 0 ).setName( "Mama" );
        assertEquals( "Mama", movieService.getAll().get( 0).getName( ));

        movieService.getAll().get(0).setYear(2008);
        assertEquals(2008,movieService.getAll().get(0).getYear());

        movieService.getAll().get(0).setOnScreens(false);
        assertFalse(movieService.getAll().get(0).isOnScreens());

        movieService.getAll().get(0).setPrice(40);
        assertEquals(40,movieService.getAll().get(0).getPrice());

    }

    @Test
    public void getName() {
    }

    @Test
    public void setName() {
    }

    @Test
    public void getYear() {
    }

    @Test
    public void setYear() {
    }

    @Test
    public void getPrice() {
    }

    @Test
    public void setPrice() {
    }

    @Test
    public void isOnScreens() {
    }

    @Test
    public void setOnScreens() {
    }
}

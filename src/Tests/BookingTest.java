package Tests;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.BookingService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;
class BookingTest {
    private IValidator < Movie > validatorMovie = new MovieValidator();
    private IValidator < Client > validatorCard = new ClientValidator();
    private IValidator < Booking > validatorTransaction = new BookingValidator();

    private IRepository < Movie > movieRepository = new InMemoryRepository <>( validatorMovie );
    private IRepository < Client > clientRepository = new InMemoryRepository <>( validatorCard );
    private IRepository < Booking > bookingRepository = new InMemoryRepository <>( validatorTransaction );


    private BookingService bookingService = new BookingService( bookingRepository, clientRepository, movieRepository );

    private Movie movie1 = new Movie( "1", "batman", 2000, 32, true );
    private Client card1 = new Client( "1", "aaa", "bbb", "2222222222222", LocalDate.of( 2012, 11, 22 ),
            LocalDate.of( 2013, 12, 11 ), 15 );


    @Test
    void getIdMovie() {
        movieRepository.insert( movie1 );
        clientRepository.insert( card1 );
        bookingService.insert( "5", "1", "1", LocalDate.of( 2012, 11, 10 ), LocalTime.of( 20, 0 ) );

        assertEquals( "1", bookingService.getAll().get( 0 ).getIdMovie() );
    }

    @Test
    void setIdMovieIdCardDateTimeEqualsAndToString() {
        movieRepository.insert( movie1 );
        clientRepository.insert( card1 );
        bookingService.insert( "5", "1", "1", LocalDate.of( 2012, 11, 10 ), LocalTime.of( 20, 0 ) );

        bookingService.getAll().get( 0 ).setIdMovie( "6" );
        assertEquals( "6", bookingService.getAll().get( 0 ).getIdMovie() );

        bookingService.getAll().get( 0 ).setIdClient( "3" );
        assertEquals( "3", bookingService.getAll().get( 0 ).getIdClient() );

        bookingService.getAll().get( 0 ).setDate( LocalDate.of( 2000, 11, 20 ) );
        assertEquals( LocalDate.of( 2000, 11, 20 ), bookingService.getAll().get( 0 ).getDate() );

        bookingService.getAll().get( 0 ).setTime( LocalTime.of( 13, 37 ) );
        assertEquals( LocalTime.of( 13, 37 ), bookingService.getAll().get( 0 ).getTime() );

        assertEquals( bookingService.getAll().get( 0 ), new Booking( "5", "1", "1", LocalDate.of( 2012, 11, 10 ), LocalTime.of( 20, 0 ) ) );

        assertTrue( bookingService.getAll().get( 0 ).toString().contains( "5" ) );

    }
}

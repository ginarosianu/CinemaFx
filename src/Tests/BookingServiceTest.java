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
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingServiceTest {

    private IValidator < Movie > validatorMovie = new MovieValidator();
    private IValidator<Client> validatorCard = new ClientValidator();
    private IValidator< Booking > validatorTransaction = new BookingValidator();

    private IRepository <Movie> movieRepository = new InMemoryRepository <>(validatorMovie);
    private IRepository<Client> customerCardRepository = new InMemoryRepository<>(validatorCard);
    private IRepository<Booking> bookingRepository = new InMemoryRepository<>(validatorTransaction);


    private BookingService bookingService = new BookingService(bookingRepository, customerCardRepository, movieRepository );

    private Movie movie1 = new Movie("1", "batman", 2000, 32, true);
    private Client card1 = new Client("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
            LocalDate.of(2013,12,11), 15);

    @org.junit.jupiter.api.Test
    void insert() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));

        assertEquals(1, bookingService.getAll().size());
    }

    @org.junit.jupiter.api.Test
    void update() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));
        bookingService.update("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(12,0));


        assertEquals(LocalTime.of(12,0), bookingService.getAll().get(0).getTime());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));
        bookingService.remove("1");

        assertEquals(0,bookingService.getAll().size());
    }

    @org.junit.jupiter.api.Test
    void getAll() {

    }

    @Test
    void fullTextSearch() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));
        String text = "2012";

        bookingService.fullTextSearch(text);

        assertEquals(1,bookingService.fullTextSearch(text).size());
    }
}
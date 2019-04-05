

import Domain.*;

import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;
import Service.MovieService;
import Service.BookingService;

import UI.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
//import UI.NewUI;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI/mainWindow.fxml"));
        Parent root = fxmlLoader.load();

        MainController mainController = fxmlLoader.getController();


        IValidator<Movie> validatorMovie = new MovieValidator();
        IValidator<Client> validatorCard = new ClientValidator();
        IValidator<Booking> validatorTransaction = new BookingValidator();

        IRepository<Movie> movieRepository = new InMemoryRepository<>(validatorMovie);
        IRepository<Client> customerCardRepository = new InMemoryRepository<>(validatorCard);
        IRepository<Booking> bookingRepository = new InMemoryRepository<>(validatorTransaction);

        MovieService movieService = new MovieService(movieRepository);
        movieService.insert( "1", "Lacul", 1999, 20.00,true );
        movieService.insert( "2", "Raul", 2000, 22.00,false );
        movieService.insert( "3", "Dealul", 2008, 35.00,true );
        movieService.insert( "4", "Muntele", 2011, 12.00,true );
        movieService.insert( "5", "Marea", 2019, 18.00,true );


        ClientService clientService = new ClientService(customerCardRepository);
        clientService.insert("1","Maria", "Popa", "1111111111111", LocalDate.of (1999,01,01), LocalDate.of(2000,01,01), 0);
        clientService.insert("2","Ion", "Dascal", "2222222222222", LocalDate.of (2000,07,25), LocalDate.of(2002,01,01), 7);
        clientService.insert("3","Alex", "Morar", "3333333333333", LocalDate.of (1980,8,22), LocalDate.of(2007,01,01), 0);
        clientService.insert("4","Mihai", "Pop", "4444444444444", LocalDate.of (1977,9,11), LocalDate.of(2010,01,01), 10);
        clientService.insert("5","Ana", "Ionescu", "5555555555555", LocalDate.of (2001,12,7), LocalDate.of(2018,01,01), 0);



        BookingService bookingService = new BookingService(bookingRepository, customerCardRepository, movieRepository );
        bookingService.insert("1", "1", "5", LocalDate.of(2007,10,04), LocalTime.of(12,45));
        bookingService.insert("2", "2", "7", LocalDate.of(2009,7,07), LocalTime.of(13,00));
        bookingService.insert("3", "3", "25", LocalDate.of(2010,12,21), LocalTime.of(21,30));
        bookingService.insert("4", "4", "23", LocalDate.of(2015,5,12), LocalTime.of(20,45));
        bookingService.insert("5", "5", "4", LocalDate.of(2014,9,17), LocalTime.of(19,15));

        mainController.setServices(movieService,clientService, bookingService);

        primaryStage.setTitle( "Cinema Manager" );
        primaryStage.setScene( new Scene( root, 1200, 700 ) );
        primaryStage.show();


        //Console console = new Console(movieService, customerCardService, bookingService);
        //console.runMenu();

//        NewUI console = new NewUI( movieService,customerCardService,bookingService);
//        console.runMenu();

    }
}

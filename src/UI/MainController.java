package UI;

import Service.BookingService;
import Service.ClientService;
import Service.MovieService;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {
    public TableView tableViewMovies;
    public TableColumn tableColumnIdMovie;
    public TableColumn tableColumnNameMovie;
    public TableColumn tableColumnYearMovie;
    public TableColumn tableColumnPriceMovie;
    public TableColumn tableColumnOnScreensMovie;
    
    public TableView tableViewCards;
    public TableColumn tableColumnIdCard;
    public TableColumn tableColumnNameCard;
    public TableColumn tableColumnSurnameCard;
    public TableColumn tableColumnCnpCard;
    public TableColumn tableColumnDateOfBirthCard;
    public TableColumn tableColumnDateOfRegistrationCard;
    public TableColumn tableColumnBonusPointsCard;
   
    public TableView tableViewBookings;
    public TableColumn tableColumnIdBooking;
    public TableColumn tableColumnIdMovieBooking;
    public TableColumn tableColumnIdCardBooking;
    public TableColumn tableColumnDateOfBooking;
    public TableColumn tableColumnTimeOfBooking;

    public void btnAddMovieClick(ActionEvent actionEvent) {
    }

    public void btnUpdateMovieClick(ActionEvent actionEvent) {
    }

    public void btnMovieDeleteClick(ActionEvent actionEvent) {
    }

    public void btnAddCardClick(ActionEvent actionEvent) {
    }

    public void btnUpdateCardClick(ActionEvent actionEvent) {
    }

    public void btnDeleteCardClick(ActionEvent actionEvent) {
    }

    public void btnAddBookingClick(ActionEvent actionEvent) {
    }

    public void btnUpdateBookingClick(ActionEvent actionEvent) {
    }

    public void btnDeleteBookingClick(ActionEvent actionEvent) {
    }

    public void setServices(MovieService movieService, ClientService clientService, BookingService bookingService) {
    }
}

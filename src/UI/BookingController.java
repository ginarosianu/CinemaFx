package UI;

import Service.BookingService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingController {
    public Spinner spnId;
    public Spinner spnIdMovie;
    public Spinner spnIdCard;

    public TextField txtBookingDay;
    public TextField txtBookingMonth;
    public TextField txtBookingYear;
    public TextField txtBookingHour;
    public TextField txtBookingMinutes;

    public Button btnAdd;
    public Button btnUpdate;
    public Button btnCancel;

    public BookingService bookingService;

    public void setService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void btnAddClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());
            String idMovie = String.valueOf(spnIdMovie.getValue());
            String idCard = String.valueOf(spnIdCard.getValue());
            LocalDate date = LocalDate.of(Integer.parseInt(txtBookingYear.getText()),
                    Integer.parseInt(txtBookingMonth.getText()), Integer.parseInt(txtBookingDay.getText()));
            LocalTime time = LocalTime.of(Integer.parseInt(txtBookingHour.getText()),
                    Integer.parseInt(txtBookingMinutes.getText()));

            bookingService.insert(id, idMovie, idCard, date, time);
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

       public void btnUpdateClick(ActionEvent actionEvent) {
           try {
               String id = String.valueOf(spnId.getValue());
               String idMovie = String.valueOf(spnIdMovie.getValue());
               String idCard = String.valueOf(spnIdCard.getValue());
               LocalDate date = LocalDate.of(Integer.parseInt(txtBookingYear.getText()),
                       Integer.parseInt(txtBookingMonth.getText()), Integer.parseInt(txtBookingDay.getText()));
               LocalTime time = LocalTime.of(Integer.parseInt(txtBookingHour.getText()),
                       Integer.parseInt(txtBookingMinutes.getText()));

               bookingService.update(id, idMovie, idCard, date, time);
               btnCancelClick(actionEvent);
           } catch (RuntimeException rex) {
               Common.showValidationError(rex.getMessage());
           }
       }
        public void btnCancelClick(ActionEvent actionEvent) {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
    }
}

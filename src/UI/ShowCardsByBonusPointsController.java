package UI;

import Domain.Client;
import Service.ClientService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowCardsByBonusPointsController {
    public TableView tableViewOrderedCards;
    public TableColumn tableColumnIdCard;
    public TableColumn tableColumnNameCard;
    public TableColumn tableColumnSurnameCard;
    public TableColumn tableColumnCnpCard;
    public TableColumn tableColumnDateOfBirthCard;
    public TableColumn tableColumnDateOfRegistrationCard;
    public TableColumn tableColumnBonusPointsCard;

    private ClientService clientService;

    private ObservableList< Client > cards = FXCollections.observableArrayList();

    public void setService(ClientService clientService) {
        this.clientService = clientService;
    }
    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            try {
                List <Client> cardsOrdered = clientService.getAll();
                cardsOrdered.sort( Comparator.comparing(Client::getBonusPoints).reversed());

                cards.addAll(cardsOrdered);
                tableViewOrderedCards.setItems(cards);
            } catch (RuntimeException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log( Level.SEVERE, "Failed to create new Window: Movies By Bookings.", e);
            }
        });
    }

}

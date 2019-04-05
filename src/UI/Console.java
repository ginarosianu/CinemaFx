package UI;

import Domain.Client;
import Domain.Client;
import Domain.Movie;
import Domain.Booking;
import Service.ClientService;
import Service.ClientService;
import Service.MovieService;
import Service.BookingService;

import java.util.Scanner;

public class Console {
    private MovieService movieService;
    private ClientService cardService;
    private BookingService bookingService;

    private Scanner scanner;

    public Console(MovieService movieService, ClientService cardService, BookingService bookingService) {
        this.movieService = movieService;
        this.cardService = cardService;
        this.bookingService = bookingService;

        this.scanner = new Scanner(System.in);
    }

    public void runMenu() {
        while (true) {
            showMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    runMovieCRUD();
                    break;
                case "2":
                    runCardsCRUD();
                    break;
                case "3":
                    runBookingsCRUD();
                    break;
                case "4":
                    runSearch();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void runSearch() {
        System.out.println("Search: ");
        String text = scanner.nextLine();
        String found = text + " found here:\n" + movieSearch(text) + "\n" + cardSearch(text) + "\n" + bookingSearch(text);

        System.out.println(found);
    }

    private String bookingSearch(String text) {
        String bookingsTextFound = "";
        for (Booking b : bookingService.fullTextSearch(text)) {
            bookingsTextFound += b + "\n";
        }
        return bookingsTextFound;
    }

    private String cardSearch(String text) {
        String cardsTextFound = "";
        for (Client c : cardService.fullTextSearch(text)) {
            cardsTextFound += c + "\n";
        }
        return cardsTextFound;
    }

    private String movieSearch(String text) {
        String moviesTextFound = "";
        for (Movie m : movieService.fullTextSearch(text)) {
            moviesTextFound += m + "\n";
        }
        return moviesTextFound;
    }

    private void showMenu() {
        System.out.println("1. Movie CRUD");
        System.out.println("2. Client CRUD");
        System.out.println("3. Booking CRUD");
        System.out.println("4. Full Text Search");
        System.out.println("x. Exit");
    }


    private void runMovieCRUD() {
        while (true) {
            showMovieMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    addMovie();
                    break;
                case "2":
                    updateMovie();
                    break;
                case "3":
                    removeMovie();
                    break;
                case "4":
                    showAllMovies();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void showMovieMenu() {
        System.out.println("1. Add Movie");
        System.out.println("2. Update Movie");
        System.out.println("3. Remove Movie");
        System.out.println("4. Show All Movies");
        System.out.println("x. Back");
    }

    private void addMovie() {
        try {
            Movie movie = ScannerForMovieAndCardsInformationPlusDateConverter.movieScanner();
            this.movieService.insert(movie.getId(), movie.getName(), movie.getYear(), movie.getPrice(), movie.isOnScreens());
            System.out.println("Movie successfully added\n");
        } catch (ConsoleException cex) {
            System.out.println("We have an adding error " + cex.getMessage());
        }
    }

    private void updateMovie() {
        try {
            Movie movie = ScannerForMovieAndCardsInformationPlusDateConverter.movieScanner();
            this.movieService.update(movie.getId(), movie.getName(), movie.getYear(), movie.getPrice(), movie.isOnScreens());
            System.out.println("\nMovie successfully updated\n");
        } catch (ConsoleException cex) {
            System.out.println("We have an updating error " + cex.getMessage());
        }
    }

    private void removeMovie() {
        try {
            System.out.println("Enter the id of the movie you want to remove:");
            String id = scanner.nextLine();
            movieService.remove(id);
            System.out.println("Movie successfully removed\n");
        } catch (ConsoleException cex) {
            System.out.println("We have a removing error " + cex.getMessage());
        }
    }

    private void showAllMovies() {
        for (Movie movie : movieService.getAll()) {
            System.out.println(movie);
        }
    }

    private void runCardsCRUD() {
        while (true) {
            showCustomerCardsMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    addCard();
                    break;
                case "2":
                    updateCard();
                    break;
                case "3":
                    removeCard();
                    break;
                case "4":
                    showAllCards();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void showCustomerCardsMenu() {
        System.out.println("1. Add Customer Card");
        System.out.println("2. Update Customer Card");
        System.out.println("3. Remove Customer Card");
        System.out.println("4. Show All Cards");
        System.out.println("x. Back");
    }

    private void addCard() {
        try {
            Client card = ScannerForMovieAndCardsInformationPlusDateConverter.cardScanner();
            cardService.insert(card.getId(), card.getName(), card.getSurname(), card.getCNP(), card.getDateOfBirth(), card.getRegistrationDate(), card.getBonusPoints());
            System.out.println("Card successfully added!\n");
        } catch (ConsoleException cex) {
            System.out.println("We have an card adding error " + cex.getMessage());
        }
    }

    private void updateCard() {
        try {
            Client card = ScannerForMovieAndCardsInformationPlusDateConverter.cardScanner();
            cardService.update(card.getId(), card.getName(), card.getSurname(), card.getCNP(), card.getDateOfBirth(), card.getRegistrationDate(), card.getBonusPoints());
            System.out.println("Card successfully updated!\n");
        } catch (ConsoleException cex) {
            System.out.println("We have an card updating error " + cex.getMessage());
        }
    }

    private void removeCard() {
        try {
            System.out.println("Enter the id of the card you want to remove: ");
            String id = scanner.nextLine();
            cardService.remove(id);
            System.out.println("Card successfully removed");
        } catch (ConsoleException cex) {
            System.out.println("We have an removing error " + cex.getMessage());
        }
    }

    private void showAllCards() {
        for (Client card : cardService.getAll()) {
            System.out.println(card);
        }
    }

    private void runBookingsCRUD() {
        while (true) {
            showBookingsMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    book();
                    break;
                case "2":
                    updateBooking();
                    break;
                case "3":
                    removeBooking();
                    break;
                case "4":
                    showAllBookings();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void book() {
        try {
            Booking booking = ScannerForMovieAndCardsInformationPlusDateConverter.bookingScanner();
            bookingService.insert(booking.getId(), booking.getIdMovie(), booking.getIdClient(), booking.getDate(), booking.getTime());
            if (cardService.getCardRepository().getById(booking.getIdClient()) != null) {
                System.out.println("Bonus points :" + cardService.getCardRepository().getById(booking.getIdClient()).getBonusPoints());
            }
            System.out.println("Booking created!\n");
        } catch (ConsoleException cex) {
            System.out.println("Adding booking failed " + cex.getMessage());
        }
    }

    private void updateBooking() {
        try {
            Booking booking = ScannerForMovieAndCardsInformationPlusDateConverter.bookingScanner();

            bookingService.update(booking.getId(), booking.getIdMovie(), booking.getIdClient(), booking.getDate(), booking.getTime());
            System.out.println("Booking updated!\n");
        } catch (ConsoleException cex) {
            System.out.println("Updating booking failed " + cex.getMessage());
        }
    }

    private void removeBooking() {
        try {
            System.out.println("Enter the booking id you want to remove: ");
            String id = scanner.nextLine();
            bookingService.remove(id);
            System.out.println("Booking removed");
        } catch (ConsoleException cex) {
            System.out.println("Remove error " + cex.getMessage());
        }
    }

    private void showAllBookings() {
        for (Booking booking : bookingService.getAll()) {
            System.out.println(booking);
        }
    }

    private void showBookingsMenu() {
        System.out.println("1. Add Booking");
        System.out.println("2. Update Booking");
        System.out.println("3. Remove Booking");
        System.out.println("4. Show all Bookings");
        System.out.println("x. Back");
    }
}
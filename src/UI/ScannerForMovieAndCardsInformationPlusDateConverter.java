package UI;
import Domain.Booking;
import Domain.Client;
import Domain.Movie;
import UI.ScanningAndConvertingInformationsException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

class ScannerForMovieAndCardsInformationPlusDateConverter {

    static Movie movieScanner() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the movie id: ");
            String id = scanner.nextLine();
            System.out.print("Enter the name of the movie: ");
            String name = scanner.nextLine();
            System.out.print("Enter the year of the movie: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter the price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("The movie is on the screen? (true/false) ");
            boolean onScreen = Boolean.parseBoolean(scanner.nextLine());
            return new Movie(id, name, year, price, onScreen);
        } catch (ScanningAndConvertingInformationsException rex) {
            throw new ScanningAndConvertingInformationsException("\n Movie scanning error: " + rex.getMessage());
        }
    }

    static Client cardScanner() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the id: ");
            String id = scanner.nextLine();
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter surname: ");
            String surname = scanner.nextLine();
            System.out.print("Enter CNP: ");
            String cnp = scanner.nextLine();
            System.out.print("Enter the date of birth: ");
            LocalDate dateOfBirth = dateScanner();
            System.out.print("Enter the registration date: ");
            LocalDate registrationDate = dateScanner();
            System.out.print("Enter the number of bonus points: ");
            int bonusPoints = Integer.parseInt(scanner.nextLine());
            return new Client(id, name, surname, cnp, dateOfBirth, registrationDate, bonusPoints);
        } catch (ScanningAndConvertingInformationsException rex) {
            throw new ScanningAndConvertingInformationsException("\nCard scanning error: " + rex.getMessage());
        }
    }

    static Booking bookingScanner() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the booking id: ");
            String id = scanner.nextLine();
            System.out.print("Enter movie id: ");
            String movieId = scanner.nextLine();
            System.out.print("Enter card id: ");
            String cardId = scanner.nextLine();
            System.out.print("Enter the date:");
            LocalDate date = dateScanner();
            System.out.print("Enter time: \n");
            System.out.print("Hour: ");
            int hour = Integer.parseInt(scanner.nextLine());
            System.out.print("Minutes: ");
            int minutes = Integer.parseInt(scanner.nextLine());
            LocalTime time = LocalTime.of(hour, minutes);

            return new Booking(id, movieId, cardId, date, time);
        } catch (ScanningAndConvertingInformationsException rex) {
            throw new ScanningAndConvertingInformationsException("\nBooking scanning error: " + rex.getMessage());

        }
    }

    private static LocalDate dateScanner() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nYear: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Month: ");
            int month = Integer.parseInt(scanner.nextLine());
            System.out.print("Day: ");
            int day = Integer.parseInt(scanner.nextLine());

            return LocalDate.of(year, month, day);
        } catch (RuntimeException rex) {
            System.out.println("\nDate scanning error: " + rex.getMessage());
        }
        return null;
    }

    /**
     * converts a date in dd.mm.yyyy string format in LocalDate format
     *
     * @param stringDate the date in string format
     * @return a date in LocalDate format
     */
    static LocalDate stringToDateConverter(String stringDate) {
        try {
            return LocalDate.of(Integer.parseInt(stringDate.substring(6, 10)), Integer.parseInt(stringDate.substring(3, 5)),
                    Integer.parseInt(stringDate.substring(0, 2)));
        } catch (RuntimeException rex) {
            if(stringDate.length() != 10){
                throw new RuntimeException("Date format is dd.mm.yyyy "+rex.getMessage());
            }
            throw new RuntimeException("Converting String date to LocalDate failed: " + rex.getMessage());
        }
    }
}


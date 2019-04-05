package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking extends Entity{
    private String idMovie, idClient;
    private LocalDate date;
    private LocalTime time;

    public Booking(String id, String idMovie, String idClient, LocalDate date, LocalTime time) {
        super( id );
        this.idMovie = idMovie;
        this.idClient = idClient;
        this.date = date;
        this.time = time;
    }



    @Override
    public String toString() {
        return "Booking{" +
                "id='" + getId() + '\'' +
                "idMovie='" + idMovie + '\'' +
                ", idClient='" + idClient + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}

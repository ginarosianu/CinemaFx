package Domain;

import java.util.Calendar;

public class MovieValidator implements IValidator < Movie > {
    /**
     * validates a movie
     *
     * @param movie to validate
     * @throws MovieValidator Exception is there validation error
     */

    public void validate(Movie movie) {
        String errors = "";
        if (movie.getPrice() <= 0) {
            errors += "The price must be a positiv number, greater than zero.";
        }

        if (movie.getYear() < 0 || movie.getYear() > Calendar.getInstance().get( Calendar.YEAR ) + 1) {
            errors += "The year must be valid!";
        }
        if (!errors.isEmpty()) {
            throw new MovieValidatorException( errors );
        }
    }
}


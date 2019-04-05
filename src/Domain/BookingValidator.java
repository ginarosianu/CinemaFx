package Domain;

import java.util.Calendar;

public class BookingValidator implements IValidator<Booking> {
    /**
     * validats a booking
     * @param booking
     */
    public void validate(Booking booking) {
        String errors = "";
        if (booking.getDate().getYear() > Calendar.getInstance().get( Calendar.YEAR )){
            errors += "the year of booking must be less than " + Calendar.getInstance().get( Calendar.YEAR );
        }
        if (!errors.isEmpty()){
            throw new BookingValidatorException (errors);
        }

    }
}

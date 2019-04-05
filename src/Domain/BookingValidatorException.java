package Domain;

import com.sun.xml.internal.bind.v2.model.runtime.RuntimeTypeInfo;

public class BookingValidatorException extends RuntimeException {
    public BookingValidatorException(String message) {
        super ("BookingValidatorException |||" + message);
    }
}

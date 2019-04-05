package Domain;

import java.util.Calendar;
public class ClientValidator implements IValidator<Client> {
    public void validate (Client client){
        String errors = "";
        if (client.getCNP().length() != 13){
            errors += "tne CNP must contain 13 characters. \n";
        }
        if (client.getDateOfBirth().getYear()< 1900 || client.getDateOfBirth().getYear()> Calendar.getInstance().get(Calendar.YEAR)){
            errors += "The year of registration must be less than " + Calendar.getInstance().get(Calendar.YEAR) + " and greater than 1900.";
        }
        if (client.getBonusPoints()<0){
            errors += "The bonus points must be a positive number.";
        }
        if (!errors.isEmpty()){
            throw new ClientValidatorException (errors);
        }
    }
}

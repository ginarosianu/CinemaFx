package Service;

import Domain.Client;
import Repository.IRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private IRepository < Client > clientRepository;

    public ClientService(IRepository < Client > clientRepository) {

        this.clientRepository = clientRepository;
    }

    /**
     * inserts a client card;
     *
     * @param id
     * @param name
     * @param surname
     * @param CNP
     * @param dateOfBirth
     * @param registrationDate
     * @param bonusPoints
     */
    public void insert(String id, String name, String surname, String CNP, LocalDate dateOfBirth,
                       LocalDate registrationDate, int bonusPoints) {
        Client card = new Client( id, name, surname, CNP, dateOfBirth, registrationDate, bonusPoints );
        List < Client > all = new ArrayList <>( clientRepository.getAll() );
        for (Client cardToTestCNP : all) {
            if (CNP.equals( cardToTestCNP.getCNP() )) {
                throw new ClientServiceException( String.format( "The %s CNP already exists", CNP ) );
            }
        }
        clientRepository.insert( card );
    }

    /**
     * updates a client card;
     *
     * @param id
     * @param name
     * @param surname
     * @param CNP
     * @param dateOfBirth
     * @param registrationDate
     * @param bonusPoints
     */
    public void update(String id, String name, String surname, String CNP, LocalDate dateOfBirth,
                       LocalDate registrationDate, int bonusPoints) {
        Client card = new Client( id, name, surname, CNP, dateOfBirth, registrationDate, bonusPoints );
        List < Client > all = new ArrayList <>( clientRepository.getAll() );
        for (Client cardToTestCNP : all) {
            if (CNP.equals( cardToTestCNP.getCNP() ) &&
                    !CNP.equals( card.getCNP() )) {
                throw new ClientServiceException( String.format( "The %s CNP already exists.", CNP ) );
            }
        }
        clientRepository.update( card );
    }

    public void remove(String id) {
        clientRepository.remove( id );
    }

    public List < Client > getAll() {
        return clientRepository.getAll();
    }

    public IRepository < Client > getCardRepository() {
        return clientRepository;
    }

    public List < Client > fullTextSearch(String text) {
        List < Client > found = new ArrayList <>();
        for (Client c : clientRepository.getAll()) {
            if (( c.getId().contains( text ) ) ||
                    c.getSurname().contains( text ) ||
                    c.getCNP().contains( text ) ||
                    c.getDateOfBirth().toString().contains( text ) ||
                    c.getRegistrationDate().toString().contains( text ) ||
                    Integer.toString( c.getBonusPoints() ).contains( text ) &&
                            !found.contains( c )) {
                found.add( c );
            }
        }
        return found;
    }

    public void luckyBonusPoints(LocalDate begin, LocalDate end, int bonus) {
        for (Client c : clientRepository.getAll()) {
//            if (c.getDateOfBirth().isAfter(birthday1) && c.getDateOfBirth().isBefore(birthday2)) {
            if (c.getDateOfBirth().getDayOfYear()>begin.getDayOfYear() && c.getDateOfBirth().getDayOfYear()<end.getDayOfYear()) {
                c.setBonusPoints(c.getBonusPoints() + bonus);
            }
        }
    }
}


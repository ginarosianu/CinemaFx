package Tests;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import Domain.Client;
import Domain.ClientValidator;
import Domain.IValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerCardTest {
    private IValidator<Client> validatorCard = new ClientValidator();
    private IRepository<Client> clientRepository = new InMemoryRepository<>(validatorCard);
    private ClientService customerCardService = new ClientService( clientRepository);

    @Test
    void setNameSurnameCNPDateOfBirthRegistrationDateBonusPoints() {
        customerCardService.insert("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
                LocalDate.of(2013,12,11), 15);

        customerCardService.getAll().get(0).setName("Name");
        assertEquals("Name",customerCardService.getAll().get(0).getName());


        customerCardService.getAll().get(0).setSurname("Surname");
        assertEquals("Surname",customerCardService.getAll().get(0).getSurname());

        customerCardService.getAll().get(0).setCNP("0000000000000");
        assertEquals("0000000000000",customerCardService.getAll().get(0).getCNP());

        customerCardService.getAll().get(0).setDateOfBirth(LocalDate.of(1990,4,10));
        assertEquals(LocalDate.of(1990,4,10),customerCardService.getAll().get(0).getDateOfBirth());

        customerCardService.getAll().get(0).setRegistrationDate(LocalDate.of(2019,2,21));
        assertEquals(LocalDate.of(2019,2,21),customerCardService.getAll().get(0).getRegistrationDate());

        customerCardService.getAll().get(0).setBonusPoints(100);
        assertEquals(100,customerCardService.getAll().get(0).getBonusPoints());
    }
}
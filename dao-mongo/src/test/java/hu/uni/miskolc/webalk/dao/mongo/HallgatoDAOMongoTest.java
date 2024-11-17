package hu.uni.miskolc.webalk.dao.mongo;

import hu.uni.miskolc.webalk.dao.exceptions.HallgatoMarLetezikException;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoNemTalalhatoException;
import hu.uni.miskolc.webalk.model.Hallgato;
import hu.uni.miskolc.webalk.model.Nem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class HallgatoDAOMongoTest {


    @Test()
    void test() throws HallgatoMarLetezikException, HallgatoNemTalalhatoException {
        HallgatoDAOMongo dao = new HallgatoDAOMongo("mongodb+srv://test:test@szoftverteszteles2021.bqwgi.mongodb.net/test?retryWrites=true&w=majority", "classModelTest", "hallgatok");
        System.out.println(dao.getAllHallgato());
        Assertions.assertThrows(HallgatoMarLetezikException.class, () -> {
                    dao.createHallgato(new Hallgato("ABC123", "Kiss Béla", "kiss.bela@pelda.hu", LocalDate.of(2000, Month.AUGUST, 12), Nem.FERFI));
                }
        );
        System.out.println(dao.getAllHallgato());
        System.out.println(dao.getHallgatoById("ABC123"));
    }
}
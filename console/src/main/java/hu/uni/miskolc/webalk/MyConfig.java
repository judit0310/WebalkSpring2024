package hu.uni.miskolc.webalk;

import hu.uni.miskolc.webalk.dao.HallgatoDAO;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoMarLetezikException;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoNemTalalhatoException;
import hu.uni.miskolc.webalk.model.Hallgato;
import hu.uni.miskolc.webalk.model.Nem;
import hu.uni.miskolc.webalk.service.HallgatoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig {

    @Bean(name = "hallgatoService")
    public HallgatoService hallgatoServiceInit() {

        return new HallgatoService(new HallgatoDAO() {

            @Override
            public List<Hallgato> getAllHallgato() {
                List<Hallgato> hallgatok = new ArrayList<>();
                Hallgato h1 = new Hallgato("ABC123", "Kiss Milán", "kiss.milan@pelda.hu",
                        LocalDate.of(2000, Month.AUGUST, 12), Nem.FERFI);
                hallgatok.add(h1);
                return hallgatok;
            }

            @Override
            public void createHallgato(Hallgato hallgato) throws HallgatoMarLetezikException {

            }

            @Override
            public Hallgato getHallgatoById(String id) throws HallgatoNemTalalhatoException {
                return null;
            }

            @Override
            public void updateHallgato(Hallgato hallgato) {

            }

            @Override
            public void deleteHallgato(Hallgato hallgato) throws HallgatoNemTalalhatoException {

            }
        });
    }
}

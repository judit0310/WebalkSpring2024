package hu.uni.miskolc.webalk;

import hu.uni.miskolc.webalk.model.Hallgato;
import hu.uni.miskolc.webalk.model.Nem;
import hu.uni.miskolc.webalk.service.HallgatoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

/**
 * Hello world!
 */
public class ConfigFromFile {
    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//
//        HallgatoService service = (HallgatoService) context.getBean("hallgatoService");
//        System.out.println(service.getHallgatok());
//
//        Hallgato h = new Hallgato("AAA111","Nagy Máté","nagy.mate@pelda.hu", LocalDate.now(), Nem.FERFI);
//        service.addHallgato(h);
//        System.out.println(service.getHallgatok());
    }
}

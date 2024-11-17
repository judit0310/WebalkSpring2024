package hu.uni.miskolc.webalk;

import hu.uni.miskolc.webalk.dao.exceptions.HallgatoMarLetezikException;
import hu.uni.miskolc.webalk.model.Hallgato;
import hu.uni.miskolc.webalk.model.Nem;
import hu.uni.miskolc.webalk.service.HallgatoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class ConfigFromAnnotation {
    public static void main(String[] args) throws HallgatoMarLetezikException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
//
//        HallgatoService service = (HallgatoService) context.getBean("hallgatoService");
//        System.out.println(service.getHallgatokDAO());
//
//        Hallgato h = new Hallgato("AAA111","Nagy Máté","nagy.mate@pelda.hu", LocalDate.now(), Nem.FERFI);
//        service.addHallgatoDAO(h);
//        System.out.println(service.getHallgatokDAO());
    }
}

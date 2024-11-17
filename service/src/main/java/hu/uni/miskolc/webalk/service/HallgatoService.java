package hu.uni.miskolc.webalk.service;

import hu.uni.miskolc.webalk.dao.HallgatoDAO;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoMarLetezikException;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoNemTalalhatoException;
import hu.uni.miskolc.webalk.model.Hallgato;
import hu.uni.miskolc.webalk.model.Nem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class HallgatoService {

    HallgatoDAO dao;


    public HallgatoService(@Autowired HallgatoDAO dao){
        this.dao = dao;
    }
/*
    public HallgatoService() {
    }

   static List<Hallgato> hallgatok;

    static {
        hallgatok = new ArrayList<>();
        Hallgato h1 = new Hallgato("ABC123","Kiss Béla","kiss.bela@pelda.hu",
                LocalDate.of(2000, Month.AUGUST, 12), Nem.FERFI);
        hallgatok.add(h1);
    }

    public List<Hallgato> getHallgatok() {
        return hallgatok;
    }

    public void addHallgato(Hallgato hallgato){
        hallgatok.add(hallgato);
    }


    public List<Hallgato> getHallgatokDAO(){
        return dao.getAllHallgato();
    }

    public void addHallgatoDAO(Hallgato hallgato) throws HallgatoMarLetezikException {
        dao.createHallgato(hallgato);
    }
*/

    public List<Hallgato> getHallgatok(){
        return dao.getAllHallgato();
    }

    public void addHallgato(Hallgato hallgato) throws HallgatoMarLetezikException {
        dao.createHallgato(hallgato);
    }


    public List<Hallgato> getHallgatokByNem(Nem nem){
       /* List<Hallgato> eredmeny = new ArrayList<>();
        List<Hallgato> hallgatok = dao.getAllHallgato();
        for (Hallgato h : hallgatok){
            if (h.getNem().equals(nem)) {
                eredmeny.add(h);
            }
        }
        return eredmeny;*/
        List<Hallgato> eredmeny = dao.getAllHallgato().stream().filter(h -> h.getNem().equals(nem)).collect(Collectors.toList());
        return eredmeny;
    }


    public Hallgato getHallgatoByNeptunKod(String neptunKod) throws HallgatoNemTalalhatoException {
        return dao.getHallgatoById(neptunKod);
    }
}

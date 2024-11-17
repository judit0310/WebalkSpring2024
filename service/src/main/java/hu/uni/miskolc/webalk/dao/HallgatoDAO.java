package hu.uni.miskolc.webalk.dao;

import hu.uni.miskolc.webalk.dao.exceptions.HallgatoMarLetezikException;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoNemTalalhatoException;
import hu.uni.miskolc.webalk.model.Hallgato;

import java.util.ArrayList;
import java.util.List;

public interface HallgatoDAO {

    default List<Hallgato> getAllHallgato(){ return new ArrayList<>();}


    void createHallgato(Hallgato hallgato) throws HallgatoMarLetezikException;

   // Id is no longer stored in _id
    // Hallgato getHallgatoById(int id) throws HallgatoNemTalalhatoException;

    void updateHallgato(Hallgato hallgato);

    void deleteHallgato(Hallgato hallgato) throws HallgatoNemTalalhatoException;


    Hallgato getHallgatoById(String id) throws HallgatoNemTalalhatoException;
}

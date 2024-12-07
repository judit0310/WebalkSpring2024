package hu.uni.miskolc.webalk.dao.exceptions;

public class HallgatoNemTalalhatoException extends Exception {

    public HallgatoNemTalalhatoException(String id){
        super("Az adott azonosítojú hallgato nem találhato: "+id);
    }
}

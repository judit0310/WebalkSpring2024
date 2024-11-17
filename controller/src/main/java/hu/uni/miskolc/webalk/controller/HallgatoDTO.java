package hu.uni.miskolc.webalk.controller;

import hu.uni.miskolc.webalk.model.Hallgato;
import hu.uni.miskolc.webalk.model.Nem;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

public class HallgatoDTO {
    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9]{6}", message = "A neptun kódnak 6 hosszúságúnak kell lennie")
    private String neptunKod;

    @Size(min = 7, max = 50)
    private String teljesNev;

    @Email
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @Past
    private LocalDate szuletesiDatum;

    private Nem nem;

    public static Hallgato convertHallgatoDTOToHallgato(HallgatoDTO dto){
        Hallgato h = new Hallgato(dto.neptunKod,dto.teljesNev,dto.email, dto.szuletesiDatum, dto.nem);
        return h;
    }

    public static HallgatoDTO convertHallgatotoHallgatoDTO(Hallgato h){
        HallgatoDTO dto = new HallgatoDTO(h.getNeptunKod(), h.getTeljesNev(), h.getEmail(), h.getSzuletesiDatum(), h.getNem());
        return dto;
    }


    public HallgatoDTO(String neptunKod, String teljesNev, String email, LocalDate szuletesiDatum, Nem nem) {
        this.neptunKod = neptunKod;
        this.teljesNev = teljesNev;
        this.email = email;
        this.szuletesiDatum = szuletesiDatum;
        this.nem = nem;
    }

    public HallgatoDTO() {
    }

    @Override
    public String toString() {
        return "HallgatoDTO{" +
                "neptunKod='" + neptunKod + '\'' +
                ", teljesNev='" + teljesNev + '\'' +
                ", email='" + email + '\'' +
                ", szuletesiDatum=" + szuletesiDatum +
                ", nem=" + nem +
                '}';
    }

    public String getNeptunKod() {
        return neptunKod;
    }

    public void setNeptunKod(String neptunKod) {
        this.neptunKod = neptunKod;
    }

    public String getTeljesNev() {
        return teljesNev;
    }

    public void setTeljesNev(String teljesNev) {
        this.teljesNev = teljesNev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getSzuletesiDatum() {
        return szuletesiDatum;
    }

    public void setSzuletesiDatum(LocalDate szuletesiDatum) {
        this.szuletesiDatum = szuletesiDatum;
    }

    public Nem getNem() {
        return nem;
    }

    public void setNem(Nem nem) {
        this.nem = nem;
    }
}

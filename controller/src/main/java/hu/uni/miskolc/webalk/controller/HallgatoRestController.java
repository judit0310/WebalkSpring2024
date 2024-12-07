package hu.uni.miskolc.webalk.controller;

import hu.uni.miskolc.webalk.dao.exceptions.HallgatoMarLetezikException;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoNemTalalhatoException;
import hu.uni.miskolc.webalk.model.Hallgato;
import hu.uni.miskolc.webalk.model.Nem;
import hu.uni.miskolc.webalk.service.HallgatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class HallgatoRestController {

    private final HallgatoService hallgatoService;

    public HallgatoRestController(@Autowired HallgatoService hallgatoService) {
        this.hallgatoService = hallgatoService;
    }

    @GetMapping(value = "hallgatok")
    public List<HallgatoDTO> hallgatok() {
// return hallgatoService.getHallgatok().stream().map(hallgato -> HallgatoDTO.convertHallgatotoHallgatoDTO(hallgato)).toList();
        return hallgatoService.getHallgatok().stream().map(HallgatoDTO::convertHallgatotoHallgatoDTO).collect(Collectors.toList());
    }

    @GetMapping("hallgato/{id:[A-Z0-9]{6}}")
    public Hallgato getHallgatoById(@PathVariable(name = "id") String neptunKod) throws HallgatoNemTalalhatoException {
        return hallgatoService.getHallgatoByNeptunKod(neptunKod);
    }


    @GetMapping("hallgatokNem/{nem}")
    public List<HallgatoDTO> getHallgatoNemSzerint(@PathVariable(name = "nem") Nem nem){
        return hallgatoService.getHallgatokByNem(nem).stream().map(HallgatoDTO::convertHallgatotoHallgatoDTO).collect(Collectors.toList());
    }

    @GetMapping("hallgatok/evek")
    public List<HallgatoDTO> getHallgatoEvekKozott(@RequestParam(name = "fromYear", required = true) int fromYear, @RequestParam(name = "toYear", defaultValue = "3000") int toYear){
        if (fromYear > toYear){
            throw new InvalidParameterException("A kezdő év nem lehet a végév után");
        }
        return hallgatoService.getHallgatokEvekKozott(fromYear, toYear).stream().map(HallgatoDTO::convertHallgatotoHallgatoDTO).collect(Collectors.toList());
    }

    @GetMapping("hallgatok/{fromYear}-{toYear}")
    public List<HallgatoDTO> getHallgatoEvekKozottPath(@PathVariable(name = "fromYear") int fromYear, @PathVariable(name = "toYear") int toYear){
        if (fromYear > toYear){
            throw new InvalidParameterException("A kezdő év nem lehet a végév után");
        }
        return hallgatoService.getHallgatokEvekKozott(fromYear, toYear).stream().map(HallgatoDTO::convertHallgatotoHallgatoDTO).collect(Collectors.toList());

    }

    @PostMapping(value = "addHallgato", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json; charset=UTF-8")
    public String addHallgato(@Valid @RequestBody HallgatoDTO dto, BindingResult bindingResult) throws HallgatoMarLetezikException {
        if (bindingResult.hasErrors()){
            return bindingResult.getFieldErrors().stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).collect(Collectors.joining("\n"));
        }
        hallgatoService.addHallgato(HallgatoDTO.convertHallgatoDTOToHallgato(dto));
        return dto.toString();
    }

    @GetMapping("hallgatok/{vezeteknev}")
    public List<HallgatoDTO> getHallgatokByVezeteknev(@PathVariable(name = "vezeteknev") String vezeteknev){
        return hallgatoService.getHallgatokByVezeteknev(vezeteknev).stream().map(HallgatoDTO::convertHallgatotoHallgatoDTO).collect(Collectors.toList());

    }
//    @ExceptionHandler(HallgatoMarLetezikException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public String hallgatoMarLetezik(HallgatoMarLetezikException e){
//        return "Ezzel az azonosítóval már létezik hallgató: "+e.getMessage();
//    }




}

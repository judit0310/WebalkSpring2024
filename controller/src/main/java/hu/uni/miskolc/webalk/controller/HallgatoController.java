package hu.uni.miskolc.webalk.controller;

import hu.uni.miskolc.webalk.dao.exceptions.HallgatoMarLetezikException;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoNemTalalhatoException;
import hu.uni.miskolc.webalk.model.Hallgato;
import hu.uni.miskolc.webalk.service.HallgatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.management.ObjectName;


@Controller
public class HallgatoController {

    public HallgatoService service;

    @ModelAttribute("hallgato")
    public HallgatoDTO hallgato(){
        HallgatoDTO h = new HallgatoDTO();
        return h;
    }

    public HallgatoController(@Autowired HallgatoService hallgatoService){
        this.service = hallgatoService;
    }

    @GetMapping("hello")
    public String hello(){
        System.out.println("Hello");
       // return "/WEB-INF/views/hello.jsp";
        return "hello";
    }

    @GetMapping("hallgatok")
    public ModelAndView hallgatok(){
        System.out.println(service.getHallgatok());
        ModelAndView mav = new ModelAndView("hallgatok");
        mav.addObject("hallgatok", service.getHallgatok());
        return mav;
    }

    @GetMapping("/hallgato/{id}")
    public ModelAndView hallgatoNeptunKodAlapjan(@PathVariable(name = "id") String neptunKod) throws HallgatoNemTalalhatoException {
        ModelAndView mav = new ModelAndView("hallgatoForm");
        mav.addObject("hallgato",service.getHallgatoByNeptunKod(neptunKod));
        mav.addObject("method","View");
        return mav;

    }

    @GetMapping("ujhallgato")
    public ModelAndView addHallgato(){
        ModelAndView mav = new ModelAndView("hallgatoForm");
        return mav;
    }

    @PostMapping("ujhallgato")
    public ModelAndView addHallgato(@ModelAttribute("hallgato") @Valid HallgatoDTO hallgato, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("hallgatoForm");
        }
        try{
            service.addHallgato(HallgatoDTO.convertHallgatoDTOToHallgato(hallgato));
            ModelAndView mav = new ModelAndView("redirect:hallgatok");
            return mav;
        } catch (HallgatoMarLetezikException e) {
            ModelAndView mav = new ModelAndView("hallgatoForm");
            mav.addObject("message", "A(z) "+hallgato.getNeptunKod()+" azonosító már foglalt");
            return mav;
        }
    }


    @ExceptionHandler(HallgatoNemTalalhatoException.class)
    public String handleException(HallgatoNemTalalhatoException ex, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", ex.getMessage());

        return "redirect:/hallgatok";
    }



}

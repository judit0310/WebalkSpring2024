package hu.uni.miskolc.webalk.controller;

import hu.uni.miskolc.webalk.dao.exceptions.HallgatoMarLetezikException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(HallgatoMarLetezikException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String hallgatoMarLetezik(HallgatoMarLetezikException e){
        return "Ezzel az azonosítóval már létezik hallgató: "+e.getMessage();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String methodNotAllowed(HttpRequestMethodNotSupportedException e){
        return "This method is not allowed for this request: "+e.getMethod()+", use one of these: "+ String.join(", ", e.getSupportedMethods());
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String unsupportedMediaType(HttpMediaTypeNotSupportedException e){
        return "Use one of the following media types: "+e.getSupportedMediaTypes().stream().map(MediaType::toString).collect(Collectors.joining(", "));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandlerFound(NoHandlerFoundException e){
        return e.getRequestURL().toString() +" not found";
    }


    @ExceptionHandler(InvalidParameterException.class)
    public String invalidParameter(InvalidParameterException e){
        return "Invalid parameter: "+e.getMessage();
    }
}

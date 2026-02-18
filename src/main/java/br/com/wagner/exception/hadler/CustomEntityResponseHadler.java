package br.com.wagner.exception.hadler;

import br.com.wagner.exception.ExceptionResponse;
import br.com.wagner.exception.ResourceNotFoundExcepetion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomEntityResponseHadler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity <ExceptionResponse> handleAllExceptions(Exception ex , WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
                new java.util.Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundExcepetion.class)
    public final ResponseEntity <ExceptionResponse> handleNotFoundExceptions(Exception ex , WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
                new java.util.Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
    }
}

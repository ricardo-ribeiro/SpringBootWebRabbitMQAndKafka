package springboot.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import springboot.exceptions.ProductNotFound;

import java.util.HashMap;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity handleException(ProductNotFound productNotFound){
        return new ResponseEntity( new HashMap<String,String>(){{put("error","Product Not Found");}}, HttpStatus.NOT_FOUND);
    }
}

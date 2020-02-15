package springboot.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.FileNotFoundException;

@RestControllerAdvice
public class RestAdvice {

    private final ObjectMapper objectMapper;

    public RestAdvice(@Autowired ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(FileNotFoundException.class)
    private ResponseEntity<ObjectNode> handleFileNotFound(FileNotFoundException fileNotFound){
        return new ResponseEntity<>(objectMapper.createObjectNode().put("error",fileNotFound.getMessage()),HttpStatus.NOT_FOUND);
    }
}

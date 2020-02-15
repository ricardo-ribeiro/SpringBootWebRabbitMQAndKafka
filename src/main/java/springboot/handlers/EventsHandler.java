package springboot.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springboot.services.EventsService;

import java.io.FileNotFoundException;

@RestController
public class EventsHandler {

    private final EventsService eventsService;

    public EventsHandler(@Autowired EventsService eventsService){
        this.eventsService = eventsService;
    }

    @GetMapping(path = "/events")
    public ResponseEntity<ArrayNode> getEvents() throws FileNotFoundException {
        return new ResponseEntity<>(eventsService.getEvents(),HttpStatus.OK);
    }

    @GetMapping(path = "/event/{id}")
    public ResponseEntity<ObjectNode> getEvent(@PathVariable("id") String id){

        return new ResponseEntity<>(
                new ObjectMapper()
                        .createObjectNode()
                        .put("id",id),
                HttpStatus.OK
        );
    }
}

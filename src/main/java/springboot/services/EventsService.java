package springboot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public final class EventsService {

    private final ObjectMapper objectMapper;

    public EventsService(@Autowired ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    private ArrayNode readFile() throws IOException {
        return objectMapper.readValue(this.getClass().getResourceAsStream("/events/events.json"),ArrayNode.class);
    }

    public ArrayNode getEvents() throws FileNotFoundException {
        try {
            return readFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Resource Not Found");
        }

    }




}

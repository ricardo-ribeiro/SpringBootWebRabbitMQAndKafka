package springboot.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import springboot.messaging.KafkaEventChannels;

@Controller
public class KafkaMessageHandler {


    @Autowired
    ObjectMapper mapper;

    @StreamListener(KafkaEventChannels.INPUT)
    public void handleMessage(Message<JsonNode> jsonNodeMessage){
        System.out.println("FROM KAFKA");
        System.out.println(jsonNodeMessage.getPayload().toPrettyString());

        Message<ObjectNode> message = MessageBuilder
                .withPayload(
                        mapper
                                .createObjectNode()
                                .put("payment","validated"))
                .build();
    }
}

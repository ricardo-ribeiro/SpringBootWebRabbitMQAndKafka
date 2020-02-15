package springboot.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import springboot.messaging.PaymentMessagingChannel;

import java.util.HashMap;
import java.util.UUID;

@Controller
public class PaymentsMessagesHandler {

    Logger logger = LoggerFactory.getLogger(PaymentsMessagesHandler.class);

    @Autowired
    private MessageChannel paymentValidatedOutput;

    @Autowired
    private MessageChannel globalEventsOutput;

    @Autowired
    ObjectMapper mapper;

    @StreamListener(PaymentMessagingChannel.INPUT)
    public void handleIncommingPayment(Message<JsonNode> jsonNodeMessage) throws JsonProcessingException {
        logger.info("Received From Rabbit : {}",jsonNodeMessage.getPayload().toPrettyString());

        Message<ObjectNode> paymentValidatedToRabbit = MessageBuilder.withPayload(mapper.createObjectNode().put("payment","validated")).build();
        paymentValidatedOutput.send(paymentValidatedToRabbit);
        logger.info("Sent TO Rabbit : {}",paymentValidatedToRabbit);


        MessageHeaders headers = new MessageHeaders(new HashMap<String,Object>(){{put(KafkaHeaders.MESSAGE_KEY,UUID.randomUUID().toString());}});
        Message<ObjectNode> message = MessageBuilder.createMessage(mapper.createObjectNode().put("payment","validated"), headers);
        message.getPayload().put("data",jsonNodeMessage.getPayload());
        globalEventsOutput.send(message);
        logger.info("Sent TO Kafka : {}",message);

    }
}

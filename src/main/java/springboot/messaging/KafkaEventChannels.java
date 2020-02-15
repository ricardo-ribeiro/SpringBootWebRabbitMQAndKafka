package springboot.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaEventChannels {

    String INPUT = "globalEventsInput";

    @Input
    SubscribableChannel globalEventsInput();

    @Output
    MessageChannel globalEventsOutput();

}

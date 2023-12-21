package com.example.springkafka.event;

import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<Object, Greeting> kafkaTemplate;
    public final String TOPIC_NAME = "yoonsla2";

    public void sendMessage1(Greeting message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendMessage2(Greeting message) {
        CompletableFuture<SendResult<Object, Greeting>> future = kafkaTemplate.send(TOPIC_NAME, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent Message = [{}], offset= [{}]", message, result.getRecordMetadata().offset());
            } else {
                log.info("Unable to send message = [{}], due to= [{}]", message, ex.getMessage());
            }
        });
    }

    // 시용자 정의
    public void sendMessage3(String message) {
        kafkaTemplate.send(TOPIC_NAME, new Greeting("Hello", "World"));
    }
}

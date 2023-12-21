package com.example.springkafka.event;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KafkaConsumer {

    public final String TOPIC_NAME = "yoonsla2";

    @KafkaListener(topics = TOPIC_NAME, groupId = "yoonsla")
    public void listenGroupFoo(String message) {
        log.info("Received Message in group foo: ==> {}", message);
    }

    @KafkaListener(topics = TOPIC_NAME)
    public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        log.info("Received Message: {}, from partition: {}", message, partition);
    }

    // 필터와 일치하는 모든 메시지 삭제
    @KafkaListener(topics = TOPIC_NAME, containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        log.info("Received Message in filtered listener: {}", message);
    }

    // 사용자 정의
    @KafkaListener(topics = TOPIC_NAME, containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
        // process greeting message
        log.info("Received Message in Custom listener: [{}]", greeting);
    }
}

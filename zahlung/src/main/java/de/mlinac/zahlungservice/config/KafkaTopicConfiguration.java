package de.mlinac.zahlungservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

//@Configuration
public class KafkaTopicConfiguration {

    /*
    @Bean
    public NewTopic  topicPaymentReceived(){
        return TopicBuilder.name("zahlung-erhalten")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic  topicPaymentFailed(){
        return TopicBuilder.name("zahlung-fehlgeschlagen")
                .partitions(3)
                .replicas(1)
                .build();
    }

     */
}

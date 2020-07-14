package de.mlinac.checkoutservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {


    /*
    @Bean
    public NewTopic  topicCheckout1(){
        return TopicBuilder.name("checkout1")
                .partitions(1)
                .replicas(1)
                .config("BOO")
                .build();
    }
    @Bean
    public NewTopic  topicCheckout(){
        return TopicBuilder.name("checkout2")
                .partitions(1)
                .replicas(1)
                .build();
    }

     */
}

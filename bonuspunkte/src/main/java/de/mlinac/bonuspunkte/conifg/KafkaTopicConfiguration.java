package de.mlinac.bonuspunkte.conifg;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

//@Configuration
public class KafkaTopicConfiguration {

    /*
    @Bean
    public NewTopic  topicCheckout(){
        return TopicBuilder.name("points-rewarded")
                .partitions(3)
                .replicas(1)
                .build();
    }

     */
}

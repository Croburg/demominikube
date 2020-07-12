package de.mlinac.orderservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

//@EnableKafka
//@Configuration
public class KafkaTopicConfiguration {

    /*
    @Bean
    public NewTopic  topicZahlungDurchfuehren(){
        return TopicBuilder.name("zahlung-durchfuehren")
                .partitions(3)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic  topicZahlungGutschreiben(){
        return TopicBuilder.name("zahlung-gutschreiben")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic  topicWareVerschicken(){
        return TopicBuilder.name("ware-verschicken")
                .partitions(3)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic  topicVersandStornieren(){
        return TopicBuilder.name("versand-stornieren")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic  topicBonuspunkteGutschreiben(){
        return TopicBuilder.name("bonuspunkte-gutschreiben")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic  topicBonuspunkteAbziehen(){
        return TopicBuilder.name("bonuspunkte-abziehen")
                .partitions(3)
                .replicas(1)
                .build();
    }

     */
}

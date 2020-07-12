package de.mlinac.bonuspunkte.conifg;


import de.mlinac.bonuspunkte.model.Bestellung;
import de.mlinac.bonuspunkte.model.PunkteKonto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {


    @ConditionalOnMissingBean(ConsumerFactory.class)
    public ConsumerFactory<String, Bestellung> bestellungConsumerFactory(){
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "bonuspunkte-service");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        ErrorHandlingDeserializer<Bestellung> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(Bestellung.class));


        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), errorHandlingDeserializer);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Bestellung> bestellungListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Bestellung> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(bestellungConsumerFactory());
        factory.setErrorHandler(new SeekToCurrentErrorHandler());
        return factory;
    }
}

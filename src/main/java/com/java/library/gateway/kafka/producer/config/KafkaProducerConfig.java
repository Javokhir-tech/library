package com.java.library.gateway.kafka.producer.config;

import com.java.library.gateway.kafka.config.property.KafkaAttributes;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {  // Producer configuration

    @Autowired
    private KafkaProperties kafkaProperties;

    // The Producer Configuration is a simple key-value map. We inject the default properties using @Autowired to obtain the KafkaProperties bean.
    // Then, we build our map passing the default values for the producer and overriding the default Kafka key and value serializers.

    // The producer will serialize keys as Strings using the Kafka library’s StringSerializer and will do the same for values but this time using JSON,
    // with a JsonSerializer, in this case provided by Spring Kafka.
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // The Kafka topic. When we inject a NewTopic bean, we’re instructing the Kafka’s AdminClient bean (already in the context) to create a topic with the given configuration.
    // The first parameter is the name (advice-topic, from the app configuration),
    // the second is the number of partitions (3) and the third one is the replication factor (1, since we’re using a single node anyway).
    @Bean
    public NewTopic adviceTopic() {
        return new NewTopic(KafkaAttributes.TOPIC, 3, (short) 1);
    }
}

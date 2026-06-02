package com.pastillazz.f1nt3ch.common.infrastructure.configuration.infrastructure;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic userTopic(){
        return TopicBuilder.name("user-topic")
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic transferTopic(){
        return TopicBuilder.name("transfer-topic")
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic depositTopic(){
        return TopicBuilder.name("deposit-topic")
                .partitions(6)
                .replicas(1)
                .build();
    }
}

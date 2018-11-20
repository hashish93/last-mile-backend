package com.appzoneltd.lastmile.microservice.employee;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import com.appzoneltd.lastmile.microservice.employee.config.OAuth2ResourceServerConfig;
import com.appzoneltd.lastmile.microservice.employee.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.employee.config.TokenServiceConfiguration;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@Import({OAuth2ResourceServerConfig.class, TokenServiceConfiguration.class})
@RibbonClient(name = "UMS_SERVICE", configuration = RibbonConfiguration.class)
@PropertySources({@PropertySource("classpath:application.yml"),
        @PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true)})
@EnableKafka
public class UserAPIApplication extends CoreApplicationConfig {

    @Value("${kafka.bootstrap.servers}")
    private String BOOTSTRAP_SERVERS;

    public static void main(String[] args) {
        SpringApplication.run(UserAPIApplication.class, args);
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate() {
        return new KafkaTemplate<Integer, String>(producerFactory());
    }

    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> kafkaPros = new HashMap<>();
        kafkaPros.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        kafkaPros.put(ProducerConfig.ACKS_CONFIG, "all");
        kafkaPros.put(ProducerConfig.RETRIES_CONFIG, 0);
        kafkaPros.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        kafkaPros.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return kafkaPros;
    }


}

package com.appzoneltd.lastmile.microservice.pushnotification;

import com.appzoneltd.lastmile.microservice.abstractconfig.CoreApplicationConfig;
import com.appzoneltd.lastmile.microservice.pushnotification.config.RibbonConfiguration;
import com.appzoneltd.lastmile.microservice.pushnotification.firebase.FCMSender;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.IPushNotificationsSender;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.impl.AndroidFirebaseNotificationSender;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.impl.IOSFirebaseNotificationSender;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
@PropertySources({@PropertySource("classpath:application.yml"),
        @PropertySource(value = "file:${configFile}", ignoreResourceNotFound = true)})
@RibbonClient(name = "PushNotificationApplication", configuration = RibbonConfiguration.class)
@EnableKafka
public class PushNotificationApplication extends CoreApplicationConfig {

    @Value("${kafka.bootstrap.servers}")
    private String BOOTSTRAP_SERVERS;

    @Value("${firebase.server-key}")
    private String FIREBASE_API_KEY;
    
    @Value("${notification.android-type}")
    private String ANDROID_TYPE;
    
    @Value("${notification.ios-type}")
    private String IOS_TYPE;
    
    @Value("${notification.default-type}")
    private String DEFAULT_TYPE;
    
    @Value("${notification.retreis-count}")
    private int RETRIES_COUNT;

    public static void main(String[] args) {
        SpringApplication.run(PushNotificationApplication.class, args);
    }

    @Bean
    public Map<String, IPushNotificationsSender> notificationSendersMap() {
    	
    	Map<String, IPushNotificationsSender> injectedSenders = new HashMap<String, IPushNotificationsSender>();
    	
    	//create android sender
    	IPushNotificationsSender androidSender = new AndroidFirebaseNotificationSender(FIREBASE_API_KEY, RETRIES_COUNT) ;
    	injectedSenders.put(ANDROID_TYPE, androidSender) ;
    	
    	//create iOS sender
    	IPushNotificationsSender iosSender = new IOSFirebaseNotificationSender(FIREBASE_API_KEY, RETRIES_COUNT);
    	injectedSenders.put(IOS_TYPE,iosSender) ;
    	
    	return injectedSenders ;
    }
//    @Bean
//    public FCMSender fcmConfigurer() {
//        return new FCMSender(FIREBASE_API_KEY);
//    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Long, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(25000L);
        return factory;
    }

    @Bean
    public ConsumerFactory<Long, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "push-notification-group");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    // KAFKA Producer Configurations

    @Bean
    public ProducerFactory<Long, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> kafkaPros = new HashMap<>();
        kafkaPros.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        kafkaPros.put(ProducerConfig.ACKS_CONFIG, "all");
        kafkaPros.put(ProducerConfig.RETRIES_CONFIG, 0);
        kafkaPros.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        kafkaPros.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return kafkaPros;
    }

    @Bean
    public KafkaTemplate<Long, String> kafkaTemplate() {
        return new KafkaTemplate<Long, String>(producerFactory());
    }

}

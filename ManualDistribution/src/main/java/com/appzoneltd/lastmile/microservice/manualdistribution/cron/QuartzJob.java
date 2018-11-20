package com.appzoneltd.lastmile.microservice.manualdistribution.cron;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Properties;

/**
 * Created by hashish on 4/5/2017.
 */

public class QuartzJob implements Job {

    public QuartzJob(){
        configureKafkaProducer();
    }

    private KafkaProducer<Integer, String> producer;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            System.out.println("attemp to execute the job");
            producer.send(new ProducerRecord<Integer, String>("AUTOMATIC_FIRE_ACTION", "OK"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureKafkaProducer() {
/// Properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "SS_PRODUCER");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }


}

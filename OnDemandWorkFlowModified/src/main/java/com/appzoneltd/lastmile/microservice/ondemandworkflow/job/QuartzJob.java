package com.appzoneltd.lastmile.microservice.ondemandworkflow.job;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.ActionNeededBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    			
    		final ObjectMapper mapper=new ObjectMapper();
            System.out.println(">>>>>>>>> change status to action needed");
            JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
            Long packageId = data.getLong("packageId");
            
            ActionNeededBase actionNeededBase=new ActionNeededBase();
            actionNeededBase.setPackageId(packageId);
            try {
				producer.send(new ProducerRecord<Integer, String>("PACKAGE_ACTION_NEEDED_TRIGGER", mapper.writeValueAsString(actionNeededBase)));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
    }

    private void configureKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "SS_PRODUCER");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

}

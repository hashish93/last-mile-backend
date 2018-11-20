//package com.appzoneltd.lastmile.consumer;
//
//import com.appzoneltd.lastmile.entity.SystemConfig;
//import com.appzoneltd.lastmile.job.SystemConfigJob;
//import com.appzoneltd.lastmile.microservice.manualdistribution.Utility.DateUtility;
//import com.appzoneltd.lastmile.repo.SystemConfigRepo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.quartz.SchedulerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.Date;
//
///**
// * Created by hashish on 4/5/2017.
// */
//@Component
//@Scope("singleton")
//public class SystemConfigConsumer {
//
//    @Autowired
//    private SystemConfigJob systemConfigJob;
//    @Autowired
//    SystemConfigRepo systemConfigRepo;
//
//    @KafkaListener(topics = {"AUTOMATIC_CRON"})
//    private void configConsumer(@Payload String payload) throws IOException, SchedulerException {
//        final ObjectMapper objectMapper = new ObjectMapper();
//        SystemConfig config = objectMapper.readValue(payload , SystemConfig.class);
//
//
//        System.out.println(">>>>>>>>>>>>>>>>>> config"+config.toString());
//        if(config.getConfigId().equals(14L) && config.getValue().equals(new BigDecimal(0))){
//            System.out.println(">>>>>>>>>>>>>>>>>> stop cron job");
//            systemConfigJob.stopJob();
//        }else {
//            if(config.getConfigId().equals(15L)) {
//                Date date = DateUtility.get24HourTimeFromString(config.getTextValue());
//                System.out.println(">>>>>>>>>>>>>>>>>> restart cron job with id 15 from kafka in  job with comming hours and minutes "+ date);
//                systemConfigJob.restartJob(date.getHours() , date.getMinutes());
//            }else {
//                String automatic_value = systemConfigRepo.findOne(15L).getTextValue();
//                Date date = DateUtility.get24HourTimeFromString(automatic_value);
//                System.out.println(">>>>>>>>>>>>>>>>>> restart cron job with id 14 from repositry with comming hours and minutes "+ date);
//                systemConfigJob.restartJob(date.getHours() , date.getMinutes());
//            }
//        }
//
//    }
//}

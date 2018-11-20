//package com.appzoneltd.lastmile.microservice.manualdistribution.consumer;
//
//
//import com.appzoneltd.lastmile.microservice.manualdistribution.service.automaticservice.AutomaticDistributionServiceImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
///**
// * Created by hashish on 4/6/2017.
// */
//@Component
//@Scope("singleton")
//public class schudulerExecuterConsumer {
//
//    @Autowired
//    AutomaticDistributionServiceImp automaticDistributionServiceImp;
//
//    @KafkaListener(topics = {"AUTOMATIC_FIRE_ACTION"})
//    private void automaticFireAction(@Payload String payload) throws Exception {
//        System.out.println("AUTOMATIC_FIRE_ACTION");
//        automaticDistributionServiceImp.savePlan();
//    }
//}

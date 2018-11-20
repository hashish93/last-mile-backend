package com.appzone.lastmile.aspect;


import com.appzone.lastmile.annotation.Auditable;
import com.appzone.lastmile.model.Action;
import com.appzone.lastmile.model.Audit;
import com.appzone.lastmile.model.Screen;
import com.appzone.lastmile.repository.AuditRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Appzone on 13-Feb-17.
 */
@Aspect
@Component
public class ServiceAspect {
    private static Logger LOGGER = Logger.getLogger(ServiceAspect.class);
    @Autowired
    AuditRepository auditRepository;

    @Pointcut("@annotation(com.appzone.lastmile.annotation.Auditable)")
    public void requestMapping() {
    }

    @Pointcut("execution(* *..controller.*.*(..)))")
    public void methodPointcut() {
    }

    @Around("requestMapping() && methodPointcut()")
    public Object profile(ProceedingJoinPoint pjp) throws JsonProcessingException {
        Object[] args = pjp.getArgs();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Object payload = new Object();

        assert args.length == parameterAnnotations.length;
        for (int argIndex = 0; argIndex < args.length; argIndex++) {
            payload = args[0];

        }
        Auditable myAnnotation = method.getAnnotation(Auditable.class);
        Action action = myAnnotation.action();
        Screen screen = myAnnotation.screenName();
        List<String> fieldNames = Arrays.asList(myAnnotation.fieldNames());
        Object retVal = null;

        try {
            retVal = pjp.proceed();
            ObjectMapper objectMapper = new ObjectMapper();
            String stringRetVal = objectMapper.writeValueAsString(retVal);
            System.out.println(">>>>>>>>>>>>> response" + stringRetVal);
            JSONObject jsonObject = new JSONObject(stringRetVal);
            String status;
            if (jsonObject.get("statusCodeValue").equals(200)) {
                status = "success";
            } else {
                status = "fail";
            }
            if (!fieldNames.get(0).equals("###")) {
                Map<String, Object> fields = readPayloadFields(payload, fieldNames);
                saveAuditInCassandra(fields, action, screen, stringRetVal, status);
            } else {
                saveAuditInCassandra(payload, action, screen, stringRetVal, status);
            }
        } catch (Exception e) {
            System.out.println("============================= cassandra exception ====================");
            e.printStackTrace();
        } finally {
            return retVal;
        }
    }

    private void checkAudit() {

    }

    private void saveAuditInCassandra(Object fields, Action action, Screen screen, String response, String status)
            throws Exception {
        Audit audit = new Audit();
        audit.setAction(action.toString());
        audit.setScreenName(screen.toString());
        audit.setTime(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        String stringFields = objectMapper.writeValueAsString(fields);
        System.out.println(stringFields);
        audit.setPayload(stringFields);
        audit.setResponse(response);
        audit.setStatus(status);
        audit.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(audit);
        System.out.println("user name " + SecurityContextHolder.getContext().getAuthentication().getName());

        auditRepository.save(audit);
    }

    private HashMap<String, Object> readPayloadFields(Object payload, List<String> fieldNames) {
        Field[] payloadFields = payload.getClass().getDeclaredFields();
        HashMap<String, Object> result = new HashMap<>();
        for (Field field : payloadFields) {
            if (isFieldRequired(field, fieldNames)) appendFieldToResult(result, payload, field);
        }
        return result;
    }


    private boolean isFieldRequired(Field field, List<String> fieldNames) {
        field.setAccessible(true);
        return fieldNames.contains(field.getName());
    }

    private void appendFieldToResult(HashMap<String, Object> result, Object payload, Field field) {
        try {
            result.put(field.getName(), field.get(payload));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

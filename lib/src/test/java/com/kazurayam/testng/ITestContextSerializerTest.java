package com.kazurayam.testng;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.Date;

public class ITestContextSerializerTest {

    private Logger logger = LoggerFactory.getLogger(ITestContextSerializerTest.class);

    @Test
    public void test_ITestContext_toString(ITestContext context) throws JsonProcessingException {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        logger.info("[" + methodName + "] " + context.toString());
    }

    @Test
    public void test_bare_exception(ITestContext context) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String serialized = mapper.writeValueAsString(context);
        } catch (JsonProcessingException e) {
            String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
            logger.error("[" + methodName + "] " + e.getMessage());
        }
    }

    @Test
    public void test_serialize(ITestContext context) throws JsonProcessingException {
        // setup
        context.setAttribute("foo", "bar");
        context.setAttribute("now", new Date());
        //
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ITestContextSerializer");
        module.addSerializer(context.getClass(), new ITestContextSerializer());
        module.addSerializer(Date.class, new DateSerializer());
        mapper.registerModule(module);

        String serialized = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(context);

        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        logger.info("[" + methodName + "] " + serialized);
    }
}

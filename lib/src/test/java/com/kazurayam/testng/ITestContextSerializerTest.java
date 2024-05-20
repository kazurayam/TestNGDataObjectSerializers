package com.kazurayam.testng;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ITestContextSerializerTest {

    private Logger logger = LoggerFactory.getLogger(ITestContextSerializerTest.class);

    private ObjectMapper mapper;

    @BeforeTest
    public void beforeTest(ITestContext context) {
        mapper = new ITestContextSerializingObjectMapperBuilder()
                .enablePrettyPrint(true)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z"))
                .build();
    }

    @Test
    public void test_ITestContext_toString(ITestContext context) {
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
    public void test_serialize(ITestContext context) throws IOException {
        // setup
        context.setAttribute("foo", "bar");
        context.setAttribute("now", new Date());
        //

        String serialized = mapper.writeValueAsString(context);
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        logger.info("[" + methodName + "] " + serialized);
    }

    @AfterTest
    public void afterTest(ITestContext context) throws IOException {
        String serialized = mapper.writeValueAsString(context);
        Path outputDirectory = Paths.get(context.getOutputDirectory());
        Files.createDirectories(outputDirectory);
        Path outputJson = outputDirectory.resolve("sample_ITestContext.json");
        Files.write(outputJson, serialized.getBytes(StandardCharsets.UTF_8));
    }
}

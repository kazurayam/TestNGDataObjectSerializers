package com.kazurayam.testng;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.text.DateFormat;
import java.util.Date;

public class ITestContextSerializingObjectMapperBuilder {

    private boolean enablePrettyPrint;
    private DateFormat dateFormat;
    public ITestContextSerializingObjectMapperBuilder() {
        enablePrettyPrint = false;
        dateFormat = DateSerializer.getDefaultDateFormat();
    }

    public ITestContextSerializingObjectMapperBuilder enablePrettyPrint(boolean enablePrettyPrint) {
        this.enablePrettyPrint = enablePrettyPrint;
        return this;
    }

    public ITestContextSerializingObjectMapperBuilder dateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    public ObjectMapper build() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT,
                this.enablePrettyPrint);

        SimpleModule module = new SimpleModule("ITestContextSerializer");
        module.addSerializer(Date.class, new DateSerializer().dateFormat(dateFormat));
        module.addSerializer(IResultMap.class, new IResultMapSerializer());
        module.addSerializer(ISuite.class, new ISuiteSerializer());
        module.addSerializer(ITestContext.class, new ITestContextSerializer());
        module.addSerializer(ITestNGMethod.class, new ITestNGMethodSerializer());
        module.addSerializer(ITestResult.class, new ITestResultSerializer());

        objectMapper.registerModule(module);
        return objectMapper;
    }
}

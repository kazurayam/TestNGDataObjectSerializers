package com.kazurayam.testng;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.testng.ITestResult;

import java.io.IOException;

public class ITestResultSerializer extends StdSerializer<ITestResult> {

    public ITestResultSerializer() { this(null); }

    public ITestResultSerializer(Class<ITestResult> t) { super(t); }

    @Override
    public void serialize(ITestResult value,
                           JsonGenerator jgen,
                           SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("name", value.getName());
        jgen.writeStringField("status", statusString(value.getStatus()));
        jgen.writeEndObject();
    }

    private String statusString(int value) {
        String result = switch (value) {
            case ITestResult.CREATED -> "CREATED";
            case ITestResult.FAILURE -> "FAILURE";
            case ITestResult.SKIP -> "SKIP";
            case ITestResult.STARTED -> "STARTED";
            case ITestResult.SUCCESS -> "SUCCESS";
            case ITestResult.SUCCESS_PERCENTAGE_FAILURE -> "SUCCESS_PERCENTAGE_FAILURE";
            default -> "?";
        };
        return result;
    }
}

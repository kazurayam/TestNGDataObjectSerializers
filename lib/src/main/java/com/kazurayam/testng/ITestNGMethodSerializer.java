package com.kazurayam.testng;

import org.testng.ITestNGMethod;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class ITestNGMethodSerializer extends StdSerializer<ITestNGMethod> {

    public ITestNGMethodSerializer() {
        this(null);
    }

    public ITestNGMethodSerializer(Class<ITestNGMethod> t) {
        super(t);
    }

    @Override
    public void serialize(
            ITestNGMethod value,
            JsonGenerator jgen,
            SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("id", value.getId());
        jgen.writeStringField("methodName", value.getMethodName());
        jgen.writeStringField("qualifiedName", value.getQualifiedName());
        jgen.writeEndObject();
    }
}

package com.kazurayam.testng;

import org.testng.IResultMap;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.IOException;


public class IResultMapSerializer extends StdSerializer<IResultMap> {

    public IResultMapSerializer() { this(null); }

    public IResultMapSerializer(Class<IResultMap> t) { super(t); }

    @Override
    public void serialize(IResultMap value,
                          JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeFieldName("allMethods");
        jgen.writeStartArray();
        for (ITestNGMethod m : value.getAllMethods()) {
            jgen.writeObject(m);
        }
        jgen.writeEndArray();
        //
        jgen.writeFieldName("allResults");
        jgen.writeStartArray();
        for (ITestResult r : value.getAllResults()) {
            jgen.writeObject(r);
        }
        jgen.writeEndArray();
        //
        jgen.writeEndObject();
    }
}

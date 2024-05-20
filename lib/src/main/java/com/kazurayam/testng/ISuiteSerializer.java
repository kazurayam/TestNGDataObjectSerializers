package com.kazurayam.testng;

import org.testng.ISuite;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ISuiteSerializer extends StdSerializer<ISuite> {
    public ISuiteSerializer() { this(null); }
    public ISuiteSerializer(Class<ISuite> t) { super(t); }

    @Override
    public void serialize(ISuite value,
                          JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {
        jgen.writeString(value.getName());
    }
}

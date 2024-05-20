package com.kazurayam.testng;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.testng.xml.XmlTest;

import java.io.IOException;

public class XmlTestSerializer extends StdSerializer<XmlTest> {

    public XmlTestSerializer() { this(null); }

    public XmlTestSerializer(Class<XmlTest> t) { super(t); }

    @Override
    public void serialize(XmlTest value,
                          JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {

    }
}

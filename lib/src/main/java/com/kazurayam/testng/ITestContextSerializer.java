package com.kazurayam.testng;

import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

/**
 * <a href="https://javadoc.io/doc/org.testng/testng/latest/org/testng/ITestContext.html">...</a>
 */
public class ITestContextSerializer extends StdSerializer<ITestContext> {

    public ITestContextSerializer() { this(null); }

    public ITestContextSerializer(Class<ITestContext> t) { super(t); }


    @Override
    public void serialize(
            ITestContext value,
            JsonGenerator jgen,
            SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("implementation-class",
                value.toString().substring(0, value.toString().indexOf("@")));
        if (value.getHost() != null) {
            jgen.writeStringField("host", value.getHost());
        }
        if (value.getName() != null) {
            jgen.writeStringField("name", value.getName());
        }
        if (value.getStartDate() != null) {
            jgen.writeObjectField("startDate", value.getStartDate());
        }
        if (value.getEndDate() != null) {
            jgen.writeObjectField("endDate", value.getEndDate());
        }
        if (value.getOutputDirectory() != null) {
            jgen.writeStringField("outputDirectory", value.getOutputDirectory());
        }
        //
        jgen.writeFieldName("attributes");
        jgen.writeStartObject();
        for (String attrName : value.getAttributeNames()) {
            Object v = value.getAttribute(attrName);
            if (v != null) {
                jgen.writeObjectField(attrName, v);
            }
        }
        jgen.writeEndObject();

        //
        //XmlTest xmlTest = value.getCurrentXmlTest();

        //
        jgen.writeEndObject();
    }

}

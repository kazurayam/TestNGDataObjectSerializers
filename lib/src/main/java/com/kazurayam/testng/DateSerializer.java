package com.kazurayam.testng;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer extends StdSerializer<Date> {

    private DateFormat dateFormat;

    public DateSerializer() { this(null); }

    public DateSerializer(Class<Date> t) {
        super(t);
        dateFormat = getDefaultDateFormat();
    }

    public DateSerializer dateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    public static final DateFormat getDefaultDateFormat() {
        DateFormat df = new SimpleDateFormat();
        return df;
    }

    private String formatDate(Date date) {
        return dateFormat.format(date);
    }

    @Override
    public void serialize(Date value,
                          JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {
        jgen.writeString(formatDate(value));
    }
}

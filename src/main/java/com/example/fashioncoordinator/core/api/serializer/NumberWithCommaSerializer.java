package com.example.fashioncoordinator.core.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberWithCommaSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); // 3자리마다 쉼표 적용
            gen.writeString(formatter.format(value));
        }
    }
}

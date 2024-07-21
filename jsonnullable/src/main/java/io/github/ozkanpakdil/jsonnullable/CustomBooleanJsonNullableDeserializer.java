package io.github.ozkanpakdil.jsonnullable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.openapitools.jackson.nullable.JsonNullable;

import java.io.IOException;

public class CustomBooleanJsonNullableDeserializer extends JsonDeserializer<JsonNullable<Boolean>> {

    @Override
    public JsonNullable<Boolean> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getCurrentToken().isBoolean()) {
            return JsonNullable.of(p.getBooleanValue());
        } else if (p.getCurrentToken().isNumeric()) {
            // Treat 1 as true, 0 as false, anything else as null
            int intValue = p.getIntValue();
            if (intValue == 1) {
                return JsonNullable.of(true);
            } else if (intValue == 0) {
                return JsonNullable.of(false);
            }
        } else if (p.getCurrentToken().isScalarValue()) {
            // For string values, you can define custom logic
            String stringValue = p.getValueAsString().toLowerCase();
            if ("true".equals(stringValue) || "yes".equals(stringValue) || "1".equals(stringValue)) {
                return JsonNullable.of(true);
            } else if ("false".equals(stringValue) || "no".equals(stringValue) || "0".equals(stringValue)) {
                return JsonNullable.of(false);
            }
        }

        // If none of the above conditions are met, return undefined
        return JsonNullable.undefined();
    }
}
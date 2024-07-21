package io.github.ozkanpakdil.jsonnullable;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openapitools.jackson.nullable.JsonNullable;

public class MyDto {
    @JsonProperty("myBooleanField")
    private JsonNullable<Boolean> myBooleanField;

    // Getters and setters
    public JsonNullable<Boolean> getMyBooleanField() {
        return myBooleanField;
    }

    public void setMyBooleanField(JsonNullable<Boolean> myBooleanField) {
        this.myBooleanField = myBooleanField;
    }
}
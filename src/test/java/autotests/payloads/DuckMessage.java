package autotests.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true)
public class DuckMessage {
    @JsonProperty
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}

package autotests.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true)
public class DuckSound {
    @JsonProperty
    private String sound;

    public void setSound(String message) {
        this.sound = sound;
    }
}

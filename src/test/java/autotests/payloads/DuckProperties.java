package autotests.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class DuckProperties {
    @JsonProperty
    private String color;
    @JsonProperty
    private double height;
    @JsonProperty
    private String material;
    @JsonProperty
    private String sound;
    @JsonProperty
    private String wingsState;

    public void setColor(String color) {
        this.color = color;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setWingsState(String wingsState) {
        this.wingsState = wingsState;
    }
}

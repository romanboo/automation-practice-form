package models.demowebshop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CartResponse {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("updatetopcartsectionhtml")
    private String updateTopCartSectionHtml;

    @JsonProperty("updateflyoutcartsectionhtml")
    private String updateFlyOutCartSectionHtml;

}

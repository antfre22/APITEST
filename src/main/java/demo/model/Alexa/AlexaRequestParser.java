package demo.model.Alexa;

import com.fasterxml.jackson.databind.JsonNode;

public class AlexaRequestParser {
    public static String getSlotValue(JsonNode request, String slotName) {
        JsonNode slots = request.path("request").path("intent").path("slots");
        if (slots.has(slotName)) {
            JsonNode slot = slots.get(slotName);
            if (slot.has("value")) {
                return slot.get("value").asText();
            }
        }
        return null;
    }
}


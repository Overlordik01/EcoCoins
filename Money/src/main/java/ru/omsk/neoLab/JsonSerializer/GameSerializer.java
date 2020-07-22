package ru.omsk.neoLab.JsonSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameSerializer {

    public static String jsonString;

    public static void serialize(final Object object) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

package space.luckycurve.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Jackson {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<?, ?> convertObjectToMap(Object object) {
        return objectMapper.convertValue(object, Map.class);
    }
}

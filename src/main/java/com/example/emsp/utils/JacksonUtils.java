package com.example.emsp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UncheckedIOException;

public class JacksonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toString(Object data) {
        try {
            return JacksonUtils.MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public static <T> T parse(String text, Class<T> type) {
        if (text == null) {
            return null;
        } else {
            try {
                return JacksonUtils.MAPPER.readValue(text, type);
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
    }

}

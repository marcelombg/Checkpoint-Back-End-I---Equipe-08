package com.example.CheckpointBackEndIEquipe08.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String asJsonString(final Object obj){
        try{
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static <T> T objectFromString(Class<T> aClass, String value){
        try{
            return objectMapper.readValue(value, aClass);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}

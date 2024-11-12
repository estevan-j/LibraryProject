package com.LibraryProject.LibraryProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ConvertData implements IConverData{

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> T getData(String json, Class<T> clazz) {
        try{
            return objectMapper.readValue(json, clazz);
        }  catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package br.com.alura.LiterAlura.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversaoDados implements IConversaoDados {

    private final ObjectMapper objectMapper = new ObjectMapper();


    public <T> T getDados(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


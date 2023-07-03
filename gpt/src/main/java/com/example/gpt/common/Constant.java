package com.example.gpt.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
    public static String OPEN_API_KEY;

    @Value("${chatgpt.api-key}")
    public void setOpenApiKey(String apiKey) {
        OPEN_API_KEY = apiKey;
    }
}

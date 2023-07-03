package com.example.gpt.domain.chat.service;

import com.example.gpt.domain.chat.ChatGptConfig;
import com.example.gpt.domain.chat.dto.ChatGptRequest;
import com.example.gpt.domain.chat.dto.ChatGptResponse;
import com.example.gpt.domain.chat.dto.PerfumeStoryRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PerfumeStoryService {
    @Value("${chatgpt.api-key}")
    private String API_KEY;
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequest> createHttpEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + API_KEY);
        return new HttpEntity<>(chatGptRequest, headers);
    }

    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequest) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequest,
                ChatGptResponse.class);
        if (isGptCannotResponse(responseEntity)) {
//            throw new GptCannotMakeStoryException();
        }
        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestionToChatGpt(PerfumeStoryRequest perfumeStoryRequest) {
        return this.getResponse(
                this.createHttpEntity(
                        ChatGptRequest.builder()
                                .model(ChatGptConfig.MODEL)
                                .prompt(perfumeStoryRequest.toPromptString())
                                .maxTokens(ChatGptConfig.MAX_TOKEN)
                                .temperature(ChatGptConfig.TEMPERATURE)
                                .topP(ChatGptConfig.TOP_P)
                                .build()));
    }

    public boolean isGptCannotResponse(HttpEntity<ChatGptResponse> chatGptResponseEntity) {
        if (chatGptResponseEntity.getBody().getChoices().isEmpty() || chatGptResponseEntity.getBody().getChoices() == null) {
            return true;
        }
        return false;
    }
}

package com.example.gpt.domain.gpt.controller;

import com.example.gpt.domain.gpt.service.GptService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/chat-gpt")
public class GptController {
    private final GptService chatService;
    private final ChatgptService chatgptService;

    //chat-gpt 와 간단한 채팅 서비스 소스
    @PostMapping("/v1")
    public String test(@RequestBody String question){
        return chatService.getChatResponse(question);
        //\n\nAs an AI language model, I don't have feelings, but I'm functioning well. Thank you for asking. How can I assist you today?
    }

}

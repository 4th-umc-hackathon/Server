package com.example.gpt.domain.chat.controller;

import com.example.gpt.domain.chat.dto.ChatGptResponse;
import com.example.gpt.domain.chat.dto.StoryRequest;
import com.example.gpt.domain.chat.service.StoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/perfume")
public class PerfumeStoryController implements PerfumeStoryControllerDocs {
    private final StoryService storyService;

    public PerfumeStoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping("/make-story")
    public ResponseEntity<ChatGptResponse> makePerfumeStory(@RequestBody final StoryRequest storyRequest) {
        log.info("Chat GPT에게 향수 스토리 생성 요청, 질문 내용 : {}", storyRequest.toPromptString());
        return ResponseEntity.ok(storyService.askQuestionToChatGpt(storyRequest));
    }

}


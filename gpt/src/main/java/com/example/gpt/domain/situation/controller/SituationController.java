package com.example.gpt.domain.situation.controller;

import com.example.gpt.domain.situation.dto.QuestionRequestDto;
import com.example.gpt.domain.situation.dto.SituationResponseDto;
import com.example.gpt.domain.situation.service.SituationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat-gpt")
public class SituationController {

    private final SituationService situationService;

    public SituationController(SituationService situationService) {
        this.situationService = situationService;
    }

    @PostMapping("/question")
    public SituationResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        return situationService.askQuestion(requestDto);
    }
}

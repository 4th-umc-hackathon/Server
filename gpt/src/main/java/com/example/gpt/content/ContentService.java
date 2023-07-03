package com.example.gpt.content;

import com.example.gpt.content.model.Content;
import com.example.gpt.query.model.MyQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    public Content saveContent(String message, String feedback, MyQuery myQueryCreated){
        Content content = new Content(message, feedback, myQueryCreated);

        return contentRepository.save(content);
    }
    public Content saveContent(String message, MyQuery myQueryCreated){
        Content content = new Content(message, myQueryCreated);

        return contentRepository.save(content);
    }
}
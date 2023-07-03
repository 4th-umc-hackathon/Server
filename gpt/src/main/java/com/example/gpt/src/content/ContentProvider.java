package com.example.gpt.src.content;

import com.example.gpt.src.content.dto.response.GetContentRes;
import com.example.gpt.src.content.model.Content;
import com.example.gpt.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentProvider {
    private final ContentRepository contentRepository;

    public List<GetContentRes> getContentResList(Long queryId){
        List<Content> contentList = contentRepository.findAllByMyQueryId(queryId);

        return EntityDtoMapper.INSTANCE.toContentResList(contentList);
    }
    public GetContentRes getContentRes(Long contentId){
        Optional<Content> contentOptional = contentRepository.findById(contentId);
        Content content = contentOptional.get();
        GetContentRes contentRes = new GetContentRes();
        contentRes.setContent(content.getContent());
        contentRes.setId(content.getId());
        contentRes.setCreatedAt(content.getCreatedAt());
        return contentRes;
    }

    public List<String> getFeedbackRes(Long queryId) {
        List<Content> contentList = contentRepository.findAllByMyQueryId(queryId);
        List<String> feedbackList = new ArrayList<>();
        System.out.println(contentList.size());
        for (Content content : contentList) {
            String feedback = content.getFeedback();
            if (feedback != null) {
                System.out.println(feedback);
                feedbackList.add(feedback);
            }
        }
        System.out.println(feedbackList.size());
        return feedbackList;
    }
}
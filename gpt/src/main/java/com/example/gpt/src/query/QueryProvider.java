package com.example.gpt.src.query;

import com.example.gpt.src.content.ContentProvider;
import com.example.gpt.src.content.dto.response.GetContentRes;
import com.example.gpt.src.query.dto.response.QueriesRes;
import com.example.gpt.src.query.model.MyQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryProvider {
    private final QueryRepository queryRepository;
    private final ContentProvider contentProvider;

    public List<QueriesRes> getQueries(Long userId){
        List<MyQuery> myQueryList = queryRepository.findAllByUserId(userId);
        List<QueriesRes> queriesResList = new ArrayList<>();

        for(MyQuery myQuery : myQueryList){
            QueriesRes queriesRes = QueriesRes.builder()
                    .myRole(myQuery.getMyRole())
                    .yourRole(myQuery.getYourRole())
                    .situation(myQuery.getSituation())
                    .createdAt(myQuery.getCreatedAt())
                    .contentResList(contentProvider.getContentResList(myQuery.getId()))
                    .build();
            queriesResList.add(queriesRes);
        }

        return queriesResList;
    }

    public GetContentRes getContent(Long contentId) {
        GetContentRes getContentRes = contentProvider.getContentRes(contentId);
        return getContentRes;
    }

    public List<String> getFeedback(Long queryId) {
        List<String> feedbackList = contentProvider.getFeedbackRes(queryId);
        return feedbackList;
    }
}
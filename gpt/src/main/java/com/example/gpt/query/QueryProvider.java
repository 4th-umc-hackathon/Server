package com.example.gpt.query;

import com.example.gpt.content.ContentProvider;
import com.example.gpt.content.dto.response.GetContentRes;
import com.example.gpt.query.dto.response.QueriesRes;
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
//        List<MyQuery> myQueryList = queryRepository.findAllByUserId(userId);
        List<QueriesRes> queriesResList = new ArrayList<>();

//        for(MyQuery myQuery : myQueryList){
//            QueriesRes queriesRes = QueriesRes.builder()
//                    .level(myQuery.getLevel())
//                    .place(myQuery.getPlace())
//                    .situation(myQuery.getSituation())
//                    .aiRole(myQuery.getAiRole())
//                    .myRole(myQuery.getMyRole())
//                    .createdAt(myQuery.getCreatedAt())
//                    .contentResList(contentProvider.getContentResList(myQuery.getId()))
//                    .build();
//            queriesResList.add(queriesRes);
//        }

        return queriesResList;
    }

    public GetContentRes getContent(Long contentId) {
        GetContentRes getContentRes = contentProvider.getContentRes(contentId);
        return getContentRes;
    }
}
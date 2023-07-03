package com.example.gpt.query;


import com.example.gpt.chatGPT.ChatGPTService;
import com.example.gpt.content.ContentService;
import com.example.gpt.content.dto.response.ContentRes;
import com.example.gpt.content.model.Content;
import com.example.gpt.query.dto.request.QueryReq;
import com.example.gpt.query.dto.response.QueryRes;
import com.example.gpt.query.model.MyQuery;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryService {
    private final ChatGPTService chatGPTService;
    private final ContentService contentService;
    private final QueryRepository queryRepository;
//    private final UserRepository userRepository;
//    private final ProfileRepository profileRepository;

    // 질문하기
    public QueryRes query(QueryReq queryReq){
        String content = makeContent(queryReq);
        ChatCompletionChoice result = chatGPTService.query(content);

        MyQuery myQueryCreated = saveQuery(content, queryReq);
        Content contentCreated = contentService.saveContent(result.getMessage().getContent(), myQueryCreated);

        return new QueryRes(
                myQueryCreated.getId(),
                myQueryCreated.getLevel(),
                myQueryCreated.getPlace(),
                myQueryCreated.getSituation(),
                myQueryCreated.getMyRole(),
                myQueryCreated.getAiRole(),
                myQueryCreated.getCreatedAt(),
                new ContentRes(contentCreated.getId(), result.getMessage().getContent(), result.getFinishReason())
        );
    }

//    public QueryRes requery(Long queryId, RequeryReq requeryReq){
//        MyQuery query = queryRepository.findById(queryId)
//                .orElseThrow(
//                        () -> new BaseException(BaseResponseStatus.INVALID_QUERY_ID)
//                );
//        ChatCompletionChoice result = chatGPTService.requery(query, requeryReq);
//        // content 저장하기
//        Content contentCreated = contentService.saveContent(result.getMessage().getContent(), requeryReq.getFeedback(), query);
//
//        QueryRes queryRes = EntityDtoMapper.INSTANCE.toQueryRes(query);
//        queryRes.setContentRes(new ContentRes(contentCreated.getId(), contentCreated.getContent(), result.getFinishReason()));
//
//        return queryRes;
//    }

    // 질문 저장
    public MyQuery saveQuery(String question, QueryReq queryReq) {
        // 질문 저장
//        User user = userRepository.findById(queryReq.getUserId())
//                .orElseThrow(
//                        () -> new BaseException(BaseResponseStatus.INVALID_USER_ID)
//                );
//        Profile profile = profileRepository.findById(queryReq.getProfileId())
//                .orElseThrow(
//                        () -> new BaseException(BaseResponseStatus.INVALID_PROFILE_ID)
//                );

        MyQuery myQuery = MyQuery.builder()
                .level(queryReq.getLevel())
                .place(queryReq.getPlace())
                .situation(queryReq.getSituation())
                .myRole(queryReq.getMyRole())
                .aiRole(queryReq.getAiRole())
                .build();

        return queryRepository.save(myQuery);
    }

    public String makeContent(QueryReq queryReq){
        String level = "";
        if (queryReq.getLevel().equals("pre"))
            level = "20";
        else if (queryReq.getLevel().equals("mid"))
            level = "50";
        else
            level = "70";
        String place = queryReq.getPlace();
        String situation = queryReq.getSituation();
        String aiRole = queryReq.getAiRole();
        String myRole = queryReq.getMyRole();
        String content =
                "Please write in english the next line according to the given situation\n" +
                        "Condition 1 : Limit all conversations to " + level +" words\n" +
                        "place : " + place + " \n" +
                        "situation : " + situation+ "\n" +
                        "your role : " + aiRole +"\n"+
                        "my role : " + myRole;
        return situation;
    }

}
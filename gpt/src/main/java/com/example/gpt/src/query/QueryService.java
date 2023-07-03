package com.example.gpt.src.query;


import com.example.gpt.common.BaseResponseStatus;
import com.example.gpt.exeption.BaseException;
import com.example.gpt.src.chatGPT.ChatGPTService;
import com.example.gpt.src.content.ContentService;
import com.example.gpt.src.content.dto.response.ContentRes;
import com.example.gpt.src.content.model.Content;
import com.example.gpt.src.query.dto.request.QueryReq;
import com.example.gpt.src.query.dto.request.RequeryReq;
import com.example.gpt.src.query.dto.response.QueryRes;
import com.example.gpt.src.query.model.MyQuery;
import com.example.gpt.src.user.UserRepository;
import com.example.gpt.src.user.model.User;
import com.example.gpt.utils.EntityDtoMapper;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryService {
    private final ChatGPTService chatGPTService;
    private final ContentService contentService;
    private final QueryRepository queryRepository;
    private final UserRepository userRepository;
//    private final ProfileRepository profileRepository;

    // 질문하기
    public QueryRes query(QueryReq queryReq){
        String content = makeContent(queryReq);
        ChatCompletionChoice result = chatGPTService.query(content);

        MyQuery myQueryCreated = saveQuery(content, queryReq);
        Content contentCreated = contentService.saveContent(result.getMessage().getContent(), myQueryCreated);

        return new QueryRes(
                myQueryCreated.getId(),
                myQueryCreated.getMyRole(),
                myQueryCreated.getYourRole(),
                myQueryCreated.getSituation(),
                myQueryCreated.getCreatedAt(),
                new ContentRes(contentCreated.getId(), result.getMessage().getContent(), result.getFinishReason())
        );
    }

    public QueryRes requery(Long queryId, RequeryReq requeryReq){
        MyQuery query = queryRepository.findById(queryId)
                .orElseThrow(
                        () -> new BaseException(BaseResponseStatus.INVALID_QUERY_ID)
                );
        System.out.println(query);
        ChatCompletionChoice result = chatGPTService.requery(query, requeryReq);
        // content 저장하기
        Content contentCreated = contentService.saveContent(result.getMessage().getContent(), requeryReq.getFeedback(), query);

        QueryRes queryRes = EntityDtoMapper.INSTANCE.toQueryRes(query);
        queryRes.setContentRes(new ContentRes(contentCreated.getId(), contentCreated.getContent(), result.getFinishReason()));

        return queryRes;
    }

    // 질문 저장
    public MyQuery saveQuery(String question, QueryReq queryReq) {
        // 질문 저장
        User user = userRepository.findById(queryReq.getUserId())
                .orElseThrow(
                        () -> new BaseException(BaseResponseStatus.INVALID_USER_ID)
                );

        MyQuery myQuery = MyQuery.builder()
                .level(queryReq.getLevel())
                .age(queryReq.getAge())
                .place(queryReq.getPlace())
                .myRole(queryReq.getMyRole())
                .yourRole(queryReq.getYourRole())
                .situation(queryReq.getSituation())
                .user(user)
                .build();

        return queryRepository.save(myQuery);
    }

    public String makeContent(QueryReq queryReq){
        String content =
                "Condition 5 : Limit all conversations to " + queryReq.getLevel() + "words \n" +
                "Condition 6 : Proceed to the level of " + queryReq.getAge() + "school students \n" +
                "- My role : " + queryReq.getMyRole() + "\n" +
                        "- Your role : " + queryReq.getYourRole() + "\n" +
                        "- Place : " + queryReq.getPlace() + "\n" +
                        "- Situation : " + queryReq.getSituation() + "\n";

        System.out.println(content);

        return content;
    }
}
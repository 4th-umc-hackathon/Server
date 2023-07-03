package com.example.gpt.src.chatGPT;


import com.example.gpt.common.Constant;
import com.example.gpt.src.content.ContentProvider;
import com.example.gpt.src.content.dto.response.GetContentRes;
import com.example.gpt.src.query.dto.request.RequeryReq;
import com.example.gpt.src.query.model.MyQuery;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatGPTService {
    private OpenAiService openAiService = new OpenAiService(Constant.OPEN_API_KEY, Duration.ofSeconds(60));
//    private final ProfileRepository profileRepository;
    private final ContentProvider contentProvider;

    public ChatCompletionChoice query(String content){
        List<ChatMessage> chatMessages = new ArrayList<>();

        chatMessages.add(new ChatMessage("system", "Please write in english the next line according to the given situation\n" +
                "Condition 1 : Don't write your role like \"Your role:\"\n" +
                "Condition 2 : Don't write \"Question:\"\n" +
                "Condition 3 : Don't write \"As the\" and \"as a\"\n" +
                "Condition 4 : First you start the question please\n"));
        chatMessages.add(new ChatMessage("user", content));

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(chatMessages)
                .temperature(0.8)
                .frequencyPenalty(0.8)
                .presencePenalty(0.8)
                .build();

        ChatCompletionChoice chatCompletionChoice = openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0);

        return chatCompletionChoice;
    }

    public ChatCompletionChoice requery(MyQuery query, RequeryReq requeryReq){
        // query id로 content list 가져오기
        List<GetContentRes> getContentResList = contentProvider.getContentResList(query.getId());
        List<ChatMessage> chatMessages = new ArrayList<>();

        chatMessages.add(new ChatMessage("user", "Limit all conversations to 20 words"));
        chatMessages.add(new ChatMessage("user", "Proceed to the level of elemetary school students "));

        for(int i=0; i<getContentResList.size(); i++){
            if(getContentResList.size() == 1){
                System.out.println(1);
//                chatMessages.add(new ChatMessage("user", query.getQuestion()));
                chatMessages.add(new ChatMessage("system", getContentResList.get(i).getContent()));
                chatMessages.add(new ChatMessage("user", requeryReq.getFeedback()));
                break;
            }
            if(i == 0){
                System.out.println(2);
//                chatMessages.add(new ChatMessage("user", query.getQuestion()));
            }
            else if (i == getContentResList.size()-1) {
                    System.out.println(3);
            chatMessages.add(new ChatMessage("system", getContentResList.get(i).getContent()));
            chatMessages.add(new ChatMessage("user", requeryReq.getFeedback()));
        }
            else {
                    System.out.println(4);
                    chatMessages.add(new ChatMessage("system", getContentResList.get(i).getContent()));
                    chatMessages.add(new ChatMessage("user", getContentResList.get(i).getFeedback()));
            }
        }
        chatMessages.forEach(System.out::println);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(chatMessages)
                .temperature(0.8)
                .frequencyPenalty(0.8)
                .presencePenalty(0.8)
                .build();

        ChatCompletionChoice chatCompletionChoice = openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0);

        return chatCompletionChoice;
    }
}
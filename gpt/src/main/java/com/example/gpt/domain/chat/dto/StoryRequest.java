package com.example.gpt.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class StoryRequest implements Serializable {
    private String place;

    private String situation;

    private String your_role;

    private String my_role;

//    private String styleAnswer;

    public StoryRequest(){}

    @Builder
    public StoryRequest(String place, String situation, String your_role, String my_role) {
        this.place = place;
        this.situation = situation;
        this.your_role = your_role;
        this.my_role = my_role;

    }

    public String toPromptString() {
        return "Please write in english the next line according to the given situation\n" +
                "Condition 1 : Don't write your role like \"Your role:\"\n" +
                "Condition 2 : Don't write \"Question:\"\n" +
                "Condition 3 : Don't write \"As the\" and \"as a\"\n" +
                "Condition 4 : First you start the question please\n" +
                "Condition 5 : Limit all conversations to 20 words  \n" +
                "Condition 6 : Proceed to the level of elementary school students " +
                " place : " + place +
                ", situation: " + situation +
                ", your role: " + your_role +
                ", my role: " + my_role +
                 "." ;
    }
}

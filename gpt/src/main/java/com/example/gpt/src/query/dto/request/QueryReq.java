package com.example.gpt.src.query.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class QueryReq {
    private String level;
    private String age;
    private String place;
    private String situation;
    private String myRole;
    private String yourRole;
    private Long userId;
}
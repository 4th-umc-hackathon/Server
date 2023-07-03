package com.example.gpt.query.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QueryReq {
    private String level;
    private String place;
    private String situation;
    private String myRole;
    private String aiRole;
}

package com.example.gpt.query.dto.response;

import com.example.gpt.content.dto.response.ContentRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryRes {
    private Long id;
    private String level;
    private String place;
    private String situation;
    private String myRole;
    private String aiRole;
    private String createdAt;
    private ContentRes contentRes;
}

package com.example.gpt.src.query.dto.response;

import com.example.gpt.src.content.dto.response.ContentRes;
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
    private String myRole;
    private String yourRole;
    private String situation;
    private String createdAt;
    private ContentRes contentRes;
}
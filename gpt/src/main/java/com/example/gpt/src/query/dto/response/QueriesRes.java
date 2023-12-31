package com.example.gpt.src.query.dto.response;

import com.example.gpt.src.content.dto.response.GetContentRes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueriesRes {
    private String myRole;
    private String yourRole;
    private String situation;
    private String createdAt;
    private List<GetContentRes> contentResList;
}
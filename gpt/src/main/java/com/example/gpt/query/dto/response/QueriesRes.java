package com.example.gpt.query.dto.response;

import com.example.gpt.content.dto.response.GetContentRes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueriesRes {
    private String level;
    private String place;
    private String situation;
    private String myRole;
    private String aiRole;
    private String createdAt;
    private List<GetContentRes> contentResList;
}

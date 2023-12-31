package com.example.gpt.src.content.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetContentRes {
    private Long id;
    private String content;
    private String feedback;
    private String createdAt;
}

package com.example.gpt.utils;

import com.example.gpt.src.content.dto.response.GetContentRes;
import com.example.gpt.src.content.model.Content;
import com.example.gpt.src.query.dto.response.QueryRes;
import com.example.gpt.src.query.model.MyQuery;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    GetContentRes toContentRes(Content content);
    List<GetContentRes> toContentResList(List<Content> contentList);
// 수정
    QueryRes toQueryRes(MyQuery query);
    List<QueryRes> toQueryResList(List<MyQuery> queryList);

}

package com.example.gpt.utils;

import com.example.gpt.content.dto.response.GetContentRes;
import com.example.gpt.content.model.Content;
import com.example.gpt.query.dto.response.QueryRes;
import com.example.gpt.query.model.MyQuery;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

//    @Mapping(target = "user.id", source = "userId")
//    Query toQuery(QueryRes queryRes);

    GetContentRes toContentRes(Content content);
    List<GetContentRes> toContentResList(List<Content> contentList);

    QueryRes toQueryRes(MyQuery query);
    List<QueryRes> toQueryResList(List<MyQuery> queryList);

}

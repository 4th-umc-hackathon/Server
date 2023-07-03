package com.example.gpt.utils;

import com.example.gpt.src.content.dto.response.GetContentRes;
import com.example.gpt.src.content.model.Content;
import com.example.gpt.src.query.dto.response.QueryRes;
import com.example.gpt.src.query.model.MyQuery;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-04T00:23:16+0900",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class EntityDtoMapperImpl implements EntityDtoMapper {

    @Override
    public GetContentRes toContentRes(Content content) {
        if ( content == null ) {
            return null;
        }

        GetContentRes getContentRes = new GetContentRes();

        getContentRes.setId( content.getId() );
        getContentRes.setContent( content.getContent() );
        getContentRes.setFeedback( content.getFeedback() );
        getContentRes.setCreatedAt( content.getCreatedAt() );

        return getContentRes;
    }

    @Override
    public List<GetContentRes> toContentResList(List<Content> contentList) {
        if ( contentList == null ) {
            return null;
        }

        List<GetContentRes> list = new ArrayList<GetContentRes>( contentList.size() );
        for ( Content content : contentList ) {
            list.add( toContentRes( content ) );
        }

        return list;
    }

    @Override
    public QueryRes toQueryRes(MyQuery query) {
        if ( query == null ) {
            return null;
        }

        QueryRes queryRes = new QueryRes();

        queryRes.setId( query.getId() );
        queryRes.setMyRole( query.getMyRole() );
        queryRes.setYourRole( query.getYourRole() );
        queryRes.setSituation( query.getSituation() );
        queryRes.setCreatedAt( query.getCreatedAt() );

        return queryRes;
    }

    @Override
    public List<QueryRes> toQueryResList(List<MyQuery> queryList) {
        if ( queryList == null ) {
            return null;
        }

        List<QueryRes> list = new ArrayList<QueryRes>( queryList.size() );
        for ( MyQuery myQuery : queryList ) {
            list.add( toQueryRes( myQuery ) );
        }

        return list;
    }
}

package com.example.gpt.query;

import com.example.gpt.common.BaseResponse;
import com.example.gpt.common.BaseResponseStatus;
import com.example.gpt.query.dto.request.QueryReq;
import com.example.gpt.query.dto.response.QueryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/home/query")
@RequiredArgsConstructor
public class QueryController {
    private final QueryService queryService;
    private final QueryProvider queryProvider;


    // 첫 질문하기
    @PostMapping("")
    public BaseResponse<QueryRes> query(
            @RequestBody QueryReq queryReq
    ){
        return new BaseResponse(BaseResponseStatus.SUCCESS, queryService.query(queryReq));
    }

//    // 재질문하기
//    @PostMapping("/{queryId}")
//    public BaseResponse<QueryRes> requery(
//            @PathVariable Long queryId,
//            @RequestBody RequeryReq requeryReq
//    ){
//        return new BaseResponse(BaseResponseStatus.SUCCESS, queryService.requery(queryId, requeryReq));
//    }

}
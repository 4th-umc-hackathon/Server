package com.example.gpt.src.query;

import com.example.gpt.common.BaseResponse;
import com.example.gpt.common.BaseResponseStatus;
import com.example.gpt.src.query.dto.request.QueryReq;
import com.example.gpt.src.query.dto.request.RequeryReq;
import com.example.gpt.src.query.dto.response.QueriesRes;
import com.example.gpt.src.query.dto.response.QueryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
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

//    // 주제 설정하기
//    @PutMapping("/theme")
//    public BaseResponse<QueryRes> query(
//            @RequestBody QueryReq queryReq
//    ){
//        return new BaseResponse(BaseResponseStatus.SUCCESS, queryService.query(queryReq));
//    }

    // 재질문하기
    @PostMapping("/{queryId}")
    public BaseResponse<QueryRes> requery(
            @PathVariable Long queryId,
            @RequestBody RequeryReq requeryReq
    ){
        return new BaseResponse(BaseResponseStatus.SUCCESS, queryService.requery(queryId, requeryReq));
    }

    // 글 목록 조회
    @GetMapping("/content")
    public BaseResponse<List<QueriesRes>> getQueries(
            @RequestParam Long userId
    ){
        return new BaseResponse(BaseResponseStatus.SUCCESS, queryProvider.getQueries(userId));
    }

    // 글 목록 조회
    @GetMapping("/content/{contentId}")
    public BaseResponse<String> getContent(@PathVariable("contentId") Long contentId){
        return new BaseResponse(BaseResponseStatus.SUCCESS, queryProvider.getContent(contentId));
    }

}
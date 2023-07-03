package com.example.gpt.domain.situation.service;

import com.example.gpt.domain.situation.config.SituationConfig;
import com.example.gpt.domain.situation.dto.QuestionRequestDto;
import com.example.gpt.domain.situation.dto.SituationRequestDto;
import com.example.gpt.domain.situation.dto.SituationResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SituationService {
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<SituationRequestDto> buildHttpEntity(SituationRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(SituationConfig.MEDIA_TYPE));
        headers.add(SituationConfig.AUTHORIZATION, SituationConfig.BEARER + SituationConfig.API_KEY);
        return new HttpEntity<>(requestDto, headers);
    }

    public SituationResponseDto getResponse(HttpEntity<SituationRequestDto> chatGptRequestDtoHttpEntity) {
//        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
//            @Override
//            public boolean hasError(ClientHttpResponse response) throws IOException {
//                HttpStatus statusCode = response.getStatusCode();
//                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
//            }
//        });

        ResponseEntity<SituationResponseDto> responseEntity = restTemplate.postForEntity(
                SituationConfig.URL,
                chatGptRequestDtoHttpEntity,
                SituationResponseDto.class);

        return responseEntity.getBody();
    }

    public SituationResponseDto askQuestion(QuestionRequestDto requestDto) {
        return this.getResponse(
                this.buildHttpEntity(
                        new SituationRequestDto(
                                SituationConfig.MODEL,
                                requestDto.getQuestion(),
                                SituationConfig.MAX_TOKEN,
                                SituationConfig.TEMPERATURE,
                                SituationConfig.TOP_P
                        )
                )
        );
    }
}

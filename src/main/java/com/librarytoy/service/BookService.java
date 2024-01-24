package com.librarytoy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarytoy.dto.BookSaveRequestDto;
import com.librarytoy.dto.SearchBookOfNaverResponseDto;
import com.librarytoy.dto.SearchBookResponseDto;
import com.librarytoy.repository.SavedBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final SavedBookRepository savedBookRepository;

    @Value("${X-Naver-Client-Id}")
    private String NAVER_CLIENT_ID;

    @Value("${X-Naver-Client-Secret-ID}")
    private String NAVER_CLIENT_SECRET_ID;

    // 도서 검색 api (title)
    public List<SearchBookResponseDto> bookSearch(String query){
        // RequestParam 방식으로 책의 제목을 가져와서 조회(api)
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", query)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", NAVER_CLIENT_ID)
                .header("X-Naver-Client-Secret", NAVER_CLIENT_SECRET_ID)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        ObjectMapper om = new ObjectMapper();
        SearchBookOfNaverResponseDto searchBookOfNaverResponseDto = null;

        try {
            searchBookOfNaverResponseDto = om.readValue(resp.getBody(), SearchBookOfNaverResponseDto.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        List<SearchBookResponseDto> searchBookResponseDtos = searchBookOfNaverResponseDto.getItems();

        return searchBookResponseDtos;
    }


    // 도서 저장 api
    public String bookSave(BookSaveRequestDto bookSaveRequestDto) {
        // 체크한 책의 구분 key를 받아옴 (requestdto)
        return "asdf";

        // 책의 정보를 저장 (중복체크)
    }
}
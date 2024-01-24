package com.librarytoy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarytoy.dto.BookSaveRequestDto;
import com.librarytoy.dto.SearchBookOfNaverResponseDto;
import com.librarytoy.dto.SearchBookResponseDto;
import com.librarytoy.entity.SavedBook;
import com.librarytoy.repository.SavedBookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.beans.Transient;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        Optional<SavedBook> findBook = savedBookRepository.findByIsbn(bookSaveRequestDto.getIsbn());

        if(findBook.isEmpty()){
            SavedBook savedBook = new SavedBook(bookSaveRequestDto);
            savedBookRepository.save(savedBook);
            return "도서가 저장되었습니다.";
        } else{
            return "이미 저장된 도서입니다.";
        }
    }
}
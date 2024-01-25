package com.librarytoy.controller;

import com.librarytoy.dto.BookSaveRequestDto;
import com.librarytoy.dto.SearchBookResponseDto;
import com.librarytoy.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    /**
     * 도서 검색 api (query)
     * @param query 검색할 책의 제목
     * @param page 확인할 페이지 번호
     * @return 검색한 책의 정보 반환
     */
    @GetMapping("/books")
    ResponseEntity<List<SearchBookResponseDto>> bookSearch(@RequestParam("query") String query,
                                                           @RequestParam(name = "page", defaultValue = "1") int page){
        List<SearchBookResponseDto> searchBookResponseDtos = bookService.bookSearch(query, page);
        return ResponseEntity.ok(searchBookResponseDtos);
    }

    /**
     * 도서 저장 api
     * @param bookSaveRequestDto 저장할 책의 정보(id)
     * @return 도서 저장 성공/실패 여부 반환
     */
    @PostMapping("/books")
    ResponseEntity<String> bookSave(@RequestBody BookSaveRequestDto bookSaveRequestDto){
        String result = bookService.bookSave(bookSaveRequestDto);
        return ResponseEntity.ok(result);
    }

    /**
     * 저장된 도서 조회 api
     * @return 저장된 책의 정보 반환
     */
    @GetMapping("/books/save")
    ResponseEntity<List<SearchBookResponseDto>> saveBooks(){
        List<SearchBookResponseDto> searchBookResponseDtos = bookService.saveBooks();
        return ResponseEntity.ok(searchBookResponseDtos);
    }

    /**
     * 저장된 도서 삭제 api
     * @param isbn 삭제할 책의 isbn번호
     * @return 삭제 수행 결과
     */
    @DeleteMapping("/books/save/{isbn}")
    ResponseEntity<String> deleteBook(@PathVariable String isbn){
        String result = bookService.deleteBook(isbn);
        return ResponseEntity.ok(result);
    }

}
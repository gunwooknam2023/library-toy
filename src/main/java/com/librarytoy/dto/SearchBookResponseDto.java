package com.librarytoy.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchBookResponseDto {
    private String title; // 책 제목
    private String link; // 네이버 도서 정보 URL
    private String image; // 책 이미지 URL
    private String author; // 저자 이름
    private String discount; // 판매 가격(판매 하지 않으면 비어있는 값)
    private String publisher; // 출판사
    private String pubdate; // 출간일
    private String isbn; // ISBN
    private String description; // 책 설명
}

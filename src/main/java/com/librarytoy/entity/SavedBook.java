package com.librarytoy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter

@Table(name = "savedbooks")
public class SavedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    // 책 제목
    @Column(name = "title")
    private String title;

    // 네이버 도서 정보 URL
    @Column(name = "link")
    private String link;

    // 책 이미지 URL
    @Column(name = "image")
    private String image;

    // 저자 이름
    @Column(name = "author")
    private String author;

    // 판매 가격(판매 하지 않으면 비어있는 값)
    @Column(name = "discount")
    private String discount;

    // 출판사
    @Column(name = "publisher")
    private String publisher;

    // 출간일
    @Column(name = "pubdate")
    private String pubdate;

    // ISBN
    @Column(name = "isbn")
    private String isbn;

    // 책 설명
    @Column(name = "description")
    private String description;
}
package com.librarytoy.entity;

import com.librarytoy.dto.BookSaveRequestDto;
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

    public SavedBook(BookSaveRequestDto bookSaveRequestDto){
            this.title = bookSaveRequestDto.getTitle();
            this.link = bookSaveRequestDto.getLink(); // 네이버 도서 정보 URL
            this.image = bookSaveRequestDto.getImage(); // 책 이미지 URL
            this.author = bookSaveRequestDto.getAuthor(); // 저자 이름
            this.discount = bookSaveRequestDto.getDiscount(); // 판매 가격(판매 하지 않으면 비어있는 값)
            this.publisher = bookSaveRequestDto.getPublisher(); // 출판사
            this.pubdate = bookSaveRequestDto.getPubdate(); // 출간일
            this.isbn = bookSaveRequestDto.getIsbn(); // ISBN
    }
}

package com.librarytoy.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchBookOfNaverResponseDto {
    private String lastBuildDate; // 검색 결과를 생성한 시간
    private int total; // 총 검색 결과 개수
    private int start; // 검색 시작 위치
    private int display; // 한 번에 표시할 검색 결과 개수
    private List<SearchBookResponseDto> items; // 개별 검색 결과들
}

package org.example.board.dto.word.dto.response;

import lombok.Getter;

import java.time.LocalDate;

/**
 * 단어 단건 조회에 대한 응답
 * /word/{id}
 */
@Getter
public class WordResponse {

    private String name;
    private String description;
    private String writer;
    private LocalDate createAt;
    private Long viewCount;

    public WordResponse(String name, String description, String writer, LocalDate createAt, Long viewCount) {
        this.name = name;
        this.description = description;
        this.writer = writer;
        this.createAt = createAt;
        this.viewCount = viewCount;
    }

    public static WordResponse of(String name, String description, String writer, LocalDate createAt, Long viewCount) {
        return new WordResponse(name, description, writer, createAt, viewCount);
    }
}

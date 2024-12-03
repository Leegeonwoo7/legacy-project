package org.example.board.dto.word.dto.response;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FindWordResponse {

    private String name;
    private String description;
    private String writer;
    private LocalDate createAt;
    private Long viewCount;

    public FindWordResponse(String name, String description, String writer, LocalDate createAt, Long viewCount) {
        this.name = name;
        this.description = description;
        this.writer = writer;
        this.createAt = createAt;
        this.viewCount = viewCount;
    }

    public static FindWordResponse of(String name, String description, String writer, LocalDate createAt, Long viewCount) {
        return new FindWordResponse(name, description, writer, createAt, viewCount);
    }
}

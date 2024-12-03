package org.example.board.dto.user.dto.response;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserWordResponse {
    private Long wordId;
    private String name;
    private LocalDate createAt;
    private Long viewCount;

    public UserWordResponse(Long wordId, String name, LocalDate createAt, Long viewCount) {
        this.wordId = wordId;
        this.name = name;
        this.createAt = createAt;
        this.viewCount = viewCount;
    }

    public static UserWordResponse of(Long wordId, String name, LocalDate createAt, Long viewCount) {
        return new UserWordResponse(wordId, name, createAt, viewCount);
    }
}

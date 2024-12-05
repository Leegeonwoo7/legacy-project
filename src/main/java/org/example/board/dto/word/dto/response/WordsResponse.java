package org.example.board.dto.word.dto.response;

import lombok.Getter;
import org.example.board.domain.Word;

/**
 * 단어 목록조회
 * /word
 * /word/search
 */
@Getter
public class WordsResponse {
    private Long id;
    private String name;
    private String dDay;
    private String writer;
    private Long viewCount;
    private Long userId;

    public WordsResponse(Long id, String name, String dDay, String writer, Long viewCount, Long userId) {
        this.id = id;
        this.name = name;
        this.dDay = dDay;
        this.writer = writer;
        this.viewCount = viewCount;
        this.userId = userId;
    }

    public static WordsResponse of(Word word, String username) {
        return new WordsResponse(
                word.getId(),
                word.getName(),
                word.changeToDday(),
                username,
                word.getViewCount(),
                word.getUserId()
        );
    }
}

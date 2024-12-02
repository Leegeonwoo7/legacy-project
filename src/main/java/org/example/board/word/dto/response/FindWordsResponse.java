package org.example.board.word.dto.response;

import lombok.Getter;
import org.example.board.word.Word;

@Getter
public class FindWordsResponse {
    private Long id;
    private String name;
    private String dDay;
    private String writer;
    private Long viewCount;
    private Long userId;

    public FindWordsResponse(Long id, String name, String dDay, String writer, Long viewCount, Long userId) {
        this.id = id;
        this.name = name;
        this.dDay = dDay;
        this.writer = writer;
        this.viewCount = viewCount;
        this.userId = userId;
    }

    public static FindWordsResponse of(Word word, String username) {
        return new FindWordsResponse(
                word.getId(),
                word.getName(),
                word.changeToDday(),
                username,
                word.getViewCount(),
                word.getUserId()
        );
    }
}

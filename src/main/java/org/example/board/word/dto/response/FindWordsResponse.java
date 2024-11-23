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

    public FindWordsResponse(Long id, String name, String dDay, String writer, Long viewCount) {
        this.id = id;
        this.name = name;
        this.dDay = dDay;
        this.writer = writer;
        this.viewCount = viewCount;
    }

    public static FindWordsResponse from(Word word) {
        return new FindWordsResponse(
                word.getId(),
                word.getName(),
                word.changeToDday(),
                null,
                word.getViewCount()
        );
    }
}

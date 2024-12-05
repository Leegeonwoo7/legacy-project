package org.example.board.dto.word.dto.response;

import lombok.Getter;

/**
 * 가장 많이 조회된 단어 10개에 대한 응답
 * /index
 */
@Getter
public class TopWordResponse {
    private Long wordId;
    private String wordName;
    private Long viewCount;

    public TopWordResponse(Long wordId, String wordName, Long viewCount) {
        this.wordId = wordId;
        this.wordName = wordName;
        this.viewCount = viewCount;
    }

    public static TopWordResponse of(Long wordId, String wordName, Long viewCount){
        return new TopWordResponse(wordId, wordName, viewCount);
    }
}

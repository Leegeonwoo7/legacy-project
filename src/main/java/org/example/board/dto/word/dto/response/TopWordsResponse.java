package org.example.board.dto.word.dto.response;

import lombok.Getter;

@Getter
public class TopWordsResponse {
    private Long wordId;
    private String wordName;
    private Long viewCount;

    public TopWordsResponse(Long wordId, String wordName, Long viewCount) {
        this.wordId = wordId;
        this.wordName = wordName;
        this.viewCount = viewCount;
    }

    public static TopWordsResponse of(Long wordId, String wordName, Long viewCount){
        return new TopWordsResponse(wordId, wordName, viewCount);
    }
}

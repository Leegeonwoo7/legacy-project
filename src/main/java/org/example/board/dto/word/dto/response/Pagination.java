package org.example.board.dto.word.dto.response;

import lombok.Getter;

@Getter
public class Pagination {

    private Integer totalPage;
    private Integer currentPage;

    public Pagination(Integer totalPage, Integer currentPage) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }
}

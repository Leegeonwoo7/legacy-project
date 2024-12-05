package org.example.board.dto;

import lombok.Getter;
import lombok.ToString;
import org.example.board.dto.word.dto.response.Pagination;

@Getter
@ToString
public class DataWrapper<T> {
    private T item;
    private Pagination pagination;

    public DataWrapper(T item, Pagination pagination) {
        this.item = item;
        this.pagination = pagination;
    }
}

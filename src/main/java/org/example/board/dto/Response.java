package org.example.board.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Response<T> {
    private T data;
    private String message;

    public Response(T data, String message) {
        this.data = data;
        this.message = message;
    }
}

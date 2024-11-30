package org.example.board.user;

import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String username;
    private String password;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}

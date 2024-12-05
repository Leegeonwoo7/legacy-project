package org.example.board.repository;

import org.example.board.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class UserMapperTest {

    UserMapper userMapper;
    @Test
    void test() {
        //given
        User user = new User("username", "password");

        //when
        userMapper.save(user);
        //then
    }
}
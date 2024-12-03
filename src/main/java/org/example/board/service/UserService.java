package org.example.board.service;

import org.example.board.repository.UserRepository;
import org.example.board.dto.user.dto.response.UserWordResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserWordResponse> findUserWords(Long userId) {
        return userRepository.findAllByUserId(userId);
    }
}

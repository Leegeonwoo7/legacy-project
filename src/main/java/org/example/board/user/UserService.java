package org.example.board.user;

import org.example.board.user.dto.response.UserWordResponse;
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

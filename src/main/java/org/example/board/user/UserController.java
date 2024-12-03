package org.example.board.user;

import org.example.board.user.dto.response.UserWordResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public String findUserWords(@PathVariable Long userId, Model model) {
        List<UserWordResponse> responses = userService.findUserWords(userId);
        model.addAttribute("response", responses);
        return "/user";
    }
}

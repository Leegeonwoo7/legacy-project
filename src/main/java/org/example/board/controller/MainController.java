package org.example.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.board.dto.word.dto.response.TopWordsResponse;
import org.example.board.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
public class MainController {
    private final WordService wordService;

    public MainController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String findTopWords(Model model) {
        log.debug("호출");
        List<TopWordsResponse> responses = wordService.findTopWords();
        model.addAttribute("topWords", responses);
        return "/index";
    }
}

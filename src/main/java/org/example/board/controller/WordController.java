package org.example.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.board.dto.Response;
import org.example.board.domain.Word;
import org.example.board.service.WordService;
import org.example.board.dto.word.dto.response.WordResponse;
import org.example.board.dto.word.dto.response.WordsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/word")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/new")
    public String wordForm() {
        return "/wordForm";
    }

    @GetMapping
    public String words(Model model, @RequestParam(defaultValue = "1") Integer page) {
        Response<?> response = wordService.findAll(page);
        model.addAttribute("words", response);

        return "/word";
    }

    @PostMapping("/new")
    public String newWord(@RequestParam String name, @RequestParam String description) {
        Word word = new Word(null, name, description, LocalDate.now(), null, null);
        wordService.save(word);
        return "redirect:/word";
    }

    @GetMapping("/{id}")
    public String wordView(@PathVariable Long id, Model model) {
        WordResponse response = wordService.findWord(id);
        model.addAttribute("word", response);
        return "/wordView";
    }

    @GetMapping("/search")
    public String findByCondition(@RequestParam String query, Model model) {
        List<WordsResponse> responses = wordService.findByCondition(query);
        model.addAttribute("words", responses);
        return "/word";
    }
}

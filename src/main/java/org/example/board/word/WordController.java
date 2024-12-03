package org.example.board.word;

import lombok.extern.slf4j.Slf4j;
import org.example.board.user.UserRepository;
import org.example.board.word.dto.response.FindWordResponse;
import org.example.board.word.dto.response.FindWordsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/word")
public class WordController {
    private final WordService wordService = new WordService(new WordRepository(), new UserRepository());

    @GetMapping("/new")
    public String wordForm() {
        return "/wordForm";
    }

    @GetMapping
    public String words(Model model) {
        List<FindWordsResponse> words = wordService.findAll();
        model.addAttribute("words", words);
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
        log.debug("[컨트롤러] INPUT ID = {}", id);
        FindWordResponse response = wordService.findWord(id);
        log.debug("[컨트롤러] OUTPUT RESPONSE = {}", response);
        model.addAttribute("word", response);
        return "/wordView";
    }

    @GetMapping("/search")
    public String findByCondition(@RequestParam String query, Model model) {
        log.debug("[컨트롤러] 검색어 = {}", query);

        List<FindWordsResponse> responses = wordService.findByCondition(query);
        log.debug("[컨트롤러] 응답 객체 = {}", responses);
        model.addAttribute("words", responses);
        return "/word";
    }
}

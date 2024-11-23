package org.example.board.word;

import org.example.board.word.dto.response.FindWordsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/word")
public class WordController {
    private final WordService wordService = new WordService(new WordRepository());

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
        Word word = new Word(null, name, description, null, null, null);
        wordService.save(word);
        return "redirect:/word";
    }
}

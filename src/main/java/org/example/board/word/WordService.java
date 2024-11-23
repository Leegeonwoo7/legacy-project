package org.example.board.word;

import org.example.board.word.dto.response.FindWordsResponse;

import java.util.ArrayList;
import java.util.List;

public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public void save(Word word) {
        wordRepository.save(word);
    }

    public List<FindWordsResponse> findAll() {
        List<Word> words = wordRepository.findAll();
        List<FindWordsResponse> responses = new ArrayList<>();

        for (Word word : words) {
            FindWordsResponse response = FindWordsResponse.from(word);
            responses.add(response);
        }

        return responses;
    }

}

package org.example.board.service;

import org.example.board.repository.WordRepository;
import org.springframework.stereotype.Service;

@Service
public class WordPageService {
    private static int OFFSET_DATA_COUNT = 10;

    private final WordRepository wordRepository;

    public WordPageService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public int findTotalPage() {
        int totalWord = wordRepository.findTotalWord();
        return 0;
    }
}

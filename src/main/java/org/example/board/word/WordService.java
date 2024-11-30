package org.example.board.word;

import lombok.extern.slf4j.Slf4j;
import org.example.board.user.UserRepository;
import org.example.board.word.dto.response.FindWordResponse;
import org.example.board.word.dto.response.FindWordsResponse;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WordService {
    private final WordRepository wordRepository;
    private final UserRepository userRepository;

    public WordService(WordRepository wordRepository, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
    }

    public void save(Word word) {
        wordRepository.save(word);
    }

    public List<FindWordsResponse> findAll() {
        List<Word> words = wordRepository.findAll();
        List<FindWordsResponse> responses = new ArrayList<>();

        for (Word word : words) {
            String username = userRepository.findUsernameById(word.getUserId());
            FindWordsResponse response = FindWordsResponse.of(word, username);
            responses.add(response);
        }

        return responses;
    }

    //트랜잭션 적용필요, 예외 발생시 viewCount가 증가되서는 안됨
    public FindWordResponse findWord(final Long wordId) {
        FindWordResponse response = wordRepository.findWordWithWriterById(wordId);
        log.debug("[서비스] RESPONSE = {}", response);
        wordRepository.updateViewCount(wordId);
        return response;
    }
}

package org.example.board.service;

import lombok.extern.slf4j.Slf4j;
import org.example.board.dto.word.dto.response.TopWordsResponse;
import org.example.board.repository.WordRepository;
import org.example.board.repository.UserRepository;
import org.example.board.domain.Word;
import org.example.board.dto.word.dto.response.FindWordResponse;
import org.example.board.dto.word.dto.response.FindWordsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
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

    public List<FindWordsResponse> findByCondition(String condition) {
        List<Word> words = wordRepository.searchWord(condition);
        log.debug("[서비스] 검색어에 대한 단어목록 = {}", words);
        List<FindWordsResponse> responses = new ArrayList<>();

        for (Word word : words) {
            String username = userRepository.findUsernameById(word.getUserId());
            FindWordsResponse response = FindWordsResponse.of(word, username);
            responses.add(response);
        }

        return responses;
    }

    public List<TopWordsResponse> findTopWords() {
        return wordRepository.findTopWords();
    }
}

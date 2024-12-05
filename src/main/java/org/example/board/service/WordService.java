package org.example.board.service;

import lombok.extern.slf4j.Slf4j;
import org.example.board.dto.DataWrapper;
import org.example.board.dto.Response;
import org.example.board.dto.word.dto.response.Pagination;
import org.example.board.dto.word.dto.response.TopWordResponse;
import org.example.board.repository.WordRepository;
import org.example.board.repository.UserRepository;
import org.example.board.domain.Word;
import org.example.board.dto.word.dto.response.WordResponse;
import org.example.board.dto.word.dto.response.WordsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.example.board.constants.PageInfo.*;

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

    public Response<?> findAll(int currentPage) {
        int totalCount = wordRepository.findTotalWord();
        int totalPage = (int) Math.ceil((double) totalCount / TOTAL_PAGE);
        int offset = (currentPage - 1) * TOTAL_PAGE;
        List<Word> words = wordRepository.test(TOTAL_PAGE, offset);


        List<WordsResponse> list = new ArrayList<>();
        for (Word word : words) {
            String username = userRepository.findUsernameById(word.getUserId());
            list.add(WordsResponse.of(word, username));
        }

        Pagination pagination = new Pagination(totalPage, currentPage);
        DataWrapper<?> dataWrapper = new DataWrapper<>(list, pagination);
        return new Response<>(dataWrapper, "success");
    }

    //트랜잭션 적용필요, 예외 발생시 viewCount가 증가되서는 안됨
    public WordResponse findWord(final Long wordId) {
        WordResponse response = wordRepository.findWordWithWriter(wordId);
        wordRepository.updateViewCount(wordId);
        return response;
    }

    public List<WordsResponse> findByCondition(int currentPage, String condition) {
        List<Word> words = wordRepository.findBy(condition);
        List<WordsResponse> responses = new ArrayList<>();


        for (Word word : words) {
            String username = userRepository.findUsernameById(word.getUserId());
            WordsResponse response = WordsResponse.of(word, username);
            responses.add(response);
        }

        int totalCount = wordRepository.findTotalWord();
        int totalPage = (int) Math.ceil((double) totalCount / TOTAL_PAGE);
        new Pagination(totalPage, currentPage);
        return responses;
    }

    public List<TopWordResponse> findTopWords() {
        return wordRepository.findTopWords();
    }
}

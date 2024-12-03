package org.example.word;

import org.example.board.domain.Word;
import org.example.board.repository.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WordRepositoryTest {

    WordRepository wordRepository = new WordRepository();

    @BeforeEach
    void clear() {
        wordRepository.deleteAll();
    }

    @Test
    void 하나의_단어를_저장한다() {
        //given
        Word word = new Word(null, "JPA", "객체와 DB사이의 패러다임 불일치 해결", LocalDate.now(), null, null);
        wordRepository.save(word);

        //when
        Word findWord = wordRepository.findByName("JPA");

        //then
        assertThat(findWord).isNotNull();

    }

    @Test
    void 모든_단어목록을_조회한다() {
        //given
        Word JPA = new Word(null, "JPA", "객체와 DB사이의 패러다임 불일치 해결", LocalDate.now(), null, null);
        Word SPRING = new Word(null, "SPRING", "객체지향적인 코드작성을 서포트해줌", LocalDate.now(), null, null);
        Word LOMBOK = new Word(null, "LOMBOK", "Getter,Setter와 같은 반복되는 코드를 어노테이션으로 해결", LocalDate.now(), null, null);

        wordRepository.save(JPA);
        wordRepository.save(SPRING);
        wordRepository.save(LOMBOK);

        //when
        List<Word> words = wordRepository.findAll();

        //then
        assertThat(words).hasSize(3);
    }

    @Test
    void view_count_를_1씩_증가시킨다() {
        //given
        Word JPA = new Word(null, "JPA", "객체와 DB사이의 패러다임 불일치 해결", LocalDate.now(), null, null);
        wordRepository.save(JPA);

        //when
        wordRepository.updateViewCount(1L);

        //then
        Word word = wordRepository.findById(1L);
        assertThat(word).extracting("id", "name", "viewCount")
                .containsExactly(1L, "JPA", 1L);
    }
}
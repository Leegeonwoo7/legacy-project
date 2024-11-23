package org.example.word;

import org.example.board.exception.NotFoundException;
import org.example.board.word.Word;
import org.example.board.word.WordRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

class WordRepositoryTest {

    WordRepository wordRepository = new WordRepository();

    @Test
    void 하나의_단어를_저장한다() {
        //given
        Word word = new Word(null, "JPA", "객체와 DB사이의 패러다임 불일치 해결", LocalDate.now(), null, null);
        wordRepository.save(word);

        //when
        Word findWord = wordRepository.find("JPA");

        //then
        assertThat(findWord).isNotNull();

    }

    @Test
    void 단어_등록일자_년_단위_테스트() {
        LocalDate beforeOneYear = LocalDate.of(2023, 11, 22);
        LocalDate today = LocalDate.now();

        long between = ChronoUnit.DAYS.between(beforeOneYear, today);
        System.out.println(dateFormat(between));

        LocalDate beforeTwoYear = LocalDate.of(2022, 11, 22);
        long twoYear = ChronoUnit.DAYS.between(beforeTwoYear, today);
        System.out.println(dateFormat(twoYear));
    }

    @Test
    void 단어_등록일자_개월_단위_테스트() {
        LocalDate beforeTwoMonth = LocalDate.of(2024, 9, 22);
        LocalDate today = LocalDate.now();

        long between = ChronoUnit.DAYS.between(beforeTwoMonth, today);
        System.out.println(dateFormat(between));

        LocalDate beforeOneMonth = LocalDate.of(2024, 10, 22);
        long oneMonth = ChronoUnit.DAYS.between(beforeOneMonth, today);
        System.out.println(dateFormat(oneMonth));
    }

    @Test
    void 단어_등록일자_주_단위_테스트() {
        LocalDate beforeOneWeek = LocalDate.of(2024, 11, 17);
        LocalDate today = LocalDate.now();

        long between = ChronoUnit.DAYS.between(beforeOneWeek, today);
        System.out.println(dateFormat(between));

        LocalDate beforeThreeWeek = LocalDate.of(2024, 11, 16);
        long threeWeek = ChronoUnit.DAYS.between(beforeThreeWeek, today);
        System.out.println(dateFormat(threeWeek));
    }

    private static String dateFormat(long day) {
        if (day >= 365) {
            return day / 365 + "년 전";
        }
        if (day > 30) {
            return day / 29 + "개월 전";
        }
        if (day >= 7) {
            return day / 7 + "주 전";
        }
        return day + "일 전";
    }
}
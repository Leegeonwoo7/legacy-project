package org.example.board.word;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class Word {
    private Long id;
    private String name;
    private String description;
    private LocalDate createAt;
    private Long userId;
    private Long viewCount;

    public Word(Long id, String name, String description, LocalDate createAt, Long userId, Long viewCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createAt = createAt;
        this.userId = userId;
        this.viewCount = viewCount;
    }

    public String changeToDday() {
        long day = ChronoUnit.DAYS.between(createAt, LocalDate.now());
        if (day >= 365) {
            return day / 365 + "년 전";
        }
        if (day > 30) {
            return day / 29 + "개월 전";
        }
        if (day >= 7) {
            return day / 7 + "주 전";
        }
        if (day > 1) {
            return day + "일 전";
        }
        return "오늘";
    }
}

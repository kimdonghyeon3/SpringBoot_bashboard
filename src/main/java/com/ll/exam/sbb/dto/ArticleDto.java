package com.ll.exam.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleDto {

    private static long lastId = 0;
    private final long id;
    private final String title;
    private final String body;

    public ArticleDto(String title, String body) {
        this(++lastId, title, body);
    }
}

package com.iceb.library.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSearchDto {

    private String search;

    private List<String> author;

    private List<String> publisher;

    private List<String> genre;

    private List<String> topic;

    private List<String> language;

    private Integer year;

    private Boolean donation;

    private List<Boolean> archived;

}

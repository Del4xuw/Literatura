package com.alura.literatura.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Kevin
 */
@Data
public class GutendexBook {
    private String title;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("download_count")
    private int downloadCount;

    @JsonProperty("authors")
    private List<Author> authors;

    @Data
    public static class Author {
        @JsonProperty("name")
        private String name;
    }
}
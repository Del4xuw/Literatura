package com.alura.literatura.api.response;

import lombok.Data;

import java.util.List;

/**
 * @author Kevin
 */
@Data
public class GutendexResponse {
    private List<GutendexBook> results;
}
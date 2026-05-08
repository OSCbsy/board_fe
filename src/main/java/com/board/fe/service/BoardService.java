package com.board.fe.service;

import com.board.fe.dto.BoardDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class BoardService {

    private final RestClient restClient;

    public BoardService(RestClient.Builder restClientBuilder,
                        @Value("${board.api.base-url}") String baseUrl) {
        this.restClient = restClientBuilder
                .baseUrl(baseUrl)
                .build();
    }

    public List<BoardDto> findAll() {
        return restClient.get()
                .uri("/boards")
                .retrieve()
                .body(new ParameterizedTypeReference<List<BoardDto>>() {
                });
    }

    public BoardDto findById(Long id) {
        return restClient.get()
                .uri("/boards/{id}", id)
                .retrieve()
                .body(BoardDto.class);
    }

    public BoardDto create(String title, String author, String content) {
        return restClient.post()
                .uri("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildDto(null, title, author, content))
                .retrieve()
                .body(BoardDto.class);
    }

    public BoardDto update(Long id, String title, String author, String content) {
        return restClient.put()
                .uri("/boards/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildDto(id, title, author, content))
                .retrieve()
                .body(BoardDto.class);
    }

    public void delete(Long id) {
        restClient.delete()
                .uri("/boards/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    private BoardDto buildDto(Long id, String title, String author, String content) {
        BoardDto dto = new BoardDto();
        dto.setId(id);
        dto.setTitle(title);
        dto.setAuthor(author);
        dto.setContent(content);
        return dto;
    }
}

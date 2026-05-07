package com.board.fe.client;

import com.board.fe.dto.BoardDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class BoardApiClient {

    private final RestClient restClient;

    public BoardApiClient(RestClient boardApiRestClient) {
        this.restClient = boardApiRestClient;
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

    public BoardDto create(BoardDto request) {
        return restClient.post()
                .uri("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(BoardDto.class);
    }

    public BoardDto update(Long id, BoardDto request) {
        return restClient.put()
                .uri("/boards/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(BoardDto.class);
    }

    public void delete(Long id) {
        restClient.delete()
                .uri("/boards/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}

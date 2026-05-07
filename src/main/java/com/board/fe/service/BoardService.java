package com.board.fe.service;

import com.board.fe.client.BoardApiClient;
import com.board.fe.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardApiClient boardApiClient;

    public BoardService(BoardApiClient boardApiClient) {
        this.boardApiClient = boardApiClient;
    }

    public List<BoardDto> findAll() {
        return boardApiClient.findAll();
    }

    public BoardDto findById(Long id) {
        return boardApiClient.findById(id);
    }

    public BoardDto create(String title, String author, String content) {
        return boardApiClient.create(buildDto(null, title, author, content));
    }

    public BoardDto update(Long id, String title, String author, String content) {
        return boardApiClient.update(id, buildDto(id, title, author, content));
    }

    public void delete(Long id) {
        boardApiClient.delete(id);
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

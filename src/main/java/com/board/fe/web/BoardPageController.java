package com.board.fe.web;

import com.board.fe.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardPageController {

    private final BoardService boardService;

    public BoardPageController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/boards")
    public String list(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "list";
    }

    @GetMapping("/boards/write")
    public String writeForm() {
        return "write";
    }

    @PostMapping("/boards")
    public String create(@RequestParam String title,
                         @RequestParam String author,
                         @RequestParam String content) {
        boardService.create(title, author, content);
        return "redirect:/boards";
    }

    @GetMapping("/boards/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "detail";
    }

    @GetMapping("/boards/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "edit";
    }

    @PostMapping("/boards/{id}/edit")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String author,
                         @RequestParam String content) {
        boardService.update(id, title, author, content);
        return "redirect:/boards/" + id;
    }

    @PostMapping("/boards/{id}/delete")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/boards";
    }
}

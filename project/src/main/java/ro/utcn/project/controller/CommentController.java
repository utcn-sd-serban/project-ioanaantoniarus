package ro.utcn.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.project.dto.CommentDTO;
import ro.utcn.project.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("reviews/{id}/comments")
    public List<CommentDTO> listAllComments(@PathVariable int id){
        return commentService.findAllComments(id);
    }

    @PostMapping("reviews/{id}/comments")
    public CommentDTO addComment(@RequestBody CommentDTO dto, @PathVariable int id){
        return commentService.addComment(dto.getText(),id);
    }
}

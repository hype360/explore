package com.hype360kh.explore.controller;

import com.hype360kh.explore.model.dto.CommentDto;
import com.hype360kh.explore.model.dto.ResponseEntity;
import com.hype360kh.explore.service.CommentService;
import com.hype360kh.explore.utils.PageableUtils;
import com.hype360kh.explore.utils.State;
import com.hype360kh.explore.utils.constant.CommonRequestParams;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comments", description = "Comment API")
@RequestMapping("/v1/comments")
@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public void create(@RequestBody CommentDto payload) {
        this.commentService.create(payload);
    }

    @GetMapping("/{id}")
    public CommentDto get(@PathVariable String id) {
        return this.commentService.get(id);
    }

    @PutMapping
    public CommentDto update(@RequestBody CommentDto payload) {
        return this.commentService.update(payload);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.commentService.delete(id);
    }

    @GetMapping
    public ResponseEntity<CommentDto> getAll(@RequestParam(required = false, defaultValue = "1") int page,
                                             @RequestParam(required = false, defaultValue = "15") int pageSize,
                                             @RequestParam(required = false, defaultValue = "asc") String sortDirection,
                                             @RequestParam(required = false, defaultValue = "createdAt") String sortField,
                                             @RequestParam(required = false, defaultValue = "") String content,
                                             @RequestParam(required = false) String filter) {
        final var pageable = PageableUtils.of(page, pageSize, sortField, sortDirection, CommonRequestParams.LIST_COMMENT);
        final var state = new State();
        state.put(CommonRequestParams.FILTER, filter);
        state.put(CommonRequestParams.CONTENT, content);
        return this.commentService.getAll(pageable, state);
    }
}

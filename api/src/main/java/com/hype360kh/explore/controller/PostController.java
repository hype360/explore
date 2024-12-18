package com.hype360kh.explore.controller;

import com.hype360kh.explore.model.dto.PostDto;
import com.hype360kh.explore.model.dto.ResponseEntity;
import com.hype360kh.explore.service.PostService;
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

@Tag(name = "Posts", description = "Posts API")
@RequestMapping("/v1/posts")
@RestController()
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostDto create(@RequestBody PostDto payload) {
        return this.postService.create(payload);
    }

    @GetMapping("/{id}")
    public PostDto get(@PathVariable String id) {
        return this.postService.get(id);
    }

    @PutMapping
    public PostDto update(@RequestBody PostDto payload) {
        return this.postService.update(payload);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.postService.delete(id);
    }

    @GetMapping
    public ResponseEntity<PostDto> getAll(@RequestParam(required = false, defaultValue = "1") int page,
                                          @RequestParam(required = false, defaultValue = "15") int pageSize,
                                          @RequestParam(required = false, defaultValue = "asc") String sortDirection,
                                          @RequestParam(required = false, defaultValue = "createdAt") String sortField,
                                          @RequestParam(required = false, defaultValue = "") String title,
                                          @RequestParam(required = false) String filter) {
        final var pageable = PageableUtils.of(page, pageSize, sortField, sortDirection, CommonRequestParams.LIST_POST);
        final var state = new State();
        state.put(CommonRequestParams.FILTER, filter);
        state.put(CommonRequestParams.TITLE, title);
        return this.postService.getAll(pageable, state);
    }
}

package com.hype360kh.explore.controller;

import com.hype360kh.explore.model.dto.GenreDto;
import com.hype360kh.explore.model.dto.ResponseEntity;
import com.hype360kh.explore.service.GenreService;
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

@Tag(name = "Genres", description = "Genre API")
@RequestMapping("/v1/genres")
@RestController
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public GenreDto create(@RequestBody GenreDto payload) {
        return this.genreService.create(payload);
    }

    @GetMapping("/{id}")
    public GenreDto get(@PathVariable String id) {
        return this.genreService.get(id);
    }

    @PutMapping
    public GenreDto update(@RequestBody GenreDto payload) {
        return this.genreService.update(payload);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.genreService.delete(id);
    }

    @GetMapping
    public ResponseEntity<GenreDto> getAll(@RequestParam(required = false, defaultValue = "1") int page,
                                           @RequestParam(required = false, defaultValue = "15") int pageSize,
                                           @RequestParam(required = false, defaultValue = "asc") String sortDirection,
                                           @RequestParam(required = false, defaultValue = "createdAt") String sortField,
                                           @RequestParam(required = false, defaultValue = "") String content,
                                           @RequestParam(required = false) String filter) {
        final var pageable = PageableUtils.of(page, pageSize, sortField, sortDirection, CommonRequestParams.LIST_GENRE);
        final var state = new State();
        state.put(CommonRequestParams.FILTER, filter);
        state.put(CommonRequestParams.CONTENT, content);
        return this.genreService.getAll(pageable, state);
    }

}

package com.hype360kh.explore.controller;

import com.hype360kh.explore.model.dto.CastDto;
import com.hype360kh.explore.model.dto.ResponseEntity;
import com.hype360kh.explore.service.CastService;
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

@Tag(name = "Casts", description = "Cast API")
@RequestMapping("/v1/casts")
@RestController
public class CastController {
    private final CastService castService;

    public CastController(CastService castService) {
        this.castService = castService;
    }

    @PostMapping
    public void create(@RequestBody CastDto payload) {
        this.castService.create(payload);
    }

    @GetMapping("/{id}")
    public CastDto get(@PathVariable String id) {
        return this.castService.get(id);
    }

    @PutMapping
    public CastDto update(@RequestBody CastDto payload) {
        return this.castService.update(payload);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.castService.delete(id);
    }

    @GetMapping
    public ResponseEntity<CastDto> getAll(@RequestParam(required = false, defaultValue = "1") int page,
                                          @RequestParam(required = false, defaultValue = "15") int pageSize,
                                          @RequestParam(required = false, defaultValue = "asc") String sortDirection,
                                          @RequestParam(required = false, defaultValue = "createdAt") String sortField,
                                          @RequestParam(required = false, defaultValue = "") String gender,
                                          @RequestParam(required = false) String filter) {
        final var pageable = PageableUtils.of(page, pageSize, sortField, sortDirection, CommonRequestParams.LIST_CAST);
        final var state = new State();
        state.put(CommonRequestParams.FILTER, filter);
        state.put(CommonRequestParams.GENDER, gender);
        return this.castService.getAll(pageable, state);
    }
}

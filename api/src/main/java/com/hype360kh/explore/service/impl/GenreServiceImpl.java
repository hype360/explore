package com.hype360kh.explore.service.impl;

import com.hype360kh.explore.model.dto.GenreDto;
import com.hype360kh.explore.model.dto.ResponseEntity;
import com.hype360kh.explore.model.entity.Genre;
import com.hype360kh.explore.repository.GenreRepository;
import com.hype360kh.explore.service.AbstractService;
import com.hype360kh.explore.service.GenreService;
import com.hype360kh.explore.service.specification.GenreSpecification;
import com.hype360kh.explore.utils.State;
import com.hype360kh.explore.utils.constant.CommonRequestParams;
import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Getter(value = AccessLevel.PRIVATE)
@Service
public class GenreServiceImpl extends AbstractService<Genre, GenreDto, GenreRepository> implements GenreService {
    protected GenreServiceImpl(GenreRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public GenreDto update(GenreDto payload) {
        return update(payload.getId(), payload);
    }

    @Override
    public ResponseEntity<GenreDto> getAll(Pageable pageable, State state) {
        final var name = state.get(CommonRequestParams.NAME, String.class);
        final var filter = state.get(CommonRequestParams.FILTER, String.class);
        var spec = Specification.where(GenreSpecification.byFilter(filter));

        if (StringUtils.isNotBlank(name)) {
            spec = spec.and(GenreSpecification.byName(name));
        }
        return this.getAll(pageable, spec);
    }
}

package com.hype360kh.explore.service.impl;

import com.hype360kh.explore.model.dto.CastDto;
import com.hype360kh.explore.model.dto.ResponseEntity;
import com.hype360kh.explore.model.entity.Cast;
import com.hype360kh.explore.repository.CastRepository;
import com.hype360kh.explore.service.AbstractService;
import com.hype360kh.explore.service.CastService;
import com.hype360kh.explore.service.specification.CastSpecification;
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
public class CastServiceImpl extends AbstractService<Cast, CastDto, CastRepository> implements CastService {
    private final CastRepository castRepository;

    public CastServiceImpl(CastRepository castRepository, ModelMapper modelMapper) {
        super(castRepository, modelMapper);
        this.castRepository = castRepository;
    }

    @Override
    public CastDto update(CastDto payload) {
        return this.update(payload.getId(), payload);
    }

    @Override
    public ResponseEntity<CastDto> getAll(Pageable pageable, State state) {
        final var gender = state.get(CommonRequestParams.GENDER, String.class);
        final var filter = state.get(CommonRequestParams.FILTER, String.class);
        var spec = Specification.where(CastSpecification.byFilter(filter));

        if (StringUtils.isNotBlank(gender)) {
            spec = spec.and(CastSpecification.byGender(gender));
        }
        return this.getAll(pageable, spec);

    }
}

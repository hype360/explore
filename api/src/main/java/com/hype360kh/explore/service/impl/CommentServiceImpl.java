package com.hype360kh.explore.service.impl;

import com.hype360kh.explore.model.dto.CommentDto;
import com.hype360kh.explore.model.dto.ResponseEntity;
import com.hype360kh.explore.model.entity.Comment;
import com.hype360kh.explore.repository.CommentRepository;
import com.hype360kh.explore.service.AbstractService;
import com.hype360kh.explore.service.CommentService;
import com.hype360kh.explore.service.specification.CommentSpecification;
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
public class CommentServiceImpl extends AbstractService<Comment, CommentDto, CommentRepository> implements CommentService {
    CommentServiceImpl(CommentRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public CommentDto update(CommentDto payload) {
        return update(payload.getId(), payload);
    }

    @Override
    public ResponseEntity<CommentDto> getAll(Pageable pageable, State state) {
        final var content = state.get(CommonRequestParams.CONTENT, String.class);
        final var filter = state.get(CommonRequestParams.FILTER, String.class);
        var spec = Specification.where(CommentSpecification.byFilter(filter));

        if (StringUtils.isNotBlank(content)) {
            spec = spec.and(CommentSpecification.byContent(content));
        }
        return this.getAll(pageable, spec);
    }
}

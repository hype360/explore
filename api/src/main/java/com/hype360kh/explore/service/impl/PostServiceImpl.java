package com.hype360kh.explore.service.impl;

import com.hype360kh.explore.model.dto.PostDto;
import com.hype360kh.explore.model.dto.ResponseEntity;
import com.hype360kh.explore.model.entity.Post;
import com.hype360kh.explore.repository.PostRepository;
import com.hype360kh.explore.service.AbstractService;
import com.hype360kh.explore.service.PostService;
import com.hype360kh.explore.service.specification.PostSpecification;
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
public class PostServiceImpl extends AbstractService<Post, PostDto, PostRepository> implements PostService {
    protected PostServiceImpl(PostRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public PostDto update(PostDto payload) {
        return update(payload.getId(), payload);
    }

    @Override
    public ResponseEntity<PostDto> getAll(Pageable pageable, State state) {
        final var title = state.get(CommonRequestParams.TITLE, String.class);
        final var filter = state.get(CommonRequestParams.FILTER, String.class);
        var spec = Specification.where(PostSpecification.byFilter(filter));

        if (StringUtils.isNotBlank(title)) {
            spec = spec.and(PostSpecification.byTitle(title));
        }
        return this.getAll(pageable, spec);

    }
}

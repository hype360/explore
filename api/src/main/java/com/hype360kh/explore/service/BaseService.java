package com.hype360kh.explore.service;

import com.hype360kh.explore.model.dto.ResponseEntity;
import com.hype360kh.explore.utils.State;
import org.springframework.data.domain.Pageable;

public interface BaseService<D> {
    D create(D payload);

    D get(String id);

    void delete(String id);

    D update(D payload);

    ResponseEntity<D> getAll(Pageable pageable, State state);
}

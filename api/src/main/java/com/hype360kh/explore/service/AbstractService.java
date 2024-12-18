package com.hype360kh.explore.service;

import com.hype360kh.explore.exception.ResourceNotFoundException;
import com.hype360kh.explore.model.dto.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public abstract class AbstractService<E, D, R extends JpaRepository<E, String> & JpaSpecificationExecutor<E>>
        extends AbstractModelMapper<D, E> {
    private final R repository;

    protected AbstractService(R repository, ModelMapper modelMapper) {
        super(modelMapper);
        this.repository = repository;
    }

    protected E getById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public D get(String id) {
        return this.mapData(getById(id));
    }

    public void delete(String id) {
        this.repository.deleteById(id);
    }

    public D create(D payload) {
        final var entity = this.repository.save(this.mapEntity(payload));
        return this.mapData(entity);
    }

    public D update(String id, D payload) {
        final var entity = getById(id);
        final var persist = this.mapEntity(payload, entity);
        return this.mapDto(this.repository.save(persist));
    }

    public ResponseEntity<D> getAll(Pageable pageable, Specification<E> spec) {
        final var data = this.repository.findAll(spec, pageable);
        final var response = data.map(this::mapData);
        return ResponseEntity.of(response);
    }
}

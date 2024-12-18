package com.hype360kh.explore.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public abstract class AbstractModelMapper<D, E> {
    protected final ModelMapper modelMapper;

    protected AbstractModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected E mapEntity(D dto) {
        return modelMapper.map(dto, this.getClassType(0));
    }

    protected D mapDto(E entity) {
        return modelMapper.map(entity, this.getClassType(1));
    }

    protected E mapEntity(D dto, E entity) {
        modelMapper.map(dto, entity);
        return entity;
    }

    protected D mapDto(E entity, D dto) {
        modelMapper.map(entity, dto);
        return dto;
    }

    protected D mapData(E entity) {
        return modelMapper.map(entity, this.getClassType(1));
    }

    protected <L, S> List<L> mapAll(final Collection<S> entities, Class<L> outClass) {
        return entities.stream().map(entity -> modelMapper.map(entity, outClass)).toList();
    }

    protected <L, S> Page<L> mapAll(final Page<S> entities, Class<L> outClass) {
        return entities.map(entity -> modelMapper.map(entity, outClass));
    }

    protected Type getClassType(int parameterTypeIndex) {
        final var superClass = (ParameterizedType) getClass().getGenericSuperclass();
        return superClass.getActualTypeArguments()[parameterTypeIndex];
    }
}

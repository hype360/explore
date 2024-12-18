package com.hype360kh.explore.service.specification;

import com.hype360kh.explore.model.entity.Cast;
import com.hype360kh.explore.model.entity.Cast_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CastSpecification {
    public static Specification<Cast> byName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(criteriaBuilder.like(root.get(Cast_.NAME), "%" + name + "%"));
    }

    public static Specification<Cast> byGender(String gender) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(criteriaBuilder.equal(root.get(Cast_.GENDER), gender));
    }

    public static Specification<Cast> byId(String id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(criteriaBuilder.equal(root.get(Cast_.ID), id));
    }

    public static Specification<Cast> byFilter(String filter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(criteriaBuilder.like(root.get(Cast_.NAME), "%" + filter + "%"),
                        criteriaBuilder.like(root.get(Cast_.DESCRIPTION), "%" + filter + "%"));
    }
}

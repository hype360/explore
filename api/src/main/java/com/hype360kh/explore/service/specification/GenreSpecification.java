package com.hype360kh.explore.service.specification;

import com.hype360kh.explore.model.entity.Genre;
import com.hype360kh.explore.model.entity.Genre_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GenreSpecification {
    public static Specification<Genre> byId(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get(Genre_.ID), id));
    }

    public static Specification<Genre> byName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.like(root.get(Genre_.NAME), "%" + name + "%"));
    }

    public static Specification<Genre> byDescription(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.like(root.get(Genre_.DESCRIPTION), "%" + description + "%"));
    }

    public static Specification<Genre> byFilter(String filter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(criteriaBuilder.like(root.get(Genre_.NAME), "%" + filter + "%"),
                        criteriaBuilder.like(root.get(Genre_.DESCRIPTION), "%" + filter + "%"));
    }
}

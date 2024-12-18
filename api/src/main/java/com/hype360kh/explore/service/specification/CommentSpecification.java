package com.hype360kh.explore.service.specification;

import com.hype360kh.explore.model.entity.Comment;
import com.hype360kh.explore.model.entity.Comment_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommentSpecification {
    public static Specification<Comment> byId(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get(Comment_.ID), id));
    }

    public static Specification<Comment> byAuthor(String author) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get(Comment_.AUTHOR), author));
    }

    public static Specification<Comment> byContent(String content) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.like(root.get(Comment_.CONTENT), "%" + content + "%"));
    }

    public static Specification<Comment> byFilter(String filter) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.like(root.get(Comment_.CONTENT), "%" + filter + "%"));
    }
}

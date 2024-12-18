package com.hype360kh.explore.service.specification;

import com.hype360kh.explore.model.entity.Post;
import com.hype360kh.explore.model.entity.Post_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PostSpecification {
    public static Specification<Post> byId(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get(Post_.ID), id));
    }

    public static Specification<Post> byTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.like(root.get(Post_.TITLE), "%" + title + "%"));
    }

    public static Specification<Post> byContent(String content) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.like(root.get(Post_.CONTENT), "%" + content + "%"));
    }

    public static Specification<Post> byAuthor(String author) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get(Post_.AUTHOR), author));
    }

    public static Specification<Post> byGenre(String genre) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get(Post_.GENRE), genre));
    }

    public static Specification<Post> byCast(String cast) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get(Post_.CAST), cast));
    }

    public static Specification<Post> byCategory(String category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get(Post_.CATEGORY), category));
    }

    public static Specification<Post> byFilter(String filter) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.like(root.get(Post_.TITLE), "%" + filter + "%"));
    }
}

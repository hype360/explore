package com.hype360kh.explore.model.entity;

import com.hype360kh.explore.model.base.BaseEntity;
import com.hype360kh.explore.model.base.CustomTsidSupplier;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity {
    @Id
    @Tsid(CustomTsidSupplier.class)
    private String id;
    private String content;
    private String author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_COMMENT_POST", value = ConstraintMode.NO_CONSTRAINT))
    private Post post;
}

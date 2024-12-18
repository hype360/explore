package com.hype360kh.explore.model.entity;

import com.hype360kh.explore.model.base.BaseEntity;
import com.hype360kh.explore.model.base.CustomTsidSupplier;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre extends BaseEntity {
    @Id
    @Tsid(CustomTsidSupplier.class)
    private String id;
    private String name;
    private String description;
}

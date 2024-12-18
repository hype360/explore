package com.hype360kh.explore.model.entity;

import com.hype360kh.explore.model.base.BaseEntity;
import com.hype360kh.explore.model.base.CustomTsidSupplier;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends BaseEntity {
    @Id
    @Tsid(CustomTsidSupplier.class)
    private String id;
    @Column(nullable = false)
    private String title;
    private String description;
    private String content;
    private String author;
    private String thumbnail;
    private String category;
    private String m3u8;
    private String slug;
    private String tags;
    private String keywords;
    private String metaDescription;
    private String metaKeywords;
    private String metaTitle;
    private String metaImage;
    private String metaRobots;
    private String metaCanonical;
    private String metaAuthor;
    private String metaPublisher;
    private String metaCopyright;
    private String metaLanguage;
    private String metaGenerator;
    private String metaRevisitAfter;
    private String metaUrl;
    private String metaRating;
    private String metaRobotsNoindex;
    private String metaRobotsNofollow;
    private String metaRobotsNoarchive;
    private String metaRobotsNoodp;
    private String metaRobotsNoimageindex;
    private String metaRobotsNotranslate;
    private String metaRobotsNosearch;
    private String metaRobotsNosnippet;
    private String status;
    private Long totalViews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cast_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_POST_CAST", value = ConstraintMode.NO_CONSTRAINT))
    private Cast cast;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_POST_GENRE", value = ConstraintMode.NO_CONSTRAINT))
    private Genre genre;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
}

package com.hype360kh.explore.model.dto;

import com.hype360kh.explore.model.entity.Cast;
import com.hype360kh.explore.model.entity.Comment;
import com.hype360kh.explore.model.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String id;
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
    private Cast cast;
    private Genre genre;
    private List<Comment> comments = new ArrayList<>();
}

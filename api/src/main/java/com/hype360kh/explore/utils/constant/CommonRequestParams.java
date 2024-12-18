package com.hype360kh.explore.utils.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonRequestParams {
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String FILTER = "filter";
    public static final String CONTENT = "content";
    public static final String GENDER = "gender";
    public static final String CREATED_AT = "createdAt";
    public static final String UPDATED_AT = "updatedAt";
    public static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String CATEGORY = "category";
    private static final String STATUS = "status";


    public static final List<String> LIST_CAST = List.of(NAME, DESCRIPTION, CREATED_AT, UPDATED_AT);
    public static final List<String> LIST_COMMENT = List.of(CONTENT, CREATED_AT, UPDATED_AT);
    public static final List<String> LIST_GENRE = List.of(NAME, DESCRIPTION, CREATED_AT, UPDATED_AT);
    public static final List<String> LIST_POST = List.of(TITLE, CONTENT, AUTHOR, CATEGORY, STATUS, CREATED_AT, UPDATED_AT);
}

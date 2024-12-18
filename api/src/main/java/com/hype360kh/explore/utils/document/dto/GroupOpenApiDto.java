package com.hype360kh.explore.utils.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupOpenApiDto implements Serializable {
    private String title;
    @Builder.Default
    private List<String> pathToMatches = new ArrayList<>();
}

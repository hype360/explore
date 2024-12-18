package com.hype360kh.explore.utils;

import com.hype360kh.explore.exception.ResourceBadRequestException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageableUtils {
    public static Pageable of(int pageNumber, int pageSize, String sortField, String sortDirection, List<String> acceptedSortFields) {
        if (!acceptedSortFields.contains(sortField)) {
            throw new ResourceBadRequestException("Invalid sort field: " + sortField);
        }
        return PageRequest.of(utilityPageNumber(pageNumber), pageSize, Sort.Direction.fromString(sortDirection), sortField);
    }

    private static int utilityPageNumber(int pageNumber) {
        return pageNumber < 1 ? 0 : pageNumber - 1;
    }
}

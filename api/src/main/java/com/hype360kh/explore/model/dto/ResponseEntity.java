package com.hype360kh.explore.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseEntity<T> {
    private int page = 1;
    private int pageSize = 0;
    private List<T> content;

    public static <T> ResponseEntity<T> of(Page<T> page) {
        return new ResponseEntity<>(page.getNumber() + 1, page.getSize(), page.getContent());
    }

    public long getTotal() {
        return content.size();
    }


}

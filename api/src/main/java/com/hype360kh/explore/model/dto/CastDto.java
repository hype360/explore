package com.hype360kh.explore.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CastDto {
    private String id;
    @Schema(example = "John Doe")
    private String name;
    private String description;
    private String photo;
    @Schema(example = "Male")
    private String gender;
    @ReadOnlyProperty
    private LocalDateTime createdAt;
    @ReadOnlyProperty
    private LocalDateTime updatedAt;
}

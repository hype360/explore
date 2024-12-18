package com.hype360kh.explore.utils.document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "hype360kh.swagger.info")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
public class SwaggerProperty {
    private String securityKey;
    private String description;
    private String title;
    private String version;
    private String termOfService;
    private String email;
    private String url;
    private String name;
}

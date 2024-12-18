package com.hype360kh.explore.config;

import com.hype360kh.explore.utils.document.SwaggerConfig;
import com.hype360kh.explore.utils.document.SwaggerProperty;
import com.hype360kh.explore.utils.document.dto.GroupOpenApiDto;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties({SwaggerProperty.class})
public class SwaggerConfigurer extends SwaggerConfig {
    private final List<GroupOpenApiDto> apiGroupConfigs =
            List.of(
                    new GroupOpenApiDto("Catalog Management", List.of("/**/v1/catalog/**")),
                    new GroupOpenApiDto("Cast Management", List.of("/**/v1/casts/**")));

    public SwaggerConfigurer(SwaggerProperty property) {
        super(property);
    }

    @Override
    public void afterPropertiesSet() {
        super.setGroupApiList(this.apiGroupConfigs);
    }
}

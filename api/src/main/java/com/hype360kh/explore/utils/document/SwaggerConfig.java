package com.hype360kh.explore.utils.document;

import com.hype360kh.explore.utils.document.dto.GroupOpenApiDto;
import com.hype360kh.explore.utils.document.dto.ResponseErrorHandler;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.customizers.SpringDocCustomizers;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.providers.SpringDocProviders;
import org.springdoc.core.service.AbstractRequestService;
import org.springdoc.core.service.GenericResponseService;
import org.springdoc.core.service.OpenAPIService;
import org.springdoc.core.service.OperationService;
import org.springdoc.webmvc.api.MultipleOpenApiWebMvcResource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

import static io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER;
import static io.swagger.v3.oas.models.security.SecurityScheme.Type.APIKEY;

public class SwaggerConfig implements WebMvcConfigurer, InitializingBean {
    private static final String REDIRECT_URL = "/swagger-ui.html";
    private static final String X_COMPANY_UUID = "X-COMPANY-UUID";
    private static final String ACCESS_TOKEN = "GRANTED-ACCESS-TOKEN";
    private static final String AUTHORIZATION = "AUTHORIZATION";
    private final List<GroupOpenApiDto> groupApiList = new ArrayList<>();

    private final SwaggerProperty swaggerProperty;

    @Value("${spring.mvc.servlet.path}")
    private String baseUrl;

    public SwaggerConfig(SwaggerProperty swaggerProperty) {
        this.swaggerProperty = swaggerProperty;
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", baseUrl.concat(REDIRECT_URL));
        registry.addRedirectViewController("/swagger-ui", baseUrl.concat(REDIRECT_URL));
        registry.addRedirectViewController("/api", baseUrl.concat(REDIRECT_URL));
    }

    @Bean
    @Lazy(value = false)
    protected OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .description(swaggerProperty.getDescription())
                                .title(swaggerProperty.getTitle())
                                .version(swaggerProperty.getVersion())
                                .termsOfService(swaggerProperty.getTermOfService())
                                .contact(
                                        new Contact()
                                                .email(swaggerProperty.getEmail())
                                                .url(swaggerProperty.getUrl())
                                                .name(swaggerProperty.getName())))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        ACCESS_TOKEN, new SecurityScheme().type(APIKEY).in(HEADER).name(AUTHORIZATION)))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(ACCESS_TOKEN));
    }


    protected void setGroupApiList(List<GroupOpenApiDto> groupApiList) {
        this.groupApiList.addAll(groupApiList);
    }


    private List<GroupedOpenApi> initialGroupOpenApi() {
        return this.groupApiList.stream().map(SwaggerConfig::groupedOpenApi).toList();
    }


    public static GroupedOpenApi groupedOpenApi(GroupOpenApiDto groupApi) {
        return GroupedOpenApi.builder()
                .group(groupApi.getTitle())
                .pathsToMatch(groupApi.getPathToMatches().toArray(new String[0]))
                .addOperationCustomizer(operationCustomizer())
                .build();
    }


    @Bean
    @Lazy(value = false)
    public MultipleOpenApiWebMvcResource multipleOpenApiResource(
            ObjectFactory<OpenAPIService> defaultOpenAPIBuilder,
            AbstractRequestService requestBuilder,
            GenericResponseService responseBuilder,
            OperationService operationParser,
            SpringDocConfigProperties springDocConfigProperties,
            SpringDocProviders springDocProviders,
            SpringDocCustomizers springDocCustomizers) {

        return new MultipleOpenApiWebMvcResource(
                this.initialGroupOpenApi(),
                defaultOpenAPIBuilder,
                requestBuilder,
                responseBuilder,
                operationParser,
                springDocConfigProperties,
                springDocProviders,
                springDocCustomizers);
    }


    private static OperationCustomizer operationCustomizer() {
        return (operation, method) -> operationResponseProcess(operation);
    }

    public static Operation operationResponseProcess(Operation operation) {
        return operation.responses(
                new ApiResponses()
                        .addApiResponse(
                                String.valueOf(HttpStatus.OK.value()),
                                operation.getResponses().get(String.valueOf(HttpStatus.OK.value())))
                        .addApiResponse(
                                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                                buildApiResponse(HttpStatus.BAD_REQUEST))
                        .addApiResponse(
                                String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                                buildApiResponse(HttpStatus.UNAUTHORIZED))
                        .addApiResponse(
                                String.valueOf(HttpStatus.FORBIDDEN.value()),
                                buildApiResponse(HttpStatus.FORBIDDEN))
                        .addApiResponse(
                                String.valueOf(HttpStatus.NOT_FOUND.value()),
                                buildApiResponse(HttpStatus.NOT_FOUND))
                        .addApiResponse(
                                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                                buildApiResponse(HttpStatus.INTERNAL_SERVER_ERROR))
                        .addApiResponse(
                                String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()),
                                buildApiResponse(HttpStatus.NOT_ACCEPTABLE)));
    }

    private static ApiResponse buildApiResponse(HttpStatus status) {
        return new ApiResponse()
                .description(status.getReasonPhrase())
                .content(
                        new Content()
                                .addMediaType(
                                        MediaType.APPLICATION_JSON_VALUE,
                                        new io.swagger.v3.oas.models.media.MediaType().schema(buildSchema(status))));
    }

    private static Schema<?> buildSchema(HttpStatus status) {
        final var errorHandler =
                new ResponseErrorHandler(
                        status, status.getReasonPhrase().concat(" occurred!"), new RuntimeException());
        final var schema = new Schema<>();
        schema.setTitle("ResponseErrorHandler");
        schema.addProperty("status", new StringSchema().example(errorHandler.getStatus()));
        schema.addProperty("statusCode", new IntegerSchema().example(errorHandler.getStatusCode()));
        schema.addProperty("timestamp", new DateSchema().example(errorHandler.getTimestamp()));
        schema.addProperty("message", new StringSchema().example(errorHandler.getMessage()));
        schema.addProperty("debugMessage", new StringSchema().example(errorHandler.getDebugMessage()));
        return schema;
    }

    @Override
    public void afterPropertiesSet() {
        // set the properties
    }
}

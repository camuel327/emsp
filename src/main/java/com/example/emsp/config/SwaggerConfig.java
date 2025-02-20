package com.example.emsp.config;

import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singletonList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).pathMapping("/")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
                .paths(PathSelectors.any())
                .build()
                .protocols(Set.of("https", "http"));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Doc - eMSP Account & Token Service")
                .description("eMSP Account & Token Service")
                .version("0.0.1")
                .build();
    }

    @Bean
    public AlternateTypeRuleConvention springDataWebPropertiesConvention(final SpringDataWebProperties webProperties) {
        return new AlternateTypeRuleConvention() {
            @Override
            public int getOrder() { return Ordered.HIGHEST_PRECEDENCE; }

            @Override
            public List<AlternateTypeRule> rules() {
                return singletonList(
                        newRule(Pageable.class, pageableDocumentedType(webProperties.getPageable(), webProperties.getSort()))
                );
            }
        };
    }

    @SuppressWarnings("all")
    private Type pageableDocumentedType(SpringDataWebProperties.Pageable pageable, SpringDataWebProperties.Sort sort) {
        final String firstPage = pageable.isOneIndexedParameters() ? "1" : "0";
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(fullyQualifiedName(Pageable.class))
                .property(property(pageable.getPageParameter(), Integer.class, Map.of(
                        "value", "Page " + (pageable.isOneIndexedParameters() ? "Number" : "Index"),
                        "defaultValue", firstPage,
                        "allowableValues", String.format("range[%s, %s]", firstPage, Integer.MAX_VALUE),
                        "example", firstPage
                )))
                .property(property(pageable.getSizeParameter(), Integer.class, Map.of(
                        "value", "Page Size",
                        "defaultValue", String.valueOf(pageable.getDefaultPageSize()),
                        "allowableValues", String.format("range[1, %s]", pageable.getMaxPageSize()),
                        "example", "5"
                )))
                .property(property(sort.getSortParameter(), String[].class, Map.of(
                        "value", "Page Multi-Sort: fieldName,(asc|desc)"
                )))
                .build();
    }

    @SuppressWarnings("all")
    private String fullyQualifiedName(Class<?> convertedClass) {
        return String.format("%s.generated.%s", convertedClass.getPackage().getName(), convertedClass.getSimpleName());
    }

    @SuppressWarnings("all")
    private AlternateTypePropertyBuilder property(String name, Class<?> type, Map<String, Object> parameters) {
        return new AlternateTypePropertyBuilder()
                .name(name)
                .type(type)
                .canRead(true)
                .canWrite(true)
                .annotations(singletonList(AnnotationProxy.of(ApiParam.class, parameters)));
    }

}

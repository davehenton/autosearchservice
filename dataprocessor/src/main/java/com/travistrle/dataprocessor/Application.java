package com.travistrle.dataprocessor;

import com.google.common.base.Predicates;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

  /**
   * Main entry point for spring boot.
   *
   * @param args {@link String}
   */
  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  /**
   * V1 APIs.
   *
   * @return {@link Docket}
   */
  @Bean
  public Docket swaggerApiV1() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("ala-api-1.0")
        .select()
        .apis(RequestHandlerSelectors
            .basePackage("com.travistrle.ums.entrypoints.controllers.api.v1"))
        .paths(Predicates.not(PathSelectors.regex("/error.*")))
        .build()
        .apiInfo(
            new ApiInfoBuilder()
                .version("1.0")
                .title("Batch Job Engine API")
                .description("Documentation Batch Job Engine API v1.0")
                .build()
        );
  }
}

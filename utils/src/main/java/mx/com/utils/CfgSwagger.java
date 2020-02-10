package mx.com.utils;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class NappSwaggerCfg.
 */
@Configuration
@EnableSwagger2
public class CfgSwagger {
  
  /**
   * ApiCatalogos.
   *
   * @return the docket
   */
  @Bean
  public Docket apiCatalogos() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Email")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("mx.com.utils.controller.rest.email"))
        .paths(regex("/api.*"))
        .build();
  }
  
  /**
   * ApiBitacora.
   *
   * @return the docket
   */
  @Bean
  public Docket apiBitacora() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Image")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("mx.com.utils.controller.rest.image"))
        .paths(regex("/api.*"))
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Spring API REST")
        .description("Api Documentation")
        .version("1.0")
        .build();
  }
  
}
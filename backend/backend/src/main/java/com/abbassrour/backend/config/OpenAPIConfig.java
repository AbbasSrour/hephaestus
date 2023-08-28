package com.abbassrour.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  //    @Value("${abbas.openapi.dev-url}")
  @Value("http://localhost:8080/docs")
  private String devUrl;

  @Value("https://abbassrour.com/api-docs")
  //     @Value("${abbas.openapi.prod-url}")
  private String prodUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setEmail("abbas.mj.srour@gmail.com");
    contact.setName("Abbas Srour");
    contact.setUrl("https://www.abbassrour.com");

    License mitLicense =
        new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info =
        new Info()
            .title("API DOCS")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints to manage tutorials.")
            .termsOfService("https://www.abbassrour.com/terms")
            .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
}

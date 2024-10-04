package com.example.ResourceServer.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class OpenApiConfig {

//    @Bean
//    public OpenAPI customOpenAPI() {
//        final String securitySchemeName = "bearerAuth";
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Resource Server API")
//                        .version("1.0")
//                        .description("API documentation for the Resource Server"))
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//                .components(new Components()
//                        .addSecuritySchemes(securitySchemeName,
//                                new SecurityScheme()
//                                        .name(securitySchemeName)
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")
//                                        .in(SecurityScheme.In.HEADER)
//                                        .description("Please enter JWT token")
//                        )
//                );
//    }

    @Value("${spring.security.oauth2.client.provider.my-auth-server.token-uri}")
    private String tokenUrl;


    @Value("${auth-server.url}")
    private String authServerUrl;

//    @Bean
//    public OpenAPI customOpenAPI() {   // client credentials type
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes("oauth2", new SecurityScheme()
//                                .type(SecurityScheme.Type.OAUTH2)
//                                .flows(new OAuthFlows()
//                                        .clientCredentials(new OAuthFlow()
//                                                .tokenUrl(tokenUrl)
//                                                .scopes(new Scopes()
//                                                        .addString("openid", "OpenID Connect scope"))))))
//                .addSecurityItem(new SecurityRequirement().addList("oauth2"));
//    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Your API Title").version("1.0.0"))
                .components(new Components()
                        .addSecuritySchemes("oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl(authServerUrl + "/oauth2/authorize")
                                                .tokenUrl(authServerUrl + "/oauth2/token")
                                                .refreshUrl(authServerUrl + "/oauth2/token")
                                                .scopes(new Scopes()
                                                        .addString("openid", "OpenID Connect scope")
                                                     )))))
                .addSecurityItem(new SecurityRequirement().addList("oauth2", Arrays.asList("openid")));
    }


//    @Bean
//    public OpenAPI customOpenAPI() {  // authorization code type
//        return new OpenAPI()
//                .info(new Info().title("Your API Title").version("1.0.0"))
//                .addServersItem(new Server().url("/"))
//                .schemaRequirement("OAuth2", createOAuthScheme());
//    }
//
//    private SecurityScheme createOAuthScheme() {
//        OAuthFlow authorizationCodeFlow = new OAuthFlow()
//                .authorizationUrl(authServerUrl + "/oauth2/authorize")
//                .tokenUrl(authServerUrl + "/oauth2/token")
//                .refreshUrl(authServerUrl + "/oauth2/token");
//
//        return new SecurityScheme()
//                .type(SecurityScheme.Type.OAUTH2)
//                .flows(new OAuthFlows().authorizationCode(authorizationCodeFlow));
//    }

}
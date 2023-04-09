package com.miridih.library.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {
    private static final String AUTH_TYPE = "bearerAuth";
    private static final String AUTH_SCHEME = "bearer";

    private final ServletContext servletContext;
    private final DocConfiguration docConfiguration;

    @Bean
    public OpenAPI openAPI() {
        DocConfiguration.GeneralInfo generalInfo = docConfiguration.getGeneral();

        // 연락처 정보
        Contact contactInfo = new Contact()
                .name(generalInfo.getContact().getName())
                .email(generalInfo.getContact().getEmail());

        // API 문서 정보
        Info info = new Info()
                .title(generalInfo.getTitle())
                .version(generalInfo.getVersion())
                .description(generalInfo.getDescription())
                .contact(contactInfo);

        // 서버 정보
        List<Server> serverList = new ArrayList<>();
        docConfiguration.getServers().forEach(server -> {
            String serverUrl = server.getProtocol() + "://" + server.getDomain() + ":" + server.getPort() + servletContext.getContextPath();
            serverList.add(new Server().url(serverUrl).description(server.getEnvironment()));
        });

        // API 문서 설정
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(AUTH_TYPE)
                )
                .components(new Components()
                        .addSecuritySchemes(AUTH_TYPE, new SecurityScheme()
                                .name(AUTH_TYPE)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(AUTH_SCHEME)
                                .bearerFormat("JWT")
                        )
                )
                .info(info)
                .servers(serverList);
    }
}

package com.miridih.library.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "doc")
@Data
public class DocConfiguration {
    @Data
    public static class ContactInfo {
        private String name;
        private String email;
    }

    @Data
    public static class ServerInfo {
        private String environment;
        private String host;
        private String port;
    }

    @Data
    public static class GeneralInfo {
        private String title;
        private String version;
        private String description;
        private ContactInfo contact;
    }

    private GeneralInfo general;
    private List<ServerInfo> servers;
}

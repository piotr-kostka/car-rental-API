package com.kodilla.rental.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ProjectConfig {

    @Value("${info.project.name}")
    private String projectName;

    @Value("${info.project.email}")
    private String projectEmail;

    @Value("${info.project.phone}")
    private String projectPhone;
}

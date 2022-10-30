package com.kodilla.rental.service.mail;

import com.kodilla.rental.config.AdminConfig;
import com.kodilla.rental.config.ProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private ProjectConfig projectConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildDailyReportMail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("project_config", projectConfig);
        context.setVariable("preview_message", "Hello!");
        context.setVariable("goodbye_message", "Have a good day!");

        return templateEngine.process("mail/daily-report", context);
    }

    public String buildNewRentalMail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("project_config", projectConfig);
        context.setVariable("preview_message", "Hello!");
        context.setVariable("goodbye_message", "Have a good day!");

        return templateEngine.process("mail/new-rental-mail", context);
    }
}

package com.kodilla.rental.service.mail;

import com.kodilla.rental.domain.mail.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private static final String SUBJECT_DAILY = "Rental Application: Daily Report";

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail) {
        try {
            javaMailSender.send(createMimeMessage(mail));
            log.info("Mail sent properly");
        } catch (MailException e) {
            log.error("Email sending failure! " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            if (mail.getSubject().equals(SUBJECT_DAILY)) {
                messageHelper.setText(mailCreatorService.buildDailyReportMail(mail.getMessage()), true);
            } else {
                messageHelper.setText(mailCreatorService.buildNewRentalMail(mail.getMessage()), true);
            }
        };
    }
}

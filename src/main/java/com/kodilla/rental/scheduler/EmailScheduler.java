package com.kodilla.rental.scheduler;

import com.kodilla.rental.config.AdminConfig;
import com.kodilla.rental.domain.mail.Mail;
import com.kodilla.rental.repository.RentalRepository;
import com.kodilla.rental.repository.UserRepository;
import com.kodilla.rental.service.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Rental Application: Daily Report";
    private final EmailService emailService;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 20 * * *")
    public void sendInformationToAdmin() {
        long rentalSize = rentalRepository.count();
        long userSize = userRepository.count();

        emailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "In database you got " + rentalSize + (rentalSize ==1 ? " rental" : " rentals") +
                                " and registered " + userSize + (userSize ==1 ? " user" : " users")
                )
        );
    }
}

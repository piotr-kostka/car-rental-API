package com.kodilla.rental.domain.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
}

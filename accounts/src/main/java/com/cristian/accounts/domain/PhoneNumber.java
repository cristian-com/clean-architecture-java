package com.cristian.accounts.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.Objects;
import java.util.StringJoiner;

@Value
public class PhoneNumber implements ContactDetail {

    String code;
    String number;
    String fullNumber;

    public PhoneNumber(String code, String number) {
        this.code = code;
        this.number = number;
        this.fullNumber = format(code, number);
    }

    public static PhoneNumber of(@Valid @NotBlank String phoneNumber) {
        Objects.requireNonNull(phoneNumber);

        if (phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number can not be blank");
        }

        return new PhoneNumber("", "");
    }

    private String format(String code, String number) {
        return new StringJoiner(" ", "+", "").add(code).add(number).toString();
    }

    @Override
    public String getText() {
        return fullNumber;
    }
}

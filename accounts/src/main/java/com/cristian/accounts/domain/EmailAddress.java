package com.cristian.accounts.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.StringJoiner;
import java.util.regex.Pattern;

import static com.cristian.accounts.common.Strings.requiresNonBlank;

@Getter
@EqualsAndHashCode(of = "fullAddress")
public class EmailAddress implements ContactDetail {

    private final String username;
    private final String domain;
    private final String fullAddress;

    private static final String REGEX =
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    private static final String AT = "@";

    public EmailAddress(@NotEmpty @Valid String username, @NotEmpty @Valid String domain) {
        requiresNonBlank(username);
        this.username = username;
        this.domain = domain;
        this.fullAddress = format(username, domain);

        requiresNonBlank(fullAddress);

        if (!PATTERN.matcher(fullAddress).matches()) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    public static EmailAddress of(@Email @NotEmpty @Valid String fullAddress) {
        String[] values = format(fullAddress);

        return new EmailAddress(values[0], values[1]);
    }

    private static String[] format(String fullAddress) {
        return fullAddress.split(AT);
    }

    private String format(String username, String domain) {
        return new StringJoiner(AT).add(username).add(domain).toString();
    }

    @Override
    public String getText() {
        return fullAddress;
    }
}

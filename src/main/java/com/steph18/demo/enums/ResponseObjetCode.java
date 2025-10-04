package com.steph18.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseObjetCode {

        OK(0, "Success"),
    NOK(500, "Failed"),
    UNKNOWN(501, "Unknown error"),
    USER_ALREADY_EXISTS(10, "User already exists"),
    USER_NOT_FOUND(11, "User not found"),
    USER_UNAUTHORISED(12, "User not active"),
        INVALID_GRANT(13,"Invalid_grant"),
        BAD_CREDENTIALS(13,"Bad credentials");

    private final int code;
    private final String description;

}

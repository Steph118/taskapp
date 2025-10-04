package com.steph18.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {
    private int code;
    private int status;
    private String message;
    private String description;
    private ResponseData data;

    public ResponseObject buildLogin(String token,Boolean changePassword) {
        this.data =  ResponseData.builder()
                .token(token)
                .changePassword(changePassword)
                .build();
        return this;
    }
}

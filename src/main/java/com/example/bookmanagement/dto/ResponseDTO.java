package com.example.bookmanagement.dto;

import lombok.Getter;

@Getter
public class ResponseDTO {
    private String message;

    public ResponseDTO(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

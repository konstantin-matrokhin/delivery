package dev.muskrat.delivery.auth.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {

    private String username;
    private String password;
}

package org.tianzun.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponseDTO implements Serializable {

    private Boolean success;

    private String system;

    private String token;

}

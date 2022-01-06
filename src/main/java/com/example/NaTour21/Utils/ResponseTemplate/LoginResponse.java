package com.example.NaTour21.Utils.ResponseTemplate;
import lombok.Data;
@Data
public class LoginResponse {

    private String action;
    private String data;

    public LoginResponse(String action, String data) {
        this.action = action;
        this.data = data;
    }
}
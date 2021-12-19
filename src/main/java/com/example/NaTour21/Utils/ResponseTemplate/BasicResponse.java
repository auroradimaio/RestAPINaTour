package com.example.NaTour21.Utils.ResponseTemplate;

import lombok.Data;
@Data
public class BasicResponse {
    private Object result;
    private String status;

    public BasicResponse(Object result, String status) {
        this.result = result;
        this.status = status;
    }

}

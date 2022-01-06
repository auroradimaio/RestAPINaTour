package com.example.NaTour21.Utils.RequestTemplate;
import lombok.Data;


@Data
public class MessageRequest {

    private String from;
    private String to;
    private String content;
}
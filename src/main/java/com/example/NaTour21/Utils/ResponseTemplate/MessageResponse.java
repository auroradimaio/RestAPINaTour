package com.example.NaTour21.Utils.ResponseTemplate;
import lombok.Data;
@Data
public class MessageResponse {

    private String from;
    private String content;
    private Long time;

    public MessageResponse(String from, String content, Long time) {
        this.from = from;
        this.content = content;
        this.time = time;
    }
}
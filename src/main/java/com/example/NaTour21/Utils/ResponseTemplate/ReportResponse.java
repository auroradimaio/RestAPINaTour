package com.example.NaTour21.Utils.ResponseTemplate;
import lombok.Data;

@Data
public class ReportResponse {

    private String post_title;
    private String from;
    private String content;
    private Long time;

    public ReportResponse(String post_title, String from, String content, Long time) {
        this.post_title = post_title;
        this.from = from;
        this.content = content;
        this.time = time;
    }
}
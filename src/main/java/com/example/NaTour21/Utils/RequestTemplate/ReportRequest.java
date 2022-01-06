package com.example.NaTour21.Utils.RequestTemplate;
import lombok.Data;

@Data
public class ReportRequest {

    private Long post_id;
    private String post_title;
    private String post_username;
    private String from;
    private String title;
    private String description;
}
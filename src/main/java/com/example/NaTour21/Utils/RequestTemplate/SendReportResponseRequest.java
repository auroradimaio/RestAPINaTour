package com.example.NaTour21.Utils.RequestTemplate;
import lombok.Data;

@Data
public class SendReportResponseRequest {

    private Long report_id;
    private String from;
    private String response_message;
}
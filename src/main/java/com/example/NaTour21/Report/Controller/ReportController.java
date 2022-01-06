package com.example.NaTour21.Report.Controller;

import com.example.NaTour21.Report.Entity.Report;
import com.example.NaTour21.Report.Service.ReportService;
import com.example.NaTour21.Post.Service.PostService;
import com.example.NaTour21.Pusher.PusherManager;
import com.example.NaTour21.Utils.RequestTemplate.ReportRequest;
import com.example.NaTour21.Utils.RequestTemplate.SendReportResponseRequest;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.example.NaTour21.Utils.ResponseTemplate.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final PostService postService;

    @PostMapping("/report/insert")
    public ResponseEntity<BasicResponse> insertReport(@RequestBody ReportRequest reportRequest) {
        BasicResponse response = null;
        try {
            Long time = new Date().getTime();
            Report report = new Report(null,reportRequest.getPost_id(),
                    reportRequest.getFrom(),reportRequest.getTitle(),
                    reportRequest.getDescription(), time,
                    null);
            response = new BasicResponse(reportService.saveReport(report), "OK");
            PusherManager.SendReport(reportRequest.getPost_username(), new ReportResponse(reportRequest.getPost_title(), reportRequest.getFrom(), reportRequest.getDescription(),time));
        } catch (Exception e) {
            response = new BasicResponse(e.getMessage(), "FAILED");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/reports")
    public ResponseEntity<BasicResponse> getReports(@Param("username") String username) {
        BasicResponse response = new BasicResponse(reportService.getReports(username.toLowerCase(Locale.ROOT)), "OK");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/report/update")
    public ResponseEntity<BasicResponse> updateResponse(@RequestBody SendReportResponseRequest request) {
        BasicResponse response = new BasicResponse(reportService.updateResponse(request.getReport_id(), request.getResponse_message()), "OK");
        return ResponseEntity.ok().body(response);
    }
}
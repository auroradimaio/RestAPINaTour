package com.example.NaTour21.Pusher;

import com.example.NaTour21.Utils.RequestTemplate.MessageRequest;
import com.example.NaTour21.Utils.ResponseTemplate.MessageResponse;
import com.example.NaTour21.Utils.ResponseTemplate.ReportResponse;
import com.pusher.rest.Pusher;

public class PusherManager {

    private static Pusher pusher = null;

    private static Pusher getPusher()
    {
        if(pusher == null)
        {
            pusher = new Pusher("1317744", "fbb723678ebc506b906c", "3b04ee6eb41125ef3d12");
            pusher.setCluster("eu");
            pusher.setEncrypted(true);
        }
        return pusher;
    }

    public static void SendMessage(String channel, MessageResponse messageResponse)
    {
        getPusher().trigger(channel, "newMessage", messageResponse);
    }

    public static void SendReport(String channel, ReportResponse reportResponse)
    {
        getPusher().trigger(channel, "report", reportResponse);
    }

    public static void SendReportResponse(String channel, String from)
    {
        getPusher().trigger(channel, "report_response", from);
    }

}
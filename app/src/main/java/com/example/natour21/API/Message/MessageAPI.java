package com.example.natour21.API.Message;

import android.app.Activity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.natour21.API.Config;
import com.example.natour21.Volley.VolleyCallback;
import com.example.natour21.Volley.VolleySingleton;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MessageAPI {

    public static void sendMessage(Activity singleChatActivity, String username, String chattingWith, String messageContent, String accessToken, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.SEND_MESSAGE;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("from", username);
            jsonBody.put("to", chattingWith);
            jsonBody.put("content", messageContent);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        final String requestBody = jsonBody.toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallback.onSuccess(response);
            }}, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallback.onError(error.getMessage());
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + accessToken);
                return params;
            }
        };

        VolleySingleton.getInstance(singleChatActivity).addToRequestQueue(stringRequest);
    }

}

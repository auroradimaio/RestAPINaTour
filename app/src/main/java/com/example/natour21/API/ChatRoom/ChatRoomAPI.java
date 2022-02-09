package com.example.natour21.API.ChatRoom;

import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.natour21.API.Config;
import com.example.natour21.Volley.VolleyCallback;
import com.example.natour21.Volley.VolleySingleton;
import java.util.HashMap;
import java.util.Map;

public class ChatRoomAPI {

    public static void getChatRooms(FragmentActivity activity, String username, String accessToken, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.GET_CHAT_ROOMS;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallback.onSuccess(response);
            }}, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallback.onError(error.getMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }

            @Override
            public Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("username", username);
                return param;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + accessToken);
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void getSingleChat(Activity singleChatActivity, String username1, String username2, String accessToken, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.GET_MESSAGE_SINGLE_CHAT;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallback.onSuccess(response);
            }}, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallback.onError(error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }


            @Override
            public Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("username1", username1);
                param.put("username2", username2);
                return param;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + accessToken);
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        VolleySingleton.getInstance(singleChatActivity).addToRequestQueue(stringRequest);
    }
}

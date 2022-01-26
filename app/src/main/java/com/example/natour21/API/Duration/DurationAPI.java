package com.example.natour21.API.Duration;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.natour21.API.Config;
import com.example.natour21.Volley.VolleyCallback;
import com.example.natour21.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class DurationAPI {

    public static void insertDurations(Activity activity, String duration, int minutes, int post_id, String accessToken, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.INSERTDURATIONS;
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("duration", duration);
            jsonBody.put("post_id",post_id);
            jsonBody.put("minutes",minutes);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

        final String requestBody = jsonBody.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallback.onError(error.getMessage());
            }
        }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
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
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + accessToken);
                return params;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);

    }

    public static void getDurationById(Activity activity, int id, TextView textView, String accessToken, RequestQueue requestQueue){

        String url = Config.BASE_URL + Config.API + Config.GETDURATIONBYID+id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int totalvalue = 0;
                    int i;
                    JSONArray jsonArray = response.getJSONArray("result");

                    for (i = 0; i < jsonArray.length(); i++) {
                        JSONObject res = jsonArray.getJSONObject(i);

                        String duration = res.getString("duration");
                        int minutes = res.getInt("minutes");

                        totalvalue = totalvalue + minutes;


                    }

                    totalvalue = totalvalue/i;
                    int hour = totalvalue/60;
                    int min = totalvalue%60;

                    textView.setText(String.format("%02d:%02d",hour,min));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(activity,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + accessToken);
                return params;
            }

        };

        requestQueue.add(request);


    }

}

package com.example.natour21.API.User;

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


public class UserAPI {

    public static void login(Activity activity, String username, String password, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.LOGIN;

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
            public Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("username", username);
                param.put("password", password);
                return param;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void loginFacebook(Activity activity, String email, String id, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.LOGIN_FACEBOOK;

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
            public Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("email", email);
                param.put("id", id);
                return param;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void loginGoogle(Activity activity, String email, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.LOGIN_GOOGLE;

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
            public Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("email", email);
                return param;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void registerNATOUR21(Activity activity, String username, String email, String password, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.REGISTER_NATOUR21;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
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
        }) {
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
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void registerFACEBOOK(Activity activity, String id, String email, String username, VolleyCallback volleyCallback) {
        String url = Config.BASE_URL + Config.API + Config.REGISTER_FACEBOOK;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("user_id", id);
            jsonBody.put("username", username);
            jsonBody.put("email", email);
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
        }) {
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
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void registerGOOGLE(Activity activity, String email, String username, VolleyCallback volleyCallback) {
        String url = Config.BASE_URL + Config.API + Config.REGISTER_GOOGLE;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("email", email);
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
        }) {
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
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void checkAuth(Activity activity, String username, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.CHECK_AUTH;

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
            public Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("username", username);
                return param;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void sendEmail(Activity activity, String email, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.API + Config.SEND_EMAIL;

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
            public Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("email", email);
                return param;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void changePassword(Activity activity, String username, String password, VolleyCallback volleyCallback) {
        String url = Config.BASE_URL + Config.API + Config.CHANGE_PASSWORD;

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
            public Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("username", username);
                param.put("password", password);
                return param;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }
}

package com.example.natour21.API.User;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
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

    public static void login(AppCompatActivity activity, String email, String password, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.USER_API + Config.LOGIN;

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
            public Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> param = new HashMap<>();
                param.put("email", email);
                param.put("password", password);
                return param;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void loginFacebook(AppCompatActivity activity, String email, String firstName, String lastName, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.USER_API + Config.LOGIN_FACEBOOK;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("firstName", firstName);
            jsonBody.put("lastName", lastName);
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
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void loginGoogle(AppCompatActivity activity, String email, String firstName, String lastName, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.USER_API + Config.LOGIN_GOOGLE;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("firstName", firstName);
            jsonBody.put("lastName", lastName);
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
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void register(AppCompatActivity activity, String firstName, String lastName, String email, String password, String auth, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.USER_API + Config.REGISTER;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("firstName", firstName);
            jsonBody.put("lastName", lastName);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
            jsonBody.put("auth",auth);
            jsonBody.put("role","ROLE_USER");
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
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void checkAuth(Activity activity, String email, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.USER_API + Config.CHECK_AUTH_NATOUR21;

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
            public Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> param = new HashMap<>();
                param.put("email", email);
                return param;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }

    public static void refreshToken(Activity activity, String refreshToken, VolleyCallback volleyCallback) {

        String url = Config.BASE_URL + Config.USER_API + Config.REFRESH_TOKEN;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + refreshToken);
                return params;
            }
        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }
}

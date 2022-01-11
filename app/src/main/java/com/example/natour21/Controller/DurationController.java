package com.example.natour21.Controller;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.natour21.API.Difficulty.DifficultyAPI;
import com.example.natour21.API.Duration.DurationAPI;
import com.example.natour21.Volley.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.natour21.Dialog.Dialog.showMessageDialog;

public class DurationController {

    public static void insertDuration(Activity activity, String duration, int minutes){

        DurationAPI.insertDuration(activity, duration,minutes, AuthenticationController.accessToken, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("OK")) {
                        Log.i("Insert Duration", "Status OK");

                    } else if (jsonObject.getString("status").equals("FAILED")) {
                        Log.e("Insert Duration", "Status FAILED");

                    } else if (jsonObject.getString("status").equals("TOKEN_EXPIRED")) {
                        AuthenticationController.logout(activity, true);
                    }
                } catch (JSONException jsonException) {
                    showMessageDialog(activity, "Impossibile inserire durata", null);
                }

            }

            @Override
            public void onError(String response) {
                showMessageDialog(activity, "Impossibile inserire difficoltà", null);
            }
        });

    }


    public static void insertDurations (Activity activity, String duration,int minutes, int post_id){

        DurationAPI.insertDurations(activity, duration,minutes,post_id, AuthenticationController.accessToken, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("OK")){
                        Log.i("Insert Duration","Status OK");

                    }else if (jsonObject.getString("status").equals("FAILED")){
                        Log.e("Insert Duration","Status FAILED");

                    }else if(jsonObject.getString("status").equals("TOKEN_EXPIRED"))
                    {
                        AuthenticationController.logout(activity, true);
                    }
                }catch (JSONException jsonException){
                    showMessageDialog(activity,"Impossibile inserire durata",null);
                }

            }

            @Override
            public void onError(String response) {
                showMessageDialog(activity,"Impossibile inserire difficoltà",null);
            }
        });

    }

    public static void getDurationById(Activity activity, int id, TextView textView, RequestQueue requestQueue){

        DurationAPI.getDurationById(activity,id,textView,AuthenticationController.accessToken, requestQueue);

    }

}

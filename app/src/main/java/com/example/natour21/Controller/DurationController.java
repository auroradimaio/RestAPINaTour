package com.example.natour21.Controller;

import android.app.Activity;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.natour21.API.Duration.DurationAPI;
import com.example.natour21.Volley.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.natour21.Dialog.Dialog.showMessageDialog;

public class DurationController {

    public static void insertDurations (Activity activity, String duration,int minutes, int post_id){

        DurationAPI.insertDurations(activity, duration,minutes,post_id, AuthenticationController.accessToken, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("OK")){
                        showMessageDialog(activity,"Cambiamenti effettuati con successo",null);
                    }else if (jsonObject.getString("status").equals("FAILED")){
                        showMessageDialog(activity,"Impossibile inserire durata",null);
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
                showMessageDialog(activity,"Impossibile inserire durata",null);
            }
        });

    }

    public static void getDurationById(Activity activity, int id, TextView textView, RequestQueue requestQueue){

        DurationAPI.getDurationById(activity,id,textView,AuthenticationController.accessToken, requestQueue);

    }

}

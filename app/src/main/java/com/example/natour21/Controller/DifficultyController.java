package com.example.natour21.Controller;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.natour21.API.Difficulty.DifficultyAPI;
import com.example.natour21.Enumeration.Auth;
import com.example.natour21.Volley.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;
import static com.example.natour21.Dialog.Dialog.showMessageDialog;

public class DifficultyController {

    public static void insertDifficulty(Activity activity, int difficulty){

        DifficultyAPI.insertDifficulty(activity, difficulty, AuthenticationController.accessToken, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("OK")){
                        Log.i("Insert Difficulty","Status OK");

                    }else if (jsonObject.getString("status").equals("FAILED")){
                        Log.e("Insert Difficulty","Status FAILED");

                    }else if(jsonObject.getString("status").equals("TOKEN_EXPIRED"))
                    {
                        AuthenticationController.logout(activity, true);
                    }
                }catch (JSONException jsonException){
                    showMessageDialog(activity,"Impossibile inserire difficoltà",null);
                }

            }

            @Override
            public void onError(String response) {
                showMessageDialog(activity,"Impossibile inserire difficoltà",null);
            }
        });

    }


    public static void insertDifficulties(Activity activity, int difficulty, int post_id){

        DifficultyAPI.insertDifficulties(activity, difficulty,post_id, AuthenticationController.accessToken, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("OK")){
                        Log.i("Insert Difficulty","Status OK");

                    }else if (jsonObject.getString("status").equals("FAILED")){
                        Log.e("Insert Difficulty","Status FAILED");

                    }else if(jsonObject.getString("status").equals("TOKEN_EXPIRED"))
                    {
                        AuthenticationController.logout(activity, true);
                    }
                }catch (JSONException jsonException){
                    showMessageDialog(activity,"Impossibile inserire difficoltà",null);
                }

            }

            @Override
            public void onError(String response) {
                showMessageDialog(activity,"Impossibile inserire difficoltà",null);
            }
        });

    }

    public static void getDifficultyById(Activity activity, int id, TextView textView, RequestQueue requestQueue){

        DifficultyAPI.getDifficultyById(activity,id,textView,AuthenticationController.accessToken, requestQueue);

    }


}
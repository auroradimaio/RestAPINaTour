package com.example.natour21.Controller;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.natour21.API.Waypoints.WaypointsAPI;
import com.example.natour21.Volley.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class WaypointsController {

    public static void insertWaypoints(Activity activity, double lat1, double lon1, double lat2, double lon2){

        WaypointsAPI.insertWaypoints(activity, lat1, lon1, lat2, lon2, AuthenticationController.accessToken, new VolleyCallback() {
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("OK")){
                        Log.i("WAYPOINTS","OK");
                    }else if(jsonObject.getString("status").equals("FAILED")){
                        Log.e("WAYPOINTS","FAILED");
                    }else if(jsonObject.getString("status").equals("TOKEN_EXPIRED"))
                    {
                        AuthenticationController.logout(activity, true);
                    }
                }catch (JSONException jsonException){
                    Log.e("WAYPOINTS","FAILED");
                }
            }

            @Override
            public void onError(String response) {
                Log.e("WAYPOINTS","FAILED");

            }
        });
    }

}

package com.example.natour21.API.Post;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.natour21.API.Config;
import com.example.natour21.Adapter.PostAdapter;
import com.example.natour21.Fragment.HomeFragment;
import com.example.natour21.Item.PostItem;
import com.example.natour21.Volley.VolleyCallback;
import com.example.natour21.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PostAPI {





    public static void getPosts(Activity activity,HomeFragment homeFragment, ArrayList<PostItem> mPostList, PostAdapter mPostAdapter, RecyclerView mRecyclerView, RequestQueue mRequestQueue, String accessToken){
        String url = Config.BASE_URL+Config.API+Config.POST;
        final PostAdapter[] mp = {mPostAdapter};
        mPostList.clear();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    double lat1=0,lat2=0,lon1=0,lon2=0;
                    JSONArray jsonArray = response.getJSONArray("result");

                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject res = jsonArray.getJSONObject(i);
                        try {

                            JSONObject way = res.getJSONObject("way");

                            lat1 = way.getDouble("lat1");
                            lat2 = way.getDouble("lat2");
                            lon1 = way.getDouble("lon1");
                            lon2 = way.getDouble("lon2");
                        }catch (JSONException je){

                        }


                        String title = res.getString("title");
                        String description = res.getString("description");
                        String startpoint = res.getString("startpoint");
                        String username = res.getString("username");


                        int id = res.getInt("id");


                        mPostList.add(new PostItem(description, title, lat1, lat2, lon1, lon2,id,startpoint,username));

                    }



                    mp[0] = new PostAdapter(activity,mPostList);
                    mRecyclerView.setAdapter(mp[0]);
                    mp[0].setOnItemClickListener(homeFragment);



                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        ){
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




        mRequestQueue.add(request);

    }






    public static void InsertPost(Activity activity, String title, String description, String startpoint,
                                  double lat1, double lng1, double lat2, double lng2, String duration,  Integer minutes,
                                  Integer difficulty, String username,String accessToken, VolleyCallback volleyCallback){

        String url = Config.BASE_URL+Config.API+Config.INSERTPOST;
        JSONObject jsonBody = new JSONObject();
        try{
            jsonBody.put("description",description);
            jsonBody.put("title",title);
            jsonBody.put("startpoint",startpoint);
            jsonBody.put("lat1", lat1);
            jsonBody.put("lng1", lng1);
            jsonBody.put("lat2", lat2);
            jsonBody.put("lng2", lng2);
            jsonBody.put("duration", duration);
            jsonBody.put("minutes",minutes);
            jsonBody.put("difficulty", difficulty);
            jsonBody.put("username",username);

        }catch (JSONException jsonException){
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
        ){
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
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + accessToken);
                return params;
            }

        };

        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }







}

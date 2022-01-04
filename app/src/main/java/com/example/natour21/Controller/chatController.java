package com.example.natour21.Controller;

import android.app.Activity;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import com.example.natour21.API.Message.MessageAPI;
import com.example.natour21.API.ChatRoom.ChatRoomAPI;
import com.example.natour21.Activity.SendMessage;
import com.example.natour21.Activity.SingleChat;
import com.example.natour21.Entity.ChatRoom;
import com.example.natour21.Entity.Message;
import com.example.natour21.Fragment.ChatListFragment;
import com.example.natour21.R;
import com.example.natour21.Volley.VolleyCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.natour21.Dialog.Dialog.showMessageDialog;

public class chatController {


    public static FragmentActivity chatListActivity;
    public static Activity singleChatActivity;
    public static boolean onChatList = false;
    public static String chattingWith;
    public static boolean onSingleChat = false;
    private static List<ChatRoom> chatRoomList = new ArrayList<ChatRoom>();
    private static List<Message> singleChat = new ArrayList<Message>();

    public static void getChatList() {

        chatRoomList.clear();

        ChatRoomAPI.getChatRooms(chatListActivity, authenticationController.user_username, authenticationController.accessToken, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("OK")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONArray current = (JSONArray) jsonArray.get(i);
                            chatRoomList.add(new ChatRoom((String) current.get(0),
                                    (String) current.get(1),
                                    (Long) current.get(2)));
                        }

                        ChatListFragment.updateUI(chatRoomList);
                    }else if(jsonObject.getString("status").equals("TOKEN_EXPIRED"))
                    {
                        authenticationController.logout(chatListActivity, true);
                    }

                } catch (JSONException jsonException) {
                    showMessageDialog(chatListActivity,"Impossibile collegarsi alla chat", null);
                }
            }

            @Override
            public void onError(String response) {
                showMessageDialog(chatListActivity,"Impossibile collegarsi alla chat", null);
            }
        });
    }

    public static void openSingleChat(Activity activity, String username){
        Intent intent = new Intent(activity, SingleChat.class);
        intent.putExtra("username", username);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public static void updateSingleChat(String from, String content, long time)
    {
        singleChatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                singleChat.add(0, new Message(from, content, time));
                SingleChat.updateUI(singleChat);
            }
        });
    }

    public static void getSingleChat(String email) {

        singleChat.clear();

        ChatRoomAPI.getSingleChat(singleChatActivity, authenticationController.user_username, email, authenticationController.accessToken, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("OK")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject current = (JSONObject) jsonArray.get(i);
                            singleChat.add(new Message(current.getString("sender"),
                                    current.getString("content"),
                                    current.getLong("time")));
                        }

                        SingleChat.updateUI(singleChat);
                    }else if(jsonObject.getString("status").equals("TOKEN_EXPIRED"))
                    {
                        authenticationController.logout(singleChatActivity, true);
                    }

                } catch (JSONException jsonException) {
                    showMessageDialog(singleChatActivity,"Impossibile collegarsi alla chat", null);
                }
            }

            @Override
            public void onError(String response) {
                showMessageDialog(singleChatActivity,"Impossibile collegarsi alla chat", null);
            }
        });

    }

    public static void sendMessage(String messageContent) {

        messageContent = messageContent.trim();
        if(messageContent.length() > 0) {
            MessageAPI.sendMessage(singleChatActivity, authenticationController.user_username, chattingWith, messageContent, authenticationController.accessToken, new VolleyCallback() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.getString("status").equals("OK")) {
                            updateSingleChat(jsonObject.getJSONObject("result").getString("from"),
                                    jsonObject.getJSONObject("result").getString("content"),
                                    jsonObject.getJSONObject("result").getLong("time"));
                        }else if(jsonObject.getString("status").equals("TOKEN_EXPIRED"))
                        {
                            authenticationController.logout(chatListActivity, true);
                        }

                    } catch (JSONException jsonException) {
                        showMessageDialog(singleChatActivity, "Impossibile inviare il messaggio", null);
                    }
                }

                @Override
                public void onError(String response) {
                    showMessageDialog(singleChatActivity, "Impossibile inviare il messaggio", null);
                }
            });
        }
    }

    public static void openNewMessage(Activity activity) {
        activity.startActivity(new Intent(activity, SendMessage.class));
        activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public static void sendNewMessage(Activity activity, String username, String messageContent) {

        if(username.length() > 0) {
            if (!username.equals(authenticationController.user_username)) {
                messageContent = messageContent.trim();
                if(messageContent.length() > 0 && !messageContent.equals("")) {
                    MessageAPI.sendMessage(activity, authenticationController.user_username, username, messageContent, authenticationController.accessToken, new VolleyCallback() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                if (jsonObject.getString("status").equals("OK")) {
                                    activity.finish();
                                } else if (jsonObject.getString("status").equals("FAILED")) {
                                    showMessageDialog(activity, "Impossibile trovare il nome utente", null);
                                }else if(jsonObject.getString("status").equals("TOKEN_EXPIRED"))
                                {
                                    authenticationController.logout(chatListActivity, true);
                                }

                            } catch (JSONException jsonException) {
                                showMessageDialog(activity, "Impossibile inviare il messaggio", null);
                            }
                        }

                        @Override
                        public void onError(String response) {
                            showMessageDialog(activity, "Impossibile inviare il messaggio", null);
                        }
                    });
                }else
                {
                    showMessageDialog(activity, "Inserire un messaggio", null);
                }
            } else {
                showMessageDialog(activity, "Non puoi inviare messaggi a te stesso", null);
            }
        }else
        {
            showMessageDialog(activity, "Inserire un nome utente", null);
        }
    }
}

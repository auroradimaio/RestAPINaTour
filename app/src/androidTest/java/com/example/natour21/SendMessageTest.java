package com.example.natour21;

import android.app.Activity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import com.example.natour21.API.Message.MessageAPI;
import com.example.natour21.Activity.SendMessage;
import com.example.natour21.Volley.VolleyCallback;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SendMessageTest {

    @Rule
    public ActivityTestRule sendMessageRule = new ActivityTestRule<>(SendMessage.class);

    public Activity sendMessage = null;

    String tokenInvalido = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWtlLmZvbnNldGEiLCJyb2xlIjpbIlJPTEVfQURNSU4iXSwiaXNzIjoiaHR0cDovLzE5Mi4xNjguMS45OjgwODAvYXBpL2xvZ2luIiwiZXhwIjoxNjQzMzY4Mzc0fQ.c6s5p5ciQpqdBxzMowcN8-rMaOzikqWXNvzkQzx2jlk";
    String tokenValido = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWtlLmZvbnNldGEiLCJyb2xlIjpbIlJPTEVfQURNSU4iXSwiaXNzIjoiaHR0cDovLzE5Mi4xNjguMS45OjgwODAvYXBpL2xvZ2luIiwiZXhwIjoxNjc3NTgxMjE2fQ.sB9Dk4jsaGaSM4Z5iM8VdyhYsYGESRDdUbRtMUYEGUk";
    String chattingWithInvalido = "alessia17";
    String chattingWithValido = "mike13";
    String messageInvalido = null;
    String usernameNull = null;
    String messageValido = "Ciao";
    String usernameValido = "mike.fonseta";
    String usernameNonRegistrato = "alessia17";
    String tokenNull = null;
    String chattingWithNull = null;

    @Before
    public void setup(){
        sendMessage = sendMessageRule.getActivity();
    }

    @Test
    public void TokenExpired()
    {
        MessageAPI.sendMessage(sendMessage, usernameValido, chattingWithValido, messageValido,tokenInvalido, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                assertEquals("{\"result\":\"Access token scaduto\",\"status\":\"TOKEN_EXPIRED\"}", response);
            }

            @Override
            public void onError(String response) {

            }
        });
    }

    @Test
    public void UsernameNotFound()
    {
        MessageAPI.sendMessage(sendMessage, usernameNonRegistrato, chattingWithValido, messageValido,tokenValido, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                assertEquals("\"status\":\"FAILED\"}", response.substring(response.length()-18));
            }

            @Override
            public void onError(String response) {

            }
        });
    }

    @Test
    public void ChattingWithNotFound()
    {
        MessageAPI.sendMessage(sendMessage, usernameValido, chattingWithInvalido, messageValido,tokenValido, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                assertEquals("\"status\":\"FAILED\"}", response.substring(response.length()-18));
            }

            @Override
            public void onError(String response) {

            }
        });
    }

    @Test
    public void ValidInsert()
    {
        MessageAPI.sendMessage(sendMessage, usernameValido, chattingWithValido, messageValido,tokenValido, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                assertEquals("\"status\":\"OK\"}", response.substring(response.length()-14));
            }

            @Override
            public void onError(String response) {

            }
        });
    }

    @Test
    public void InvalidInsertNoUsername()
    {
        MessageAPI.sendMessage(sendMessage, usernameNull, chattingWithNull, messageValido,tokenValido, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                //assertEquals("\"status\":\"FAILED\"}", response.substring(response.length()-18));
            }

            @Override
            public void onError(String response) {
                assertNotNull(response);
            }
        });
    }

    @Test
    public void InvalidInsertNoChattingWith()
    {
        MessageAPI.sendMessage(sendMessage, usernameValido, chattingWithNull, messageValido,tokenValido, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                assertEquals("\"status\":\"FAILED\"}", response.substring(response.length()-18));
            }

            @Override
            public void onError(String response) {
            }
        });

    }

    @Test
    public void InvalidInsertNoMessageContent()
    {
        MessageAPI.sendMessage(sendMessage, usernameValido, chattingWithValido, messageInvalido,tokenValido, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                assertEquals("\"status\":\"FAILED\"}", response.substring(response.length()-18));
            }

            @Override
            public void onError(String response) {

            }
        });
    }

    @Test
    public void InvalidInsertNoAccessToken()
    {
        MessageAPI.sendMessage(sendMessage, usernameValido, chattingWithValido, messageValido,tokenNull, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                assertEquals("\"status\":\"FAILED\"}", response.substring(response.length()-18));
            }

            @Override
            public void onError(String response) {

            }
        });
    }

    @Test
    public void InvalidInsertSelfMessage()
    {
        MessageAPI.sendMessage(sendMessage, usernameValido, usernameValido, messageValido,tokenValido, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                assertEquals("\"status\":\"FAILED\"}", response.substring(response.length()-18));
            }

            @Override
            public void onError(String response) {

            }
        });
    }

    @After
    public void tearDown(){
        sendMessage = null;
    }

}

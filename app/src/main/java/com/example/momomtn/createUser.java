package com.example.momomtn;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class createUser {

    public static int userCreation(String PRIMARY_KEY, String CONTENT_TYPE, String UUID) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"providerCallbackHost\": \"localhost:8000\"\r\n}");
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/v1_0/apiuser")
                .method("POST", body)
                .addHeader("X-Reference-Id", UUID)
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("Ocp-Apim-Subscription-Key", PRIMARY_KEY)
                .build();
        Response response = client.newCall(request).execute();

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Creation of User Response Code = ".concat(String.valueOf(response.code())));
        System.out.println("-----------------------------------------------------------------------------");

        return response.code();

    }
}

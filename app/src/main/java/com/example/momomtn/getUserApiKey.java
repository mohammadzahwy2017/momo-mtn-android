package com.example.momomtn;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class getUserApiKey {
    public String userApiKey(String PRIMARY_KEY, String CONTENT_TYPE, String UUID) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse(CONTENT_TYPE);
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/v1_0/apiuser/".concat(UUID).concat("/apikey"))
                .method("POST", body)
                .addHeader("Ocp-Apim-Subscription-Key", PRIMARY_KEY)
                .addHeader("Content-Type", CONTENT_TYPE)
                .build();
        Response response = client.newCall(request).execute();
        //Get Response Body
        //Get Response Body
        String tmp = response.body().string();

        //Convert Response to JSON Object
        JSONObject object = new JSONObject(tmp);

        //Get attributes from response body
        String API_KEY = object.getString("apiKey");

        String code = String.valueOf(response.code());
        getUserInformation getUserInformation = new getUserInformation();
        System.out.println("Get User API Key Response");
        System.out.println("------------------------------");
        System.out.println("API_KEY = ".concat(API_KEY));
        System.out.println("Response code = ".concat(code));
        System.out.println("------------------------------");

        return API_KEY;
    }


}

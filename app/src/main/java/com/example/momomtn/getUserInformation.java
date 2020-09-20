package com.example.momomtn;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getUserInformation {

    public static String userInformation(String PRIMARY_KEY,String CONTENT_TYPE,String UUID) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/v1_0/apiuser/".concat(UUID))
                .method("GET", null)
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("Ocp-Apim-Subscription-Key", PRIMARY_KEY)
                .build();
        Response response = client.newCall(request).execute();
        JSONObject object = new JSONObject(response.body().string());

        //Get attributes from response body
        String providerCallbackHost = object.getString("providerCallbackHost");
        String targetEnvironment = object.getString("targetEnvironment");

        System.out.println("Get User Information Response");
        System.out.println("------------------------------");
        System.out.println("providerCallbackHost = ".concat(providerCallbackHost));
        System.out.println("targetEnvironment = ".concat(targetEnvironment));
        System.out.println("------------------------------");

        return targetEnvironment;

    }
}

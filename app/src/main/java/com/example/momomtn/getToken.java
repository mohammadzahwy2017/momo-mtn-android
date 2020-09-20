package com.example.momomtn;// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class getToken extends MainActivity {
    public static String theToken(String PRIMARY_KEY, String AUTH_KEY) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/collection/token/")
                .method("POST", body)
                .addHeader("Ocp-Apim-Subscription-Key", PRIMARY_KEY)
                .addHeader("Authorization", "Basic ".concat(AUTH_KEY).concat(""))
                .build();
        Response response = client.newCall(request).execute();

        //Get Response Body
        String tmp = response.body().string();

        //Convert Response to JSON Object
        JSONObject object = new JSONObject(tmp);

        //Get attributes from response body
        String TOKEN = object.getString("access_token");
        String token_type = object.getString("token_type");
        String expires_in = object.getString("expires_in");

        //Get returned status code
        String status = String.valueOf(response.code());
        System.out.println("Get User Token Response");
        System.out.println("------------------------------");
        System.out.println("Response Code = " + status);
        System.out.println("Access Token = " + TOKEN);
        System.out.println("Token Type = " + token_type);
        System.out.println("Expires In = " + expires_in);
        System.out.println("------------------------------");

        if (status.equals("200"))
            return TOKEN;
        if(status.equals("401"))
            return "UnAuthorized";
        if(status.equals("500"))
            return "INTERNAL_SERVER_ERROR";
        return "ERROR";
    }
}
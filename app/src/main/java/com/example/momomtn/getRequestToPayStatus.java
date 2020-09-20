package com.example.momomtn;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getRequestToPayStatus {

    public static String getStatus(String PRIMARY_KEY, String CONTENT_TYPE, String TARGET_ENVIRONMENT, String UUID, String USER_TOKEN) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay/".concat(UUID))
                .method("GET", null)
                .addHeader("Authorization", "Bearer ".concat(USER_TOKEN))
                .addHeader("X-Target-Environment", TARGET_ENVIRONMENT)
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("Ocp-Apim-Subscription-Key", PRIMARY_KEY)
                .build();
        Response response = client.newCall(request).execute();

        JSONObject object = new JSONObject(response.body().string());
        //Get attributes from response body
        String externalId = object.getString("externalId");
        String amount = object.getString("amount");
        String currency = object.getString("currency");
        String payerMessage = object.getString("payerMessage");
        String payeeNote = object.getString("payeeNote");
        String status = object.getString("status");
        String payer = object.getString("payer");

        System.out.println("Get Request to pay Status:");
        System.out.println("------------------------------");
        System.out.println("Response Code = " + response.code());
        System.out.println("Status = " + status);
        System.out.println("externalId = " + externalId);
        System.out.println("amount = " + amount);
        System.out.println("currency = " + currency);
        System.out.println("payerMessage = " + payerMessage);
        System.out.println("payeeNote = " + payeeNote);
        if (object.has("reason") ) {
                String reason = object.getString("reason");
                System.out.println("reason = " + reason);
        }
        System.out.println("payer = " + payer);
        System.out.println("------------------------------");

        return ("Response Code = " + status + "externalId = " + externalId + "amount = " + amount + "currency = " + currency + "payer = " + payer);
    }
}

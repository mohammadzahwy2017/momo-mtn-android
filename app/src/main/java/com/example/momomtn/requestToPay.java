package com.example.momomtn;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class requestToPay extends getToken {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static int getRequestToPay(String PRIMARY_KEY, String CONTENT_TYPE, String UUID, String USER_TOKEN, String TARGET_ENVIRONMENT,String AMOUNT,String PHONE) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse(CONTENT_TYPE);
        RequestBody body = RequestBody.create(mediaType,
                "{\r\n  \"amount\": \""
                        +AMOUNT+"\",\r\n  \"currency\": \"EUR\",\r\n  \"externalId\": \"15977684\",\r\n  \"payer\": {\r\n    \"partyIdType\": \"MSISDN\",\r\n    \"partyId\": \""
                        +PHONE+"\"\r\n  },\r\n  \"payerMessage\": \"Please Note\",\r\n  \"payeeNote\": \"Confirm To Pay\"\r\n}");
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay")
                .method("POST", body)
                .addHeader("Authorization", "Bearer ".concat(USER_TOKEN))
                .addHeader("X-Reference-Id", UUID)
                .addHeader("X-Target-Environment", TARGET_ENVIRONMENT)
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("Ocp-Apim-Subscription-Key", PRIMARY_KEY)
                .build();

        Response response = client.newCall(request).execute();

        //Get returned status code
        String status2 = String.valueOf(response.code());
        System.out.println("Get Request To Pay Response");
        System.out.println("------------------------------");
        System.out.println("Response Code = " + status2);
        System.out.println("------------------------------");

        if(response.code() == 409){
            JSONObject object = new JSONObject(response.body().string());

            System.out.println("Code = " + object.getString("code"));
            System.out.println("Message = " + object.getString("message"));

        }
        return response.code();
    }
}

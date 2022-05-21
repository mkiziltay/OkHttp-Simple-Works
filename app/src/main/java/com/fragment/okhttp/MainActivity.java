package com.fragment.okhttp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.fragment.okhttp.Fragments.CryptosFrgmnt;
import com.fragment.okhttp.Fragments.CurrenciesFrgmnt;
import com.fragment.okhttp.Model.Crypto;
import com.fragment.okhttp.Model.Currency;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Currency> currencyList = new ArrayList();
    public ArrayList<Crypto> cryptoList = new ArrayList<>();
    JSONObject respObj;
    TextView currencyText,cryptoText,goldText;
    private  OkHttpClient client = new OkHttpClient();
    private final String BASE_URL = "https://finans.apipara.com/json/v9//converter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        listeners();
        callClientResponse();
    }

    void callClientResponse(){
        Request get = new Request.Builder()
                .url(BASE_URL)
                .build();

        client.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.i("data", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    respObj = jsonObject.getJSONObject("response");
                    Log.i("datas",respObj.length()+"");
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Log.i("data", "RESPONSE BODY LOADED");
                    showCurrencies();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showCurrencies() {
        getCurrencyList(respObj);
        CurrenciesFrgmnt.setData(currencyList);
        CurrenciesFrgmnt currenciesFrgmnt = new CurrenciesFrgmnt();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentBox,currenciesFrgmnt).commit();
        changeTextColor(currencyText);
    }

    private void showCryptos() {
        getCryptoList(respObj);
        CryptosFrgmnt.setData(cryptoList);
        CryptosFrgmnt cryptosFrgmnt = new CryptosFrgmnt();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentBox,cryptosFrgmnt).commit();
        changeTextColor(cryptoText);
    }

    private void showGolds() {
        changeTextColor(goldText);
        Toast.makeText(getApplicationContext(), "This Fragment not added",Toast.LENGTH_SHORT).show();
    }

    void changeTextColor(TextView textView){
        switch (textView.getId()){
            case R.id.curr_tv: currencyText.setTextColor(Color.BLUE);
                                cryptoText.setTextColor(Color.BLACK);
                                goldText.setTextColor(Color.BLACK);
            break;
            case R.id.cryp_tv: cryptoText.setTextColor(Color.BLUE);
                currencyText.setTextColor(Color.BLACK);
                goldText.setTextColor(Color.BLACK);
                break;
            case R.id.gold_tv: goldText.setTextColor(Color.BLUE);
                cryptoText.setTextColor(Color.BLACK);
                currencyText.setTextColor(Color.BLACK);
                break;
        }
    }

    void listeners(){
        currencyText = findViewById(R.id.curr_tv);
        cryptoText = findViewById(R.id.cryp_tv);
        goldText = findViewById(R.id.gold_tv);


        currencyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCurrencies();
            }
        });

        cryptoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { showCryptos(); }
        });

        goldText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGolds();
            }
        });
    }

    void showListWithDelay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("data", "DATA SENT TO FRAGMENT....");
                showCurrencies();
            }
        }, 4000); // After 3 seconds
    }

    ArrayList<Currency> getCurrencyList(JSONObject responseObj){
        JSONArray currencies = null;
        try {
            currencies = responseObj.getJSONArray("currency");
            for (int i = 0; i < currencies.length() - 1; i++) {
                JSONObject item = currencies.getJSONObject(i);
                currencyList.add(new Currency(i,
                        item.getString("code"),
                        item.getDouble("buying"),
                        item.getDouble("selling"),
                        item.getDouble("change_rate")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return currencyList;
    }

    ArrayList<Crypto> getCryptoList(JSONObject responseObj){
        JSONArray cryptos = null;
        try {
            cryptos = responseObj.getJSONArray("coin");
            for (int i = 0; i < cryptos.length() - 1; i++) {
                JSONObject item = cryptos.getJSONObject(i);
                cryptoList.add(new Crypto(i,
                        item.getString("code"),
                        item.getDouble("buying"),
                        item.getDouble("selling"),
                        item.getDouble("change_rate")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cryptoList;
    }
}
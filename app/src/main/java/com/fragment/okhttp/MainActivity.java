package com.fragment.okhttp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fragment.okhttp.Adapters.MyAdapter;
import com.fragment.okhttp.Fragments.CryptosFrgmnt;
import com.fragment.okhttp.Fragments.CurrenciesFrgmnt;
import com.fragment.okhttp.Model.Crypto;
import com.fragment.okhttp.Model.Currency;
import com.fragment.okhttp.Model.Money;

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

    public ArrayList<Money> currencyList = new ArrayList();
    public ArrayList<Money> cryptoList = new ArrayList<>();
    JSONObject respObj;
    //ListView listView;
    //MyAdapter myadapter;
    TextView currencyText,cryptoText,goldText;
    private  OkHttpClient client = new OkHttpClient();
    private final String BASE_URL = "http://mkiziltay.epizy.com/jsonsample.php?i=1"; // throwing SSL Error. if u use api source with https project works.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        listeners();

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
                    //JSONArray currencies = respObj.getJSONArray("currency");
                    //getCurrencyList(respObj);
                    //Log.i("data", currencies.length() + "");
/*
                    for (int i = 0; i < currencies.length() - 1; i++) {
                        JSONObject item = currencies.getJSONObject(i);
//                        String currencyName = item.getString("code");
//                        String currencyDesc = item.getString("shortName");
//                        Double currencyValue = item.getDouble("buying");
//                        Double cellRatio = item.getDouble("cell");
//                        System.out.println("id: "+i+" : "+currencyName+" : "+currencyDesc+" : "+currencyValue+" : "+cellRatio);
                        Log.i("data", item.getString("code") + " : " + item.getString("buying") + " : " + item.getString("selling") + " : " + item.getString("change_rate"));
                        currencyList.add(new Currency(i,
                                item.getString("code"),
                                item.getDouble("buying"),
                                item.getDouble("selling"),
                                item.getDouble("change_rate")
                        ));
                    }
*/
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    //Log.i("data", responseBody.string());
                    Log.i("data", "RESPONSE BODY");
                    //Log.i("data", currencyList.get(1).getCurrencyName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        showList();
    }

    private void showCurrencies() {
        getCurrencyList(respObj);
        CurrenciesFrgmnt.setData(currencyList);
        CurrenciesFrgmnt currenciesFrgmnt = new CurrenciesFrgmnt();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(R.id.fragmentBox,currenciesFrgmnt).commit();
        transaction.replace(R.id.fragmentBox,currenciesFrgmnt).commit();
        changeTextColor(currencyText);
    }

    private void showCryptos() {
        getCryptoList(respObj);
        CryptosFrgmnt.setData(cryptoList);
        CryptosFrgmnt cryptosFrgmnt = new CryptosFrgmnt();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(R.id.fragmentBox,cryptosFrgmnt).commit();
        transaction.replace(R.id.fragmentBox,cryptosFrgmnt).commit();
        changeTextColor(cryptoText);
    }

    private void showGolds() {
        changeTextColor(goldText);
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

/*
    public void configureListView() {
        //listView=findViewById(R.id.listview);
        myadapter = new MyAdapter(MainActivity.this, currencyList);
        listView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
        Log.i("data", "LIST VIEW HAS BEEN BUILT RIGHT NOW");
    }
*/
    void showList() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //configureListView();
                Log.i("data", "DATA SENT TO FRAGMENT....");
                //showCurrencies();
                //Log.i("data", currencyList.get(0).getCurrencyName());
                showCryptos();
            }
        }, 5000); // After 3 seconds
    }

    ArrayList<Money> getCurrencyList(JSONObject responseObj){
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

    ArrayList<Money> getCryptoList(JSONObject responseObj){
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

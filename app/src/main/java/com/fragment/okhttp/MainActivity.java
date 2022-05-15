package com.fragment.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.fragment.okhttp.Adapters.MyAdapter;
import com.fragment.okhttp.Model.Currency;
import org.json.JSONArray;
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

    public ArrayList<Currency> currencyList = new ArrayList<>();
    ListView listView;
    MyAdapter myadapter;
    private  OkHttpClient client = new OkHttpClient();
    private final String BASE_URL = "https://finans.apipara.com/json/v9//converter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                    JSONObject respObj = jsonObject.getJSONObject("response");
                    JSONArray currencies = respObj.getJSONArray("currency");
                    Log.i("data", currencies.length() + "");

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

                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    //Log.i("data", responseBody.string());
                    Log.i("data", "RESPONSE BODY");
                    Log.i("data", currencyList.get(1).getCurrencyName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        showList();
    }


    public void configureListView() {
        listView=findViewById(R.id.listview);
        myadapter = new MyAdapter(MainActivity.this, currencyList);
        listView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
        Log.i("data", "LIST VIEW HAS BEEN BUILT RIGHT NOW");
    }

    void showList() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                configureListView();
            }
        }, 3000); // After 3 seconds
    }

}
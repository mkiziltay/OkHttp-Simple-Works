package com.fragment.okhttp.Fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.fragment.okhttp.Adapters.MoneyAdapter;
import com.fragment.okhttp.Model.Crypto;
import com.fragment.okhttp.R;
import java.util.ArrayList;

public class CryptosFrgmnt extends Fragment {
    private static ArrayList<Crypto> cryptoList;
    View frgView;
    ListView listView;
    MoneyAdapter moneyAdapter;
    /*
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public CurrenciesFrgmnt() {
        }

        public static CurrenciesFrgmnt newInstance(String param1, String param2) {
            CurrenciesFrgmnt fragment = new CurrenciesFrgmnt();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }

        }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frgView = inflater.inflate(R.layout.fragment_cryptos, container, false);

        configureListView();
        return frgView;
    }

    public static void setData(ArrayList<Crypto> arrayList){
        cryptoList = arrayList;
    }

    public ArrayList<Crypto> getData(){
        return cryptoList;
    }

    public void configureListView() {
        listView=frgView.findViewById(R.id.listview2);
        moneyAdapter = new MoneyAdapter(getActivity().getApplicationContext(), getData());
        listView.setAdapter(moneyAdapter);
        moneyAdapter.notifyDataSetChanged();
        Log.i("data", "LIST VIEW HAS BEEN BUILT FOR CRYPTOS RIGHT NOW");
        Log.i("data", getData().get(4).getCurrencyName());
    }
}
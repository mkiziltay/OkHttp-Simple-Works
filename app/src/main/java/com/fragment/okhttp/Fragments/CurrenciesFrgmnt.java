package com.fragment.okhttp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.fragment.okhttp.Adapters.MyAdapter;
import com.fragment.okhttp.MainActivity;
import com.fragment.okhttp.Model.Currency;
import com.fragment.okhttp.Model.Money;
import com.fragment.okhttp.R;

import java.util.ArrayList;

public class CurrenciesFrgmnt extends Fragment {
    private static ArrayList<Money> currencyList;
    View frgView;
    ListView listView;
    MyAdapter myAdapter;
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
        frgView = inflater.inflate(R.layout.fragment_currencies, container, false);

        configureListView();
        //container.addView(listView);
        return frgView;
    }

    public static void setData(ArrayList<Money> arrayList){
        //currencyList = new ArrayList<>();
        currencyList = arrayList;
    }

    public ArrayList<Money> getData(){
        return currencyList;
    }

    public void configureListView() {
        listView=frgView.findViewById(R.id.listview);
        myAdapter = new MyAdapter(getActivity().getApplicationContext(), getData());
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        Log.i("data", "LIST VIEW HAS BEEN BUILT RIGHT NOW");
        Log.i("data", getData().get(4).getCurrencyName());
    }
}
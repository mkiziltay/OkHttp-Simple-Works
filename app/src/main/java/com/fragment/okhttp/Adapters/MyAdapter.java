package com.fragment.okhttp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.fragment.okhttp.Model.Currency;
import com.fragment.okhttp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Currency> currList;

    public MyAdapter(Context context, ArrayList<Currency> currList) {
        this.context = context;
        this.currList = currList;
    }

    @Override
    public int getCount() {
        return currList.size();
    }

    @Override
    public Object getItem(int position) {
        return currList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_layout ,parent,false);

        TextView title,buying,selling,changing;
        title = convertView.findViewById(R.id.title);
        buying = convertView.findViewById(R.id.buying);
        selling = convertView.findViewById(R.id.selling);
        changing = convertView.findViewById(R.id.changing);

        title.setText(currList.get(position).getCurrencyName());
        buying.setText(new DecimalFormat("#0.0000").format(currList.get(position).getCurrencyBuy()));
        selling.setText(new DecimalFormat("#0.0000").format(currList.get(position).getCurrencySelling()));
//        changing.setText(currList.get(position).getGetCurrencyChange().toString());
        changing.setText(new DecimalFormat("#0.0000").format(currList.get(position).getGetCurrencyChange()));

        if (currList.get(position).getGetCurrencyChange()<0){
            changing.setTextColor(Color.RED);
            buying.setTextColor(Color.RED);
            selling.setTextColor(Color.RED);
            title.setTextColor(Color.RED);
        }


        listeners(buying, title, position);
        return convertView;
    }

    void listeners(View view, TextView value, int pos){

    }
}

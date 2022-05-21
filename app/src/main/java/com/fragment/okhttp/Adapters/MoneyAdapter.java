package com.fragment.okhttp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.fragment.okhttp.Model.Money;
import com.fragment.okhttp.R;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MoneyAdapter extends BaseAdapter {

    Context context;
    ArrayList<? extends Money> currList;

    public MoneyAdapter(Context context, ArrayList<? extends Money> currList) {
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
        buying.setText(formatValue(currList.get(position).getCurrencyBuy()));
        selling.setText(formatValue(currList.get(position).getCurrencySelling()));
        changing.setText(formatValue(currList.get(position).getGetCurrencyChange()));

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

    String formatValue(Double value){
        String newValue="1.0";
        if(value<1000){
            newValue = new DecimalFormat("#0.0000").format(value);
        }else if(value<100000){
            newValue = new DecimalFormat("#0.00").format(value);
        }else{
            newValue = new DecimalFormat("#0").format(value);
        }

        return newValue;
    }
}

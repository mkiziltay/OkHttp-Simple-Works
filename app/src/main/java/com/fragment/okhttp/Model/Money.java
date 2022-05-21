package com.fragment.okhttp.Model;

public abstract class Money {
    private int id;
    private String currencyName;
    private Double currencyBuy;
    private Double currencySelling;
    private Double getCurrencyChange;

    abstract int getId();

    abstract void setId(int id);

    public abstract String getCurrencyName();

    abstract void setCurrencyName(String currencyName);

    public abstract Double getCurrencyBuy();

    abstract void setCurrencyBuy(Double currencyBuy);

    public abstract Double getCurrencySelling();

    abstract void setCurrencySelling(Double currencySelling);

    public abstract Double getGetCurrencyChange();

    abstract void setGetCurrencyChange(Double getCurrencyChange);
}

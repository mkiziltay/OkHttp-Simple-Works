package com.fragment.okhttp.Model;

public abstract class Money {
    private int id;
    private String currencyName;
    private Double currencyBuy;
    private Double currencySelling;
    private Double getCurrencyChange;

//    public Money(int id, String currencyName, Double currencyBuy, Double currencySelling, Double getCurrencyChange) {
//        this.id = id;
//        this.currencyName = currencyName;
//        this.currencyBuy = currencyBuy;
//        this.currencySelling = currencySelling;
//        this.getCurrencyChange = getCurrencyChange;
//    }

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

package com.fragment.okhttp.Model;

public class Currency {
    private int id;
    private String currencyName;
    private Double currencyBuy;
    private Double currencySelling;
    private Double getCurrencyChange;

    public Currency(int id, String currencyName, Double currencyBuy, Double currencySelling, Double getCurrencyChange) {
        this.id = id;
        this.currencyName = currencyName;
        this.currencyBuy = currencyBuy;
        this.currencySelling = currencySelling;
        this.getCurrencyChange = getCurrencyChange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getCurrencyBuy() {
        return currencyBuy;
    }

    public void setCurrencyBuy(Double currencyBuy) {
        this.currencyBuy = currencyBuy;
    }

    public Double getCurrencySelling() {
        return currencySelling;
    }

    public void setCurrencySelling(Double currencySelling) {
        this.currencySelling = currencySelling;
    }

    public Double getGetCurrencyChange() {
        return getCurrencyChange;
    }

    public void setGetCurrencyChange(Double getCurrencyChange) {
        this.getCurrencyChange = getCurrencyChange;
    }
}

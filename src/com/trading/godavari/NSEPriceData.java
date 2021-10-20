package com.trading.godavari;

import java.math.BigDecimal;

public class NSEPriceData {

    public String symbol;
    public String date;
    public String expiry;
    public String optionType;
    public Long strikePrice;
    public BigDecimal open;
    public BigDecimal high;
    public BigDecimal low;
    public BigDecimal close;

    public NSEPriceData(String symbol, String date, String expiry, String optionType, Long strikePrice, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close) {
        this.symbol = symbol;
        this.date = date;
        this.expiry = expiry;
        this.optionType = optionType;
        this.strikePrice = strikePrice;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDate() {
        return date;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getOptionType() {
        return optionType;
    }

    public Long getStrikePrice() {
        return strikePrice;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public void setStrikePrice(Long strikePrice) {
        this.strikePrice = strikePrice;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public NSEPriceData() {
    }
}

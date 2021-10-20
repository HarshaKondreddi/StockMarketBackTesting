package com.trading.godavari;


import java.math.BigDecimal;
public class IndexData {

    public String date;
    public BigDecimal open;
    public BigDecimal high;
    public BigDecimal low;
    public BigDecimal close;
    public BigDecimal sharesTraded;
    public BigDecimal turnOver;
    public String dayName;

    public String getDate() {
        return date;
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

    public BigDecimal getSharesTraded() {
        return sharesTraded;
    }

    public BigDecimal getTurnOver() {
        return turnOver;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDate(String date) {
        this.date = date;
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

    public void setSharesTraded(BigDecimal sharesTraded) {
        this.sharesTraded = sharesTraded;
    }

    public void setTurnOver(BigDecimal turnOver) {
        this.turnOver = turnOver;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public IndexData(String date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal sharesTraded, BigDecimal turnOver, String dayName) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.sharesTraded = sharesTraded;
        this.turnOver = turnOver;
        this.dayName = dayName;
    }

    public IndexData() {
    }
}

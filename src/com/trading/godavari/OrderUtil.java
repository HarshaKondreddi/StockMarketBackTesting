package com.trading.godavari;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OrderUtil {

    public static final Collector<NSEPriceData, ?, Map<String, Map<String, Map<Long, List<NSEPriceData>>>>> collector = Collectors.groupingBy(NSEPriceData::getDate, Collectors.groupingBy(NSEPriceData::getExpiry,
            Collectors.groupingBy(NSEPriceData::getStrikePrice)));
    public static Map<String, Map<String, Map<Long, List<NSEPriceData>>>> PREMIUM_DATA;
    public static final BigDecimal THOUSAND = new BigDecimal(1000);
    public static List<Long> STRIKE_PRICES = new ArrayList<>();
    public static Map<Long, BigDecimal> CALL_ORDERS =  new HashMap<>();
    public static Map<Long, BigDecimal> PUT_ORDERS = new HashMap<>();
    public static String[] DAYS = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

    public  static void placeOrder(String date, BigDecimal spotPrice, Long range) {
        if(!getDayOfTheTrade(date).equals(DAYS[4])) {
            return;
        }
        Long suitableSpotPrice = spotPrice.divide(THOUSAND).setScale(2, RoundingMode.HALF_UP).multiply(THOUSAND).longValue();
        Long callPremium = getPremium(date, suitableSpotPrice+range);
        Long putStrikePrice = getPremium(date, suitableSpotPrice-range);
        CALL_ORDERS.put(suitableSpotPrice+range, new BigDecimal(callPremium));
        PUT_ORDERS.put(suitableSpotPrice-range, new BigDecimal(putStrikePrice));
    }

    private static Long getPremium(String date, Long premium) {
        return null;
    }


    public static void setAllStrikePrices() throws IOException {
        NSEPriceDataReader nsePriceDataReader = new NSEPriceDataReader("/Users/upstox/Downloads/OPTIDX_BANKNIFTY_PE_15-10-2020_TO_14-10-2021.csv", true);
        List<NSEPriceData> nsePriceData = nsePriceDataReader.read();
        Map<Long, List<NSEPriceData>> strikePriceGroupedData = nsePriceData.stream().collect(Collectors.groupingBy(NSEPriceData::getStrikePrice));
        STRIKE_PRICES.addAll(strikePriceGroupedData.keySet().stream().sorted().collect(Collectors.toList()));
    }

    public static void setPremiumData()  throws IOException {
        NSEPriceDataReader nsePriceDataReader = new NSEPriceDataReader("/Users/upstox/Downloads/OPTIDX_BANKNIFTY_PE_15-10-2020_TO_14-10-2021.csv", true);
        List<NSEPriceData> nsePriceData = nsePriceDataReader.read();
        PREMIUM_DATA = nsePriceData.stream().collect(collector);
    }

    private static String getDayOfTheTrade(String dateDetail) {
        String[] dateDetails = dateDetail.split("-");
        String year = dateDetails[2];
        String date = dateDetails[0];
        String month = getMonth(dateDetails[1]);
        String dayOfTrade = getNameOfDay(year, date, month);
        return dayOfTrade;
    }

    private static String getNameOfDay(String year, String date, String month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.valueOf(year), Integer.valueOf(month).intValue()-1, Integer.valueOf(date));
        return DAYS[calendar.get(Calendar.DAY_OF_WEEK)-1];
    }

    private static String getMonth(String dateDetail) {
        return dateDetail;
    }
}

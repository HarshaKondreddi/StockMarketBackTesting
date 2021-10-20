package com.trading.godavari;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

public class BackTestingAPI {

    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";
    public static String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };


    public static void main(String[] args) throws IOException {
        printSuccessData();
//        getProfitAndLoss();
    }

    private static void getProfitAndLoss() throws IOException {
        List<IndexData> indexDataList = getIndexData();
        for(IndexData d : indexDataList) {
            String dayOfTrade = getDayOfTheTrade(d);
            d.setDayName(dayOfTrade);
        }
        OrderUtil.setAllStrikePrices();
        for(IndexData indexData : indexDataList) {
            if(indexData.getDayName().equals(days[4])) {
                OrderUtil.placeOrder(indexData.getDate(), indexData.getClose(), 800L);
            }
        }
    }

    private static void printSuccessData() throws IOException {
        Map<Integer,Map<String, String>> successMap = new HashMap<>();
        for(int i=100; i<=2500; i=i+100) {
            successMap.put(i, getSuccessData(new BigDecimal(i)));
        }
        System.out.println("########################################################################################");
        System.out.println(successMap);
        for(Integer range : successMap.keySet().stream().sorted().collect(Collectors.toList())) {
            Map<String, String> rangeSuccessDetail = successMap.get(range);
            int successCount = 0;
            int len = rangeSuccessDetail.keySet().size();
            for(String key : rangeSuccessDetail.keySet()) {
                if(rangeSuccessDetail.get(key).equals(SUCCESS)) {
                    successCount++;
                }
            }
            System.out.println("########################################################################################");
            System.out.println("Range : " + range + " Success rate: " + successCount*100/len);
        }
    }

    private static String getNameOfDay(String year, String date, String month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.valueOf(year), Integer.valueOf(month).intValue()-1, Integer.valueOf(date));
        return days[calendar.get(Calendar.DAY_OF_WEEK)-1];
    }

    private static String getMonth(String dateDetail) {
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        for(int i=0; i< shortMonths.length; i++) {
            if(dateDetail.equals(shortMonths[i])) {
                return String.valueOf(i+1);
            }
        }
        return null;
    }

    private static List<IndexData> getIndexData() throws IOException {
        return new IndexDataReader("/Users/upstox/Downloads/data.csv", true).read();
    }

    private static Map<String, String> getSuccessData(BigDecimal range) throws IOException {
        Map<String, String> status = new HashMap<>();
        List<IndexData> indexData = getIndexData();
        for(IndexData d : indexData) {
            String dayOfTrade = getDayOfTheTrade(d);
            d.setDayName(dayOfTrade);
        }
        Map<String, List<IndexData>> dayWiseData = indexData.stream().collect(Collectors.groupingBy(IndexData::getDayName));
        List<IndexData> thursdayData = dayWiseData.get(days[4]);
        Iterator<IndexData> indexDataIterator = thursdayData.iterator();
        IndexData indexData1 = indexDataIterator.next();
        String tradeDate = indexData1.getDate();
        BigDecimal weekOneOpening = indexData1.getClose();
        BigDecimal weekTwoUpper = weekOneOpening.add(range);
        while(indexDataIterator.hasNext()) {
            IndexData indexData2 = indexDataIterator.next();
            if(indexData2.getClose().compareTo(weekTwoUpper) == -1) {
                status.put(tradeDate, SUCCESS);
            } else {
                status.put(tradeDate, FAILURE);
            }
            tradeDate = indexData2.getDate();
            weekTwoUpper = indexData2.getClose().add(range);
        }
        return status;
    }

    private static String getDayOfTheTrade(IndexData d) {
        String dateDetail = d.getDate();
        String[] dateDetails = dateDetail.split("-");
        String year = dateDetails[2];
        String date = dateDetails[0];
        String month = getMonth(dateDetails[1]);
        String dayOfTrade = getNameOfDay(year, date, month);
        return dayOfTrade;
    }

}

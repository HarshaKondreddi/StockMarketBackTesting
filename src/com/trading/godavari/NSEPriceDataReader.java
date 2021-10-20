package com.trading.godavari;

import java.math.BigDecimal;

public class NSEPriceDataReader extends CsvReader<NSEPriceData>{

    public NSEPriceDataReader(String fileName, boolean skipHeader) {
        super(fileName, skipHeader);
    }

    /**
     * @param line
     * @return
     */
    @Override
    protected NSEPriceData readCsv(String[] line) {
        NSEPriceData nsePriceData = new NSEPriceData();
        nsePriceData.setSymbol(line[0]);
        nsePriceData.setDate(line[1]);
        nsePriceData.setExpiry(line[2]);
        nsePriceData.setOptionType(line[3]);
        nsePriceData.setStrikePrice(Long.valueOf(line[4].trim()));
        nsePriceData.setOpen(new BigDecimal(line[5].trim()));
        nsePriceData.setHigh(new BigDecimal(line[6].trim()));
        nsePriceData.setLow(new BigDecimal(line[7].trim()));
        nsePriceData.setClose(new BigDecimal(line[8].trim()));
        return nsePriceData;
    }
}

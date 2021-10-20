package com.trading.godavari;

import java.math.BigDecimal;

public class IndexDataReader extends CsvReader<IndexData>{

    public IndexDataReader(String fileName, boolean skipHeader) {
        super(fileName, skipHeader);
    }

    @Override
    public IndexData read(String[] line) {
        IndexData indexData = new IndexData();
        indexData.setDate(line[0]);
        indexData.setOpen(new BigDecimal(line[1]));
        indexData.setHigh(new BigDecimal(line[2]));
        indexData.setLow(new BigDecimal(line[3]));
        indexData.setClose(new BigDecimal(line[4]));
        indexData.setSharesTraded(new BigDecimal(line[5]));
        indexData.setTurnOver(new BigDecimal(line[6]));
        return indexData;
    }
}

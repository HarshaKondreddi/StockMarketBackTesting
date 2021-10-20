package com.trading.godavari;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvReader<T> {

    private final String fileName;
    private final boolean skipHeader;
    private List<T> lines = new ArrayList<>();
    private CSVReader csvReader;

    public CsvReader(String fileName, boolean skipHeader) {
        this.fileName = fileName;
        this.skipHeader = skipHeader;
    }

    public List<T> read() throws IOException {

        try {
            init();
            String[] strings = csvReader.readNext();

            while (strings != null) {
                lines.add(readLine(strings));
                strings = csvReader.readNext();
            }
        } catch (IOException e) {
            throw e;
        }
        return lines;
    }

    public void destroy() {

        try {
            csvReader.close();
        } catch (IOException e) {
        }
    }

    /**
     * @param strings
     * @return
     */
    protected abstract T readCsv(String[] strings);

    private void init() throws IOException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        csvReader = new CSVReaderBuilder(fileReader).withSkipLines(skipHeader ? 1 : 0)
                .build();
    }

    /**
     * @param strings
     * @return
     */
    private T readLine(String[] strings) {
        return readCsv(strings);
    }
}

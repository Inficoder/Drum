package com.bryce.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CsvUtil {


    /**
     * @return void
     * @Description 导出csv
     * @Author Bryce
     * @Date 17:04 2020/8/13
     * @Param []
     **/
    public void generate(String path, String name, String[] headers, List<Object[]> data) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        Appendable printWriter = new PrintWriter(file + "/" + name + ".csv", "utf-8");
        CSVPrinter csvPrinter = CSVFormat.EXCEL.withHeader(headers).print(printWriter);
        for (Object[] d : data) {
            csvPrinter.printRecord(d);
        }
        csvPrinter.flush();
        csvPrinter.close();
    }


}
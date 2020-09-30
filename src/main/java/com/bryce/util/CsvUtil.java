package com.bryce.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CsvUtil {
    /**
     * @return void
     * @Description 导出csv
     * @Author Bryce
     * @Date 17:04 2020/8/13
     * @Param []
     **/
    public static void exportByFile(String path, String[] headers, List<LinkedHashMap<String, Object>> dataList) throws IOException {
        File file = new File(path);
        File fileParent = file.getParentFile();
        System.out.println("fileParent:" + fileParent);
        if (!fileParent.exists()) {
            //创建多级目录
            fileParent.mkdirs();
        }
        if (!file.exists())
            //有路径才能创建文件
            file.createNewFile();
        FileOutputStream fileOutputStream = null;
        CSVPrinter csvPrinter = null;
        fileOutputStream = new FileOutputStream(path);
        //TODO  windows 编码默认 GBK，LINUX 为UTF-8
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(headers);
        csvPrinter = new CSVPrinter(outputStreamWriter, csvFormat);
        for (int i = 0; i < dataList.size(); i++) {
            List<String> values = new ArrayList<>();
            LinkedHashMap<String, Object> rowDatas = dataList.get(i);
            for (String key : rowDatas.keySet()) {
                values.add(String.valueOf(rowDatas.get(key)));
            }
            csvPrinter.printRecord(values);
        }
        close(fileOutputStream, csvPrinter);
    }


    //关闭文件字节流
    public static void close(OutputStream outputStream, CSVPrinter csvPrinter) throws IOException {
        if (csvPrinter != null) {
            csvPrinter.flush();
            csvPrinter.close();
        }
        if (outputStream != null) {
            outputStream.flush();
            outputStream.close();
        }
    }

}
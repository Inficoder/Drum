package com.bryce;

import com.bryce.entity.Mail;
import com.bryce.util.EmailUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@SpringBootTest
class DrumApplicationTests {

    @Resource
    EmailUtil emailUtil;

    @Test
    @SneakyThrows
    public void test1() {
        //private final String from = "767174855@qq.com";
        String to = "tianhaonan@buaa.edu.cn";
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setContent("hello mail miao!");
        mail.setMsgId("1");
        mail.setTitle("hello qq m");
        emailUtil.sentDocumentMail(mail);
        Thread.sleep(30000);
    }




    /**
     * @Description 导出csv
     **/
//    @Override
//    @SneakyThrows
//    public boolean exportOrgMonthVolume(String yearMonth, int org) {
//        List<VolumeOrgMonthVO> volumeOrgMonthVOS = volumeService.listOrgMonthVolume(true, Constant.INT_PLACEHOLDER, Constant.INT_PLACEHOLDER, yearMonth, org);
//        List<LinkedHashMap<String, Object>> linkedHashMaps = ConvertListUtil.covertListObject(volumeOrgMonthVOS);
//        LocalDateTime now = LocalDateTime.now();
//        String format = now.format(formatter);
//        String path = Constant.REPORT_SAVE_PATH+ReportTypeAndName.REPORT_VOLUME_MONTH.getReportCnName()+"_"+format+Constant.CSV_SUFFIX;
//        reportFormsMapper.generateReport(UUID.randomUUID().toString(), ReportTypeAndName.REPORT_VOLUME_MONTH.getTypeId() ,ReportTypeAndName.REPORT_VOLUME_MONTH.getNameId(), path, now);
//        String[] headers = new String[]{
//                ConstantKeys.DEPARTMENT_NAME + "/" + ConstantKeys.COST_CENTER_NAME,
//                ConstantKeys.A4_WHITE_VOLUME,
//                ConstantKeys.A4_COLOR_VOLUME,
//                ConstantKeys.A3_WHITE_VOLUME,
//                ConstantKeys.A3_COLOR_VOLUME,
//                ConstantKeys.VOLUME_SUM
//        };
//        GenerateCsvUtil.exportByFile(path,headers,linkedHashMaps);
//        return true;
//    }

    //list转map，反射
    public static <T> List<LinkedHashMap<String, Object>> covertListObject(List<T> list) throws Exception {
        List<LinkedHashMap<String, Object>> listAndMap = new ArrayList<LinkedHashMap<String, Object>>();
        if (list != null && !list.isEmpty()) {
            for (T t : list) {
                Field[] fields = t.getClass().getDeclaredFields();
                LinkedHashMap<String, Object> newMap = new LinkedHashMap<String, Object>();
                for (Field field : fields) {
                    String fieldName = field.getName(); //获取属性名
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, t.getClass()); //获取属性的描述符
                    Method readMethod = propertyDescriptor.getReadMethod(); //获取用于读取属性值的方法
                    Object o = readMethod.invoke(t); //读取属性值
                    //格式化时间
                    if ("java.util.Date".equals(field.getType().getName())) {
//                        o = DateTimeUtil.getFormatDate(o);
                        Date date = (Date) o;
                        o = getFormatDateTime(dateToLocalDateTime(date));
                    }
                    newMap.put(fieldName, o);
                }
                listAndMap.add(newMap);
            }
        }
        return listAndMap;
    }

    public static String getFormatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }


}

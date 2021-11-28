package com.curtis.apache.lang3;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-27
 * @email curtis.cai@outlook.com
 * @reference
 */
public class DateUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtilsTest.class);

    @Test
    public void testDateParse() throws ParseException {
        Date date1 = DateUtils.parseDate("2021-12-12", "yyyy-MM-dd");
        Date date2 = FastDateFormat.getInstance("yyyy-MM-dd").parse("2021-12-12");
        Date date3 = DateUtils.parseDate("2021-12-12", DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
        Date date4 = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-12-12");
        // 01:55:01,847  INFO DateUtilsTest:30 - date1 -> 2021-12-12T00:00:00.000+0800, date2 -> 2021-12-12T00:00:00.000+0800,
        // date3 -> 2021-12-12T00:00:00.000+0800, date4 -> 2021-12-12T00:00:00.000+0800
        LOGGER.info("date1 -> {}, date2 -> {}, date3 -> {}, date4 -> {}", date1, date2, date3, date4);


        Date datetime1 = DateUtils.parseDate("2021-12-12 11:12:30", "yyyy-MM-dd hh:mm:ss");
        Date datetime2 = FastDateFormat.getInstance("yyyy-MM-dd hh:mm:ss").parse("2021-12-12 11:12:30");
        // 01:55:01,847  INFO DateUtilsTest:35 - datetime1 -> 2021-12-12T11:12:30.000+0800, datetime2 -> 2021-12-12T11:12:30.000+0800
        LOGGER.info("datetime1 -> {}, datetime2 -> {}", datetime1, datetime2);
    }

    @Test
    public void testDateFormat() {
        String dateStr1 = FastDateFormat.getInstance("yyyy-MM-dd").format(new Date());
        String dateStr2 = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(new Date());
        // 01:54:41,046  INFO DateUtilsTest:42 - dateStr1 -> 2021-11-27, dateStr2 -> 2021-11-27
        LOGGER.info("dateStr1 -> {}, dateStr2 -> {}", dateStr1, dateStr2);


        String dateTimeStr1 = FastDateFormat.getInstance("yyyy-MM-dd hh:mm:ss").format(new Date());
        String dateTimeStr2 = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date());
        // 01:54:41,056  INFO DateUtilsTest:47 - dateTimeStr1 -> 2021-11-27 01:54:41, dateTimeStr2 -> 2021-11-27T01:54:41
        LOGGER.info("dateTimeStr1 -> {}, dateTimeStr2 -> {}", dateTimeStr1, dateTimeStr2);
    }
}

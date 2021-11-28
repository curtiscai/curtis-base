package com.curtis.apache.lang3;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-27
 * @email curtis.cai@outlook.com
 * @reference
 */
public class ObjectUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtilsTest.class);

    /**
     * 判断集合是空或者是空元素
     */
    @Test
    public void testObjectUtils() {
        List<Integer> intList1 = null;
        List<Integer> intList2 = Lists.newArrayList();
        boolean empty1 = ObjectUtils.isEmpty(intList1);
        boolean empty2 = ObjectUtils.isEmpty(intList2);
        // 16:34:32,363  INFO ObjectUtilsTest:25 - empty1 -> true, empty2 -> true
        LOGGER.info("empty1 -> {}, empty2 -> {}", empty1, empty2);

        boolean notEmpty1 = ObjectUtils.isNotEmpty(intList1);
        boolean notEmpty2 = ObjectUtils.isNotEmpty(intList2);
        // 16:34:32,366  INFO ObjectUtilsTest:29 - notEmpty1 -> false, notEmpty2 -> false
        LOGGER.info("notEmpty1 -> {}, notEmpty2 -> {}", notEmpty1, notEmpty2);

        String str1 = ObjectUtils.defaultIfNull(null, "--");
        String str2 = ObjectUtils.defaultIfNull("", "--");
        String str3 = ObjectUtils.defaultIfNull("test", "--");
        // 16:46:38,287  INFO ObjectUtilsTest:42 - str1 -> --, str2 -> , str3 -> test
        LOGGER.info("str1 -> {}, str2 -> {}, str3 -> {}", str1, str2, str3);
    }
}

package com.curtis.apache.beanutils;

import org.apache.commons.beanutils.converters.BooleanConverter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-28
 * @email curtis.cai@outlook.com
 * @reference
 */
public class BooleanConvertTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooleanConvertTest.class);

    @Test
    public void test() {
        Boolean true1 = new BooleanConverter().convert(Boolean.class, "true");
        Boolean true2 = new BooleanConverter().convert(Boolean.class, "yes");
        Boolean true3 = new BooleanConverter().convert(Boolean.class, "y");
        Boolean true4 = new BooleanConverter().convert(Boolean.class, "on");
        Boolean true5 = new BooleanConverter().convert(Boolean.class, "1");
        Boolean true6 = new BooleanConverter().convert(Boolean.class, 1);
        // 22:40:28,356  INFO BooleanConvertTest:27 - true1 -> true, true1 -> true, true1 -> true, true1 -> true, true1 -> true, true1 -> true
        LOGGER.info("true1 -> {}, true1 -> {}, true1 -> {}, true1 -> {}, true1 -> {}, true1 -> {}", true1, true2, true3, true4, true5, true6);

        Boolean false1 = new BooleanConverter().convert(Boolean.class, "false");
        Boolean false2 = new BooleanConverter().convert(Boolean.class, "no");
        Boolean false3 = new BooleanConverter().convert(Boolean.class, "n");
        Boolean false4 = new BooleanConverter().convert(Boolean.class, "off");
        Boolean false5 = new BooleanConverter().convert(Boolean.class, "0");
        Boolean false6 = new BooleanConverter().convert(Boolean.class, 0);
        // 22:40:28,356  INFO BooleanConvertTest:35 - false1 -> false, false2 -> false, false3 -> false, false4 -> false, false5 -> false, false6 -> false
        LOGGER.info("false1 -> {}, false2 -> {}, false3 -> {}, false4 -> {}, false5 -> {}, false6 -> {}", false1, false2, false3, false4, false5, false6);

        // org.apache.commons.beanutils.ConversionException: Can't convert value 'test' to type class java.lang.Boolean
        Boolean test = new BooleanConverter().convert(Boolean.class, "test");
        LOGGER.info("test -> {}", test);

    }
}

package com.curtis.apache.lang3;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-27
 * @email curtis.cai@outlook.com
 * @reference
 */
public class BooleanUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooleanUtilsTest.class);

    @Test
    public void testConstant() {
        String trueStr = BooleanUtils.TRUE;
        String falseStr = BooleanUtils.FALSE;
        String yes = BooleanUtils.YES;
        String no = BooleanUtils.NO;
        String on = BooleanUtils.ON;
        String off = BooleanUtils.OFF;
        LOGGER.info("trueStr -> {}, falseStr -> {}, yes -> {}, no -> {}, on -> {}, off -> {}", trueStr, falseStr, yes, no, on, off);
    }

    @Test
    public void test() {
        String falseStr = BooleanUtils.FALSE;
        String trueStr = BooleanUtils.TRUE;
        String yes = BooleanUtils.YES;
        String no = BooleanUtils.NO;
        String on = BooleanUtils.ON;
        String off = BooleanUtils.OFF;
        LOGGER.info("falseStr -> {}, falseStr -> {}, falseStr -> {}, falseStr -> {}, falseStr -> {}, falseStr -> {}", falseStr, trueStr, yes, no, on, off);
    }
}

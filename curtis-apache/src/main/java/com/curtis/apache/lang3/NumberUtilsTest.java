package com.curtis.apache.lang3;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-26
 * @email curtis.cai@outlook.com
 * @reference
 */
public class NumberUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberUtils.class);

    /**
     * 判断字符串是否是纯数字
     */
    @Test
    public void testisDigits() {
        boolean digits1 = NumberUtils.isDigits("123");
        boolean digits2 = NumberUtils.isDigits("123.1");
        boolean digits3 = NumberUtils.isDigits("+123.1");
        boolean digits4 = NumberUtils.isDigits("-123.1");
        // 00:28:19,750  INFO NumberUtils:31 - digits1 -> true, digits2 -> false, digits3 -> false, digits4 -> false
        LOGGER.info("digits1 -> {}, digits2 -> {}, digits3 -> {}, digits4 -> {}", digits1, digits2, digits3, digits4);
    }

    /**
     * 数值0、-1、1各类型常量（工作中也比较常用，比如各种状态，数据库定义使用了TINYINT，则对应分别是byte类型）
     */
    @Test
    public void testConstant() {
        Byte byteZero = NumberUtils.BYTE_ZERO;
        Byte byteOne = NumberUtils.BYTE_ONE;
        Byte byteMinusOne = NumberUtils.BYTE_MINUS_ONE;
        Short shortZero = NumberUtils.SHORT_ZERO;
        Short shortOne = NumberUtils.SHORT_ONE;
        Short shortMinusOne = NumberUtils.SHORT_MINUS_ONE;
        Integer integerZero = NumberUtils.INTEGER_ZERO;
        Integer integerOne = NumberUtils.INTEGER_ONE;
        Integer integerMinusOne = NumberUtils.INTEGER_MINUS_ONE;
    }
}

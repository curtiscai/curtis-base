package com.curtis.apache.lang3.string;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

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

    @Test
    public void test(){
        boolean parsable = NumberUtils.isParsable("123.1");
        boolean parsable1 = NumberUtils.isParsable("+123.1");
        boolean parsable2 = NumberUtils.isParsable("-123.1");
        boolean digits = NumberUtils.isDigits("123.1");
        boolean digits1 = NumberUtils.isDigits("+123.1");
        boolean digits2 = NumberUtils.isDigits("-123.1");

        Byte byteZero = NumberUtils.BYTE_ZERO;
        Byte byteOne = NumberUtils.BYTE_ONE;
        Short shortZero = NumberUtils.SHORT_ZERO;
        Short shortOne = NumberUtils.SHORT_ONE;
        Integer integerZero = NumberUtils.INTEGER_ZERO;
        Integer integerOne = NumberUtils.INTEGER_ONE;

        BigDecimal bigDecimal = NumberUtils.toScaledBigDecimal(1.11, 2, RoundingMode.HALF_UP);

    }
}

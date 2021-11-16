package com.curtis.core.base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author curtis.cai
 * @desc Java基础数据类型-整形
 * @date 2021-09-21
 * @email curtis.cai@outlook.com
 * @reference
 */
public class JavaPrimitiveDataTypeOfIntegerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaPrimitiveDataTypeOfIntegerTest.class);

    /**
     * 测试：整数三种表示方式：
     * 十进制（以非零数字开头，后跟多个0～9数字）
     * 八进制（以0开头，后跟多个0～7的数字）
     * 十六进制（以0x或0X开头，后跟多个0～9的数字或a～f的小写字母或A～F的大写字母）
     * 二进制（以0b或0B开头，后跟多个0～1的数字） JDK 7+支持
     */
    @Test
    public void testJavaPrimitiveDataTypeOfIntegerValue() {
        // 八进制（以0开头，后跟多个0～7的数字）
        int intValueOfOctal = 077;
        // 十进制（以非零数字大头，后跟多个0～9数字）
        int intValueOfDecimal = 100;
        // 十六进制（以0x或0X开头，后跟多个0～9的数字或a～f的小写字母或A～F的大写字母）
        int intValueOfHexadecimal1 = 0x9f;
        int intValueOfHexadecimal2 = 0x9F;
        int intValueOfHexadecimal3 = 0X9f;
        int intValueOfHexadecimal4 = 0X9F;
        // 二进制（以0b或0B开头，后跟多个0～1的数字） JDK 7+支持
        int intValueOfBinary1 = 0b0101;
        int intValueOfBinary2 = 0B0101;
    }

    /**
     * 测试：二进制、八进制、十进制、十六进制互相转换
     */
    @Test
    public void testJavaPrimitiveDataTypeOfIntegerConvert() {

        System.out.println("******** 十进制转为二进制、八进制、十六进制 ********");
        int intValue = 31;
        String binaryString = Integer.toBinaryString(intValue);
        String octalString = Integer.toOctalString(intValue);
        String hexString = Integer.toHexString(intValue);
        // 22:17:06,332  INFO JavaPrimitiveDataTypeTest:48 - binaryString -> 11111
        LOGGER.info("binaryString -> {}",binaryString);
        // 22:17:06,334  INFO JavaPrimitiveDataTypeTest:49 - octalString -> 37
        LOGGER.info("octalString -> {}",octalString);
        // 22:17:06,334  INFO JavaPrimitiveDataTypeTest:50 - hexString -> 1f
        LOGGER.info("hexString -> {}",hexString);


        System.out.println("******** 二进制、八进制、十六进制转为十进制 ********");
        Integer binaryInteger = Integer.valueOf("11111", 2);
        // 22:19:27,965  INFO JavaPrimitiveDataTypeTest:58 - binaryInteger -> 31
        LOGGER.info("binaryInteger -> {}",binaryInteger);
        Integer octalInteger = Integer.valueOf("37", 8);
        // 22:19:27,965  INFO JavaPrimitiveDataTypeTest:61 - octalInteger -> 31
        LOGGER.info("octalInteger -> {}",octalInteger);
        Integer hexInteger = Integer.valueOf("1f", 16);
        // 22:19:27,965  INFO JavaPrimitiveDataTypeTest:64 - hexInteger -> 31
        LOGGER.info("hexInteger -> {}",hexInteger);
    }

    /**
     * 测试：Java语言定义了4种表示整数的类型：字节型（byte）、短整型（short）、整型（int）、长整型（long）。每种整型的数据都是带符号位的。
     */
    @Test
    public void testJavaPrimitiveDataTypeOfInteger(){
        byte byteValue = Byte.MAX_VALUE;
        // 编译报错
        // byte byteErrorValue = 128;

        short shortValue = Short.MAX_VALUE;
        // 编译报错
        // short shortErrorValue = 32768;

        int intValue = Integer.MAX_VALUE;
        // 编译报错
        // int intErrorValue = 2147483648;

        long longValue = Long.MAX_VALUE;
        // 编译报错
        // long longErrorValue = 9223372036854775808L;

        // 编译报错：一个整数隐含为整型（int型）。当要将一个整数强制表示为长整数时，需在后面加字母l或L。
        // 所以若声明long型变量的值超过int型的取值范围时，如果数的后面不加l或L，系统会认是int型而出错。
        // long val = 2147483648;
        long val1 = 2147483648L;
        // 推荐使用大写L，而不是l
        long val2 = 2147483648l;
    }
}

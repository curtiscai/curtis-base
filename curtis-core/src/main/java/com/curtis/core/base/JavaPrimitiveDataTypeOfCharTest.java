package com.curtis.core.base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author curtis.cai
 * @desc Java基础数据类型-布尔型
 * @date 2021-09-21
 * @email curtis.cai@outlook.com
 * @reference
 */
public class JavaPrimitiveDataTypeOfCharTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaPrimitiveDataTypeOfCharTest.class);

    /**
     * 测试：Java字符型三种表示方式
     */
    @Test
    public void testJavaPrimitiveDataTypeOfChar() {
        // 表示方式1：单引号括住字符。
        char ch = 'A';
        int chInt = ch;
        // 23:37:38,707  INFO JavaPrimitiveDataTypeOfCharTest:27 - ch = A, chInt = 65
        LOGGER.info("ch = {}, chInt = {}", ch, chInt);

        // 表示方式2：斜杠u加四位十六进制数字
        char ch2 = '\u0041';
        int chInt2 = ch2;
        // 23:37:38,710  INFO JavaPrimitiveDataTypeOfCharTest:32 - ch2 = A, chInt2 = 65
        LOGGER.info("ch2 = {}, chInt2 = {}", ch2, chInt2);

        // 表示方式3：转义字符
        char ch3 = '\'';
        int chInt3 = ch3;
        // 23:50:23,530  INFO JavaPrimitiveDataTypeOfCharTest:40 - ch3 = ', chInt3 = 39
        LOGGER.info("ch3 = {}, chInt3 = {}", ch3, chInt3);

        char ch4 = '邉';
        int chInt4 = (int) ch4;
        // 23:37:38,710  INFO JavaPrimitiveDataTypeOfCharTest:36 - ch4 = 邉, chInt4 = 37001
        LOGGER.info("ch4 = {}, chInt4 = {}", ch4, chInt4);
    }
}

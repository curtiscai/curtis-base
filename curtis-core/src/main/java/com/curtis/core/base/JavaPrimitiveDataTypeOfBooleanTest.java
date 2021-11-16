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
public class JavaPrimitiveDataTypeOfBooleanTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaPrimitiveDataTypeOfBooleanTest.class);

    /**
     * 测试：Java语言定义了1种表示布尔值的类型：boolean。布尔型只有true和false两个取值。
     */
    @Test
    public void testJavaPrimitiveDataTypeOfBoolean(){
        boolean trueValue = Boolean.TRUE;
        boolean falseValue = Boolean.FALSE;
    }
}

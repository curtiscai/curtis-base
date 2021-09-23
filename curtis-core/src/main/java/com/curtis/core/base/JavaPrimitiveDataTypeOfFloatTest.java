package com.curtis.core.base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author curtis.cai
 * @desc Java基础数据类型-浮点型
 * @date 2021-09-21
 * @email curtis.cai@outlook.com
 * @reference
 */
public class JavaPrimitiveDataTypeOfFloatTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaPrimitiveDataTypeOfFloatTest.class);

    /**
     * 测试：Java语言定义了2种表示浮点数的类型：单精度浮点（float）和双精度浮点（double）。
     */
    @Test
    public void testJavaPrimitiveDataTypeOfInteger(){
        float floatValue = Float.MAX_VALUE;

        double doubleValue = Double.MAX_VALUE;

        // 编译报错：一个浮点数隐含为double型。若在一个浮点数后加字母f或F，将其强制转换为float型，
        // 所以若声明float型变量时如果数的后面不加f或F，系统会认为是double型而出错。
        // float val = 1.1;
        float val1 = 1.1F;
        float val2 = 1.1f;
    }
}

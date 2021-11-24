package com.curtis.apache.lang3.string;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-25
 * @email curtis.cai@outlook.com
 * @reference
 */
public class StringUtilsTest {

    @Test
    public void testStringUtils(){
        boolean isNotBlank = StringUtils.isNotBlank(null);
        boolean isNotEmpty = StringUtils.isNotEmpty(null);

        String[] split = StringUtils.split("a&b", "&");

        String join = StringUtils.join(Lists.newArrayList("a", "b", "c"), "&");

    }
}

package com.curtis.apache.lang3;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.util.Collections;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-26
 * @email curtis.cai@outlook.com
 * @reference
 */
public class LangTest {

    @Test
    public void test(){
        String s = ObjectUtils.defaultIfNull(null, "");
        boolean b = ObjectUtils.notEqual(null, null);
        boolean empty = ObjectUtils.isEmpty(null);
        // ObjectUtils.median(Lists.newArrayList(1,2,3));
        // T clone = SerializationUtils.clone(new Object());
        // SerializationUtils.serialize()
        // SerializationUtils.deserialize()
    }
}

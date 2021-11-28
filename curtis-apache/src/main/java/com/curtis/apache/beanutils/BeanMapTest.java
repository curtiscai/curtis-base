package com.curtis.apache.beanutils;

import com.curtis.apache.model.User;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-28
 * @email curtis.cai@outlook.com
 * @reference
 */
public class BeanMapTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanMapTest.class);

    /**
     * Model转Map，然后根据属性名获取属性值
     */
    @Test
    public void testBeanMapConvert() {
        User user = new User(1, "curtis1", 17600001001L, true, BigDecimal.valueOf(171.1));
        BeanMap beanMap = new BeanMap(user);
        Integer id = (Integer) beanMap.get("id");
        String name = (String) beanMap.get("name");
        String notfound = (String) beanMap.get("notfound");
        // 22:51:14,978  INFO BeanMapTest:29 - id -> 1, name -> curtis1
        LOGGER.info("id -> {}, name -> {}, notfound -> {}", id, name, notfound);

        // model转Map
        try {
            Map<String, String> describe = BeanUtils.describe(user);
            // 23:23:43,434  INFO BeanMapTest:41 - describe -> {phone=17600001001, sex=true, name=curtis1, id=1, height=171.1}
            LOGGER.info("describe -> {}", describe);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * model转Map，然后根据属性名修改属性值
     */
    @Test
    public void testBeanMapPut() {
        User user = new User(1, "curtis1", 17600001001L, true, BigDecimal.valueOf(171.1));
        BeanMap beanMap = new BeanMap(user);

        Object put = beanMap.put("name", "curtis11");
        // 22:51:14,978  INFO BeanMapTest:29 - id -> 1, name -> curtis1
        LOGGER.info("user -> {}, put -> {}", user, put);
    }
}

package com.curtis.apache.collections;

import com.curtis.apache.model.User;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-28
 * @email curtis.cai@outlook.com
 * @reference
 */
public class MapUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapUtilsTest.class);

    @Test
    public void testMapUtilsOfEmpty() {
        boolean notEmpty1 = MapUtils.isNotEmpty(Maps.newHashMap());
        boolean notEmpty2 = MapUtils.isNotEmpty(null);
        // 23:55:11,645  INFO MapUtilsTest:24 - notEmpty1 -> false, notEmpty2 -> false
        LOGGER.info("notEmpty1 -> {}, notEmpty2 -> {}", notEmpty1, notEmpty2);
    }

    @Test
    public void testMapToProperties() {
        User user = new User(1, "curtis1", 17600001001L, true, BigDecimal.valueOf(171.1));
        Map<String, String> describe = Maps.newHashMap();
        try {
            describe = BeanUtils.describe(user);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        Properties properties = MapUtils.toProperties(describe);
        String id = properties.getProperty("id");
        // 00:01:08,219  INFO MapUtilsTest:48 - id -> 1
        LOGGER.info("id -> {}", id);
    }
}

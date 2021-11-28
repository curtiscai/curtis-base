package com.curtis.apache.beanutils;

import com.curtis.apache.model.User;
import com.curtis.apache.model.UserDto;
import com.google.common.collect.Maps;
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
public class BeanUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtilsTest.class);

    /**
     * 性能较差，不建议使用
     */
    @Test
    public void testBeanUtils() {
        User user = new User(1, "curtis1", 17600001001L, true, BigDecimal.valueOf(171.1));
        UserDto userDto = new UserDto();
        try {
            BeanUtils.copyProperties(userDto, user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 23:06:35,575  INFO BeanUtilsTest:39 - user -> User{id=1, name='curtis1', phone=17600001001, sex=true, height=171.1}
        LOGGER.info("user -> {}", user);
        // 23:06:35,575  INFO BeanUtilsTest:40 - userDto -> User{id=1, name='curtis1', phone=17600001001, sex=true, height=171.1}
        LOGGER.info("userDto -> {}", userDto);
    }

    /**
     * 将Map键值对赋值给指定对象相应属性
     */
    @Test
    public void testBeanUtilsPopulate() {
        User user = new User();

        Map<String, Object> keyValueMap = Maps.newHashMap();
        keyValueMap.put("id", 1);
        keyValueMap.put("name", "curtis1");
        keyValueMap.put("phone", 17600001001L);
        keyValueMap.put("sex", true);
        keyValueMap.put("height", BigDecimal.valueOf(171.1));
        try {
            BeanUtils.populate(user, keyValueMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 23:10:37,486  INFO BeanUtilsTest:66 - user -> User{id=1, name='curtis1', phone=17600001001, sex=true, height=171.1}
        LOGGER.info("user -> {}", user);
        // 23:10:37,486  INFO BeanUtilsTest:68 - keyValueMap -> {phone=17600001001, sex=true, name=curtis1, id=1, height=171.1}
        LOGGER.info("keyValueMap -> {}", keyValueMap);
    }

    @Test
    public void testGetSetProperty(){
        User user = new User(1, "curtis1", 17600001001L, true, BigDecimal.valueOf(171.1));
        try {
            String name = BeanUtils.getProperty(user, "name");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            String sex = BeanUtils.getProperty(user, "sex");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            BeanUtils.setProperty(user,"name","curtis11");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 23:20:42,460  INFO BeanUtilsTest:90 - user -> User{id=1, name='curtis11', phone=17600001001, sex=true, height=171.1}
        LOGGER.info("user -> {}", user);
    }
}

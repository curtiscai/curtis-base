package com.curtis.apache.lang3;

import com.curtis.apache.model.User;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-27
 * @email curtis.cai@outlook.com
 * @reference
 */
public class SerializationUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializationUtilsTest.class);

    @Test
    public void testSerializationUtils() {
        // 深拷贝
        // 必须实现序列化接口：Serializable
        final User user = new User(1, "curtis1", 17600001001L, true, BigDecimal.valueOf(171.1));
        User userClone = (User) SerializationUtils.clone(user);
        userClone.setId(2);
        LOGGER.info("user -> {}", user);
        LOGGER.info("userClone -> {}", userClone);


        // 序列化与反序列化
        byte[] serialize = SerializationUtils.serialize(user);
        User deserializeUser = SerializationUtils.deserialize(serialize);
        LOGGER.info("serialize -> {}", serialize);
        LOGGER.info("deserializeUser -> {}", deserializeUser);
    }
}

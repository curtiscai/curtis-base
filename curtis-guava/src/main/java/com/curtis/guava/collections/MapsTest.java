package com.curtis.guava.collections;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author curtis.cai
 * @desc Guava Collection之Maps api的使用
 * @date 2021-11-17
 * @email curtis.cai@outlook.com
 * @reference
 */
public class MapsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapsTest.class);

    @Test
    public void testCreateMap() {
        // 构造无序Map
        Map<String, String> hashMap = Maps.newHashMap();
        hashMap.put("河北", "石家庄");
        hashMap.put("北京", "北京");
        hashMap.put("陕西", "西安");
        LOGGER.info("hashMap->{}", hashMap);

        // 构造有序Map
        Map<String, String> linkedHashMap = Maps.newLinkedHashMap();
        linkedHashMap.put("河北", "石家庄");
        linkedHashMap.put("北京", "北京");
        linkedHashMap.put("陕西", "西安");
        LOGGER.info("linkedHashMap->{}", linkedHashMap);
    }

    /**
     * 获取两个Map键或值不同的集合
     */
    @Test
    public void testDifferenceMap() {
        Map<String, String> hashMap1 = Maps.newHashMap();
        hashMap1.put("河北", "保定");
        hashMap1.put("北京", "北京");
        hashMap1.put("陕西", "西安");

        Map<String, String> hashMap2 = Maps.newHashMap();
        hashMap2.put("河北", "石家庄");
        hashMap2.put("北京", "北京");
        hashMap2.put("山西", "太原");
        MapDifference<String, String> difference = Maps.difference(hashMap1, hashMap2);
        // 23:11:37,783  INFO MapsTest:57 - difference->not equal: only on left={陕西=西安}: only on right={山西=太原}: value differences={河北=(保定, 石家庄)}
        LOGGER.info("difference->{}", difference);
    }

}

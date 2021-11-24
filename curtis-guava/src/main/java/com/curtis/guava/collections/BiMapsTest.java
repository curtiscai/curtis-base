package com.curtis.guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author curtis.cai
 * @desc Guava Collection之BiMap api的使用
 * @date 2021-11-18
 * @email curtis.cai@outlook.com
 * @reference
 */
public class BiMapsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BiMapsTest.class);

    /**
     * 双向Map
     * BiMap是双向Map，特点是Key和Value都不能重复，并且BiMap是为了解决工作中需要根据Value找Key的场景，也是非常实用。
     * BiMap要求key和value都唯一，如果key不唯一则覆盖key，如果value不唯一则直接报错。
     */
    @Test
    public void testBiMaps() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("河北", "石家庄");
        biMap.put("北京", "北京");
        biMap.put("山西", "太原");
        // 22:14:17,840  INFO BiMapsTest:29 - biMap -> {河北=石家庄, 北京=北京, 山西=太原}
        LOGGER.info("biMap -> {}", biMap);

        // 根据Key找Value
        String value = biMap.get("河北");
        // 22:14:17,850  INFO BiMapsTest:32 - value -> 石家庄
        LOGGER.info("value -> {}", value);

        // 根据Value找Key
        BiMap<String, String> inverseMap = biMap.inverse();
        String key = inverseMap.get("石家庄");
        // 22:14:17,850  INFO BiMapsTest:36 - key -> 河北
        LOGGER.info("key -> {}", key);
    }

    /**
     * 在Java集合类库中的Map，它的特点是存放的键（Key）是唯一的，而值（Value）可以不唯一，
     * 而bimap要求key和value都唯一，如果key不唯一则覆盖key，如果value不唯一则直接报错。
     */
    @Test
    public void testBiMapsRepeat() {
        // key重复，覆盖
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("河北", "石家庄");
        biMap.put("北京", "北京");
        biMap.put("山西", "太原");
        biMap.put("河北", "保定");
        // 00:27:58,111  INFO BiMapsTest:46 - biMap -> {河北=保定, 北京=北京, 山西=太原}
        LOGGER.info("biMap -> {}", biMap);

        // 值重复，报错
        BiMap<String, String> biMap2 = HashBiMap.create();
        biMap2.put("河北", "石家庄");
        biMap2.put("北京", "北京");
        biMap2.put("山西", "太原");
        try{
            // java.lang.IllegalArgumentException: value already present: 太原
            biMap2.put("陕西", "太原");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.curtis.apache.lang3.enums;

import org.apache.commons.lang3.EnumUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-24
 * @email curtis.cai@outlook.com
 * @reference
 */
public class EnumUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnumUtilsTest.class);

    /**
     * 测试简单枚举
     */
    @Test
    public void testEnumUtilsSimple() {

        boolean jpeg11 = EnumUtils.isValidEnum(ImagesTypeEnum.class, "JPEG");
        boolean jpeg12 = EnumUtils.isValidEnum(ImagesTypeEnum.class, "jpeg");
        boolean jpeg13 = EnumUtils.isValidEnumIgnoreCase(ImagesTypeEnum.class, "Jpeg");
        boolean jpeg14 = EnumUtils.isValidEnumIgnoreCase(ImagesTypeEnum.class, "jpeg");
        boolean jpeg15 = EnumUtils.isValidEnumIgnoreCase(ImagesTypeEnum.class, "JPEG");
        LOGGER.info("jpeg11 -> {}, jpeg12 -> {}, jpeg13 -> {}, jpeg14 -> {}, jpeg15 -> {}", jpeg11, jpeg12, jpeg13, jpeg14, jpeg15);


        ImagesTypeEnum jpeg21 = EnumUtils.getEnum(ImagesTypeEnum.class, "JPEG");
        ImagesTypeEnum jpeg22 = EnumUtils.getEnumIgnoreCase(ImagesTypeEnum.class, "Jpeg");
        ImagesTypeEnum jpeg23 = EnumUtils.getEnum(ImagesTypeEnum.class, "JPEG-FALSE");
        LOGGER.info("jpeg21 -> {}, jpeg22 -> {}, jpeg23 -> {}", jpeg21, jpeg22, jpeg23);

        List<ImagesTypeEnum> enumList = EnumUtils.getEnumList(ImagesTypeEnum.class);
        LOGGER.info("enumList -> {}", enumList);

        Map<String, ImagesTypeEnum> enumMap = EnumUtils.getEnumMap(ImagesTypeEnum.class);
        LOGGER.info("enumMap -> {}", enumMap);
    }

    /**
     * 测试复杂枚举
     */
    @Test
    public void testEnumUtils() {
        // isValidEnum(Class<E> enumClass, String enumName)方法用于校验指定字符串是否在指定枚举类中（使用枚举名称进行匹配）
        // isValidEnumIgnoreCase(Class<E> enumClass, String enumName)方法用于校验指定字符串（不区分大小写，更常用）是否在指定枚举类中（使用枚举名称进行匹配）
        boolean isValid1 = EnumUtils.isValidEnum(CoordinateSystemEnum.class, "WGS84");
        boolean isValid2 = EnumUtils.isValidEnum(CoordinateSystemEnum.class, "wgs84");
        boolean isValid3 = EnumUtils.isValidEnumIgnoreCase(CoordinateSystemEnum.class, "WGS84");
        boolean isValid4 = EnumUtils.isValidEnumIgnoreCase(CoordinateSystemEnum.class, "Wgs84");
        boolean isValid5 = EnumUtils.isValidEnumIgnoreCase(CoordinateSystemEnum.class, "wgs84");
        // 22:47:51,306  INFO EnumUtilsTest:54 - isValid1 -> true, isValid2 -> false, isValid3 -> true, isValid4 -> true, isValid5 -> true
        LOGGER.info("isValid1 -> {}, isValid2 -> {}, isValid3 -> {}, isValid4 -> {}, isValid5 -> {}", isValid1, isValid2, isValid3, isValid4, isValid5);

        // getEnum(Class<E> enumClass, String enumName)方法用于获取枚举类中指定名称的枚举对象
        // getEnumIgnoreCase(Class<E> enumClass, String enumName)方法用于获取枚举类中指定名称（不区分大小写，更常用）的枚举对象
        CoordinateSystemEnum coordinateSystemEnum1 = EnumUtils.getEnum(CoordinateSystemEnum.class, "WGS84");
        CoordinateSystemEnum coordinateSystemEnum2 = EnumUtils.getEnumIgnoreCase(CoordinateSystemEnum.class, "Wgs84");
        CoordinateSystemEnum coordinateSystemEnum3 = EnumUtils.getEnum(CoordinateSystemEnum.class, "WGS84-FALSE");
        // 22:47:51,306  INFO EnumUtilsTest:61 - coordinateSystemEnum1 -> WGS84, coordinateSystemEnum2 -> WGS84, coordinateSystemEnum3 -> null
        LOGGER.info("coordinateSystemEnum1 -> {}, coordinateSystemEnum2 -> {}, coordinateSystemEnum3 -> {}", coordinateSystemEnum1, coordinateSystemEnum2, coordinateSystemEnum3);

        // getEnumList(Class<E> enumClass)方法用于获取指定枚举类的枚举集合List
        // getEnumMap(Class<E> enumClass)方法用于获取指定枚举类的枚举集合Map，然后就可以从Map中获取指定枚举
        List<CoordinateSystemEnum> enumList = EnumUtils.getEnumList(CoordinateSystemEnum.class);
        // 22:47:51,306  INFO EnumUtilsTest:66 - enumList -> [WGS84, GCJ02, BD09]
        LOGGER.info("enumList -> {}", enumList);
        // 22:47:51,306  INFO EnumUtilsTest:68 - enumMap -> {WGS84=WGS84, GCJ02=GCJ02, BD09=BD09}
        Map<String, CoordinateSystemEnum> enumMap = EnumUtils.getEnumMap(CoordinateSystemEnum.class);
        LOGGER.info("enumMap -> {}", enumMap);
        CoordinateSystemEnum coordinateSystemEnum = enumMap.get("WGS84");
        // 22:47:51,306  INFO EnumUtilsTest:70 - coordinateSystemEnum -> WGS84
        LOGGER.info("coordinateSystemEnum -> {}", coordinateSystemEnum);
        String coordinateSystemLabel = coordinateSystemEnum.getCoordinateSystemLabel();
        // 22:47:51,306  INFO EnumUtilsTest:72 - coordinateSystemLabel -> wgs84
        LOGGER.info("coordinateSystemLabel -> {}", coordinateSystemLabel);
        Integer coordinateSystemValue = coordinateSystemEnum.getCoordinateSystemValue();
        // 22:47:51,306  INFO EnumUtilsTest:74 - coordinateSystemValue -> 1
        LOGGER.info("coordinateSystemValue -> {}", coordinateSystemValue);
    }
}

package com.curtis.apache.lang3.enums;

import org.apache.commons.lang3.EnumUtils;
import org.junit.Test;

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

    @Test
    public void testLang3() {
        ImagesTypeEnum jpeg = EnumUtils.getEnum(ImagesTypeEnum.class, "JPEG");
        ImagesTypeEnum jpeg2 = EnumUtils.getEnumIgnoreCase(ImagesTypeEnum.class, "Jpeg");
        ImagesTypeEnum jpeg3 = EnumUtils.getEnum(ImagesTypeEnum.class, "JPEG-FALSE");
        System.out.println(jpeg);
        System.out.println(jpeg2);
        System.out.println(jpeg3);

        List<ImagesTypeEnum> enumList = EnumUtils.getEnumList(ImagesTypeEnum.class);
        System.out.println(enumList);

        boolean jpeg11 = EnumUtils.isValidEnum(ImagesTypeEnum.class, "JPEG");
        boolean jpeg12 = EnumUtils.isValidEnum(ImagesTypeEnum.class, "jpeg");
        System.out.println(jpeg11);
        System.out.println(jpeg12);

        Map<String, ImagesTypeEnum> enumMap = EnumUtils.getEnumMap(ImagesTypeEnum.class);
        List<ImagesTypeEnum> enumList1 = EnumUtils.getEnumList(ImagesTypeEnum.class);
        System.out.println();


        CoordinateSystemEnum wgs84 = EnumUtils.getEnum(CoordinateSystemEnum.class, "WGS84");
        System.out.println(wgs84);
        List<CoordinateSystemEnum> coordinateSystemEnumList = EnumUtils.getEnumList(CoordinateSystemEnum.class);
        Map<String, CoordinateSystemEnum> coordinateSystemEnumMap = EnumUtils.getEnumMap(CoordinateSystemEnum.class);
        System.out.println(coordinateSystemEnumMap);
        CoordinateSystemEnum wgs841 = coordinateSystemEnumMap.get("WGS84");
        System.out.println(wgs841.getCoordinateSystemLabel());
        System.out.println(wgs841.getCoordinateSystemValue());


    }
}

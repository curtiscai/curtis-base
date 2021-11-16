package com.curtis.java8.stream;

import com.curtis.java8.model.ProvinceCity;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author curtis.cai
 * @desc Java8 Stream Api Of Collect
 * @date 2021-09-10
 * @email curtis.cai@outlook.com
 * @reference
 */
public class StreamApiOfCollectTest {

    @Test
    public void testStreamApiOfCollect() {
        List<ProvinceCity> provinceCityList = Lists.newArrayList(
                new ProvinceCity(130000, "河北省", 130800, "承德市", false),
                new ProvinceCity(130000, "河北省", 130600, "保定市", false),
                new ProvinceCity(130000, "河北省", 130100, "石家庄市", true),
                new ProvinceCity(140000, "山西省", 140100, "太原市", true),
                new ProvinceCity(140000, "山西省", 140300, "阳泉市", false),
                new ProvinceCity(140000, "山西省", 140900, "忻州市", false)
        );


        //
        Map<Integer, List<ProvinceCity>> provinceCodeMap = provinceCityList.stream()
                .collect(Collectors.groupingBy(ProvinceCity::getProvinceCode, Collectors.toList()));

        // 收集器多层嵌套：Collectors.groupingBy()
        Map<Integer, Map<Boolean, List<ProvinceCity>>> collect1 = provinceCityList.stream()
                .collect(Collectors.groupingBy(ProvinceCity::getProvinceCode, Collectors.groupingBy(ProvinceCity::getProvinceCapital, Collectors.toList())));

        // 收集为List<v1,v2>
        Map<Integer, String> cityCodeNameMap = provinceCityList.stream()
                .collect(Collectors.toMap(ProvinceCity::getCityCode, ProvinceCity::getCityName));
        // 收集为List<v1,Object>
        Map<Integer, ProvinceCity> collect = provinceCityList.stream()
                .collect(Collectors.toMap(ProvinceCity::getCityCode, Function.identity()));


        // 收集器多层嵌套：
//        provinceCityList.stream()
//                .collect(Collectors.toMap(ProvinceCity::getCityCode, Collectors.collectingAndThen(ProvinceCity::getCityCode,Function.identity())));
        // 收集为Map<>
        Map<Integer, Map<Integer, String>> collect2 = provinceCityList.stream()
                .collect(Collectors.groupingBy(ProvinceCity::getProvinceCode, Collectors.toMap(ProvinceCity::getCityCode, ProvinceCity::getCityName)));


        // 收集器多层转换

        // 收集并统计
        Map<Integer, Map<Boolean, Long>> collect3 = provinceCityList.stream()
                .collect(Collectors.groupingBy(ProvinceCity::getProvinceCode, Collectors.groupingBy(ProvinceCity::getProvinceCapital, Collectors.counting())));

    }
}

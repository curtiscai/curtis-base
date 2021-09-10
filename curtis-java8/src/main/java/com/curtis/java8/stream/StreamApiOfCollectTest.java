package com.curtis.java8.stream;

import com.curtis.java8.model.ProvinceCity;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    public void testStreamApiOfCollect(){
        List<ProvinceCity> provinceCityList = Lists.newArrayList(
                new ProvinceCity(130000,"河北省",130800,"承德市"),
                new ProvinceCity(130000,"河北省",130600,"保定市"),
                new ProvinceCity(130000,"河北省",130100,"石家庄市"),
                new ProvinceCity(140000,"山西省",140100,"太原市"),
                new ProvinceCity(140000,"山西省",140300,"阳泉市"),
                new ProvinceCity(140000,"山西省",140900,"忻州市")
        );

        Map<Integer, String> cityCodeNameMap = provinceCityList.stream().collect(Collectors.toMap(ProvinceCity::getCityCode, ProvinceCity::getCityName));

        Map<Integer, List<ProvinceCity>> collect = provinceCityList.stream().collect(Collectors.groupingBy(ProvinceCity::getProvinceCode, Collectors.toList()));


//        R collect1 = provinceCityList.stream().collect(Collectors.toMap(ProvinceCity::getCityCode, Collectors.toMap(ProvinceCity::getCityCode, ProvinceCity::getCityName)));


    }
}

package com.curtis.guava.stream;

import com.curtis.guava.model.City;
import com.curtis.guava.model.Province;
import com.curtis.guava.model.User;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author curtis.cai
 * @desc Java8 Stream Api Of Map And FlatMap
 * @date 2021-09-10
 * @email curtis.cai@outlook.com
 * @reference
 */
public class StreamApiOfMapTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamApiOfMapTest.class);

    /**
     * List<List<>>中元素合并为List<>
     */
    @Test
    public void testStreamApiOfMap() {
        List<User> sourceList1 = Lists.newArrayList(new User(1, "curtis1", 15100001004L, false, BigDecimal.valueOf(180.3)),
                new User(2, "curtis2", 15100001003L, false, BigDecimal.valueOf(180.2)),
                null,
                new User(3, "curtis3", 15100001002L, true, null),
                null,
                new User(4, "curtis4", 15100001001L, true, BigDecimal.valueOf(180.1)));

        List<User> sourceList2 = Lists.newArrayList(new User(5, "curtis5", 15100001004L, false, BigDecimal.valueOf(180.6)),
                new User(6, "curtis6", 15100001003L, false, BigDecimal.valueOf(180.8)),
                null,
                new User(7, "curtis7", 15100001002L, true, null),
                null,
                new User(8, "curtis8", 15100001001L, true, BigDecimal.valueOf(180.5)));

        List<List<User>> sourceListList = Lists.newArrayList(sourceList1, sourceList2);

        List<User> userList = sourceListList.stream().flatMap(Collection::stream).collect(Collectors.toList());
        // 20:22:48,465  INFO StreamApiOfMapTest:43 - userList -> [User{id=1, name='curtis1', phone=15100001004, sex=false, height=180.3},
        // User{id=2, name='curtis2', phone=15100001003, sex=false, height=180.2}, null,
        // User{id=3, name='curtis3', phone=15100001002, sex=true, height=null}, null,
        // User{id=4, name='curtis4', phone=15100001001, sex=true, height=180.1},
        // User{id=5, name='curtis5', phone=15100001004, sex=false, height=180.6},
        // User{id=6, name='curtis6', phone=15100001003, sex=false, height=180.8}, null,
        // User{id=7, name='curtis7', phone=15100001002, sex=true, height=null}, null,
        // User{id=8, name='curtis8', phone=15100001001, sex=true, height=180.5}]
        LOGGER.info("userList -> {}", userList);
    }

    /**
     * 合并List<>属性为新的List
     */
    @Test
    public void testStreamApiOfMap2() {
        List<Province> sourceList = Lists.newArrayList(
                new Province(130000,"河北省",
                        Lists.newArrayList(new City(130800,"承德市"),
                                new City(130600,"保定市"),
                                new City(130100,"石家庄市"))),
                new Province(140000,"山西省",
                        Lists.newArrayList(new City(140100,"太原市"),
                                new City(140300,"阳泉市"),
                                new City(140900,"忻州市")))
        );
        LOGGER.info("sourceList -> {}", sourceList);

        List<City> cityList1 = sourceList.stream().flatMap(item -> item.getCityList().stream())
                .sorted(Comparator.comparing(City::getCityCode)).collect(Collectors.toList());
        // 20:33:33,716  INFO StreamApiOfMapTest:80 - cityList1 -> [
        // City{cityCode=130100, cityName='石家庄市'},
        // City{cityCode=130600, cityName='保定市'},
        // City{cityCode=130800, cityName='承德市'},
        // City{cityCode=140100, cityName='太原市'},
        // City{cityCode=140300, cityName='阳泉市'},
        // City{cityCode=140900, cityName='忻州市'}]
        LOGGER.info("cityList1 -> {}", cityList1);

        List<City> cityList2 = sourceList.stream().map(Province::getCityList).flatMap(Collection::stream)
                .sorted(Comparator.comparing(City::getCityCode)).collect(Collectors.toList());
        // 20:30:15,097  INFO StreamApiOfMapTest:80 - cityList -> [
        // City{cityCode=130100, cityName='石家庄市'},
        // City{cityCode=130600, cityName='保定市'},
        // City{cityCode=130800, cityName='承德市'},
        // City{cityCode=140100, cityName='太原市'},
        // City{cityCode=140300, cityName='阳泉市'},
        // City{cityCode=140900, cityName='忻州市'}]
        LOGGER.info("cityList2 -> {}", cityList2);
    }
}

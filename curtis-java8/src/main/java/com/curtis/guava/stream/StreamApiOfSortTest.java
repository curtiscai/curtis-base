package com.curtis.guava.stream;

import com.curtis.guava.model.City;
import com.curtis.guava.model.Province;
import com.curtis.guava.model.User;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author curtis.cai
 * @desc Java8 Stream Api Of Sorted
 * @date 2021-09-10
 * @email curtis.cai@outlook.com
 * @reference https://javadevcentral.com/comparator-nullsfirst-and-nullslast
 * @reference https://www.cnblogs.com/kuanglongblogs/p/11230250.html
 */
public class StreamApiOfSortTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamApiOfSortTest.class);

    @Test
    public void testStreamApiOfSort() {
        List<Integer> sourceList = Lists.newArrayList(1, 4, 3, 6, null, 9, 2, 5, 4, 4, null);
        // 20:15:27,586  INFO StreamApiOfSortTest:34 - sourceList -> [1, 4, 3, 6, null, 9, 2, 5, 4, 4, null]
        LOGGER.info("sourceList -> {}", sourceList);


//        List<Integer> sortedIntList11 = sourceList.stream().sorted().collect(Collectors.toList());
//        // java.lang.NullPointerException
//        LOGGER.info("sortedIntList1 -> {}", sortedIntList11);
//        List<Integer> sortedIntList12 = sourceList.stream().sorted(Comparator.comparing(Functions.identity())).collect(Collectors.toList());
//        // java.lang.NullPointerException
//        LOGGER.info("sortedIntList2 -> {}", sortedIntList12);

        // 升序排列（需要过滤空值）
        List<Integer> sortedIntList21 = sourceList.stream().filter(Objects::nonNull).sorted().collect(Collectors.toList());
        // 20:15:27,591  INFO StreamApiOfSortTest:47 - sortedIntList21 -> [1, 2, 3, 4, 4, 4, 5, 6, 9]
        LOGGER.info("sortedIntList21 -> {}", sortedIntList21);
        List<Integer> sortedIntList22 = sourceList.stream().filter(Objects::nonNull).sorted(Comparator.comparing(Functions.identity())).collect(Collectors.toList());
        // 20:15:27,592  INFO StreamApiOfSortTest:50 - sortedIntList22 -> [1, 2, 3, 4, 4, 4, 5, 6, 9]
        LOGGER.info("sortedIntList22 -> {}", sortedIntList22);
        List<Integer> sortedIntList23 = sourceList.stream().filter(Objects::nonNull).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        // 20:15:27,593  INFO StreamApiOfSortTest:53 - sortedIntList23 -> [9, 6, 5, 4, 4, 4, 3, 2, 1]
        LOGGER.info("sortedIntList23 -> {}", sortedIntList23);


        // 升序排列，空值放在最前或者最后
        List<Integer> sortedIntList31 = sourceList.stream().sorted(Comparator.nullsFirst(Comparator.comparing(Functions.identity()))).collect(Collectors.toList());
        // 20:15:27,593  INFO StreamApiOfSortTest:59 - sortedIntList31 -> [null, null, 1, 2, 3, 4, 4, 4, 5, 6, 9]
        LOGGER.info("sortedIntList31 -> {}", sortedIntList31);
        List<Integer> sortedIntList32 = sourceList.stream().sorted(Comparator.nullsLast(Comparator.comparing(Functions.identity()))).collect(Collectors.toList());
        // 20:15:27,593  INFO StreamApiOfSortTest:62 - sortedIntList32 -> [1, 2, 3, 4, 4, 4, 5, 6, 9, null, null]
        LOGGER.info("sortedIntList32 -> {}", sortedIntList32);


//        List<Integer> sortedIntList41 = sourceList.stream().sorted(Comparator.nullsFirst(Comparator.comparing(Functions.identity(),Comparator.reverseOrder()))).collect(Collectors.toList());
//        // 13:43:10,670  INFO StreamAPITest:44 - sortedIntList31 -> [null, null, 1, 2, 3, 4, 4, 4, 5, 6, 9]
//        LOGGER.info("sortedIntList41 -> {}", sortedIntList41);
//        List<Integer> sortedIntList42 = sourceList.stream().sorted(Comparator.nullsLast(Comparator.comparing(Functions.identity(),Comparator.reverseOrder()))).collect(Collectors.toList());
//        // 13:43:10,670  INFO StreamAPITest:47 - sortedIntList32 -> [1, 2, 3, 4, 4, 4, 5, 6, 9, null, null]
//        LOGGER.info("sortedIntList42 -> {}", sortedIntList42);

    }

    @Test
    public void testStreamApiOfSort2() {
        List<User> sourceList = Lists.newArrayList(new User(1, "curtis1", 15100001004L, false, BigDecimal.valueOf(180.3)),
                new User(2, "curtis2", 15100001003L, false, BigDecimal.valueOf(180.2)),
                null,
                new User(3, "curtis3", 15100001002L, true, null),
                null,
                new User(4, "curtis4", 15100001001L, true, BigDecimal.valueOf(180.1)));
        LOGGER.info("sourceList -> {}", sourceList);


        // 按手机号升序排列，但是有空对象，报空指针：java.lang.NullPointerException
//        List<User> sortedList11 = sourceList.stream().sorted(Comparator.comparing(User::getPhone)).collect(Collectors.toList());
//        // java.lang.NullPointerException
//        LOGGER.info("sortedList11 -> {}", sortedList11);
        // 按身高升序排列， 没有空对象，但是排序字段为空，报空指针：java.lang.NullPointerException
//        List<User> sortedList12 = sourceList.stream().filter(Objects::nonNull).sorted(Comparator.comparing(User::getHeight)).collect(Collectors.toList());
//        // java.lang.NullPointerException
//        LOGGER.info("sortedList12 -> {}", sortedList12);


        // 升序排列（需要过滤空值）
        List<User> sortedList21 = sourceList.stream().filter(Objects::nonNull)
                .sorted(Comparator.comparing(User::getPhone)).collect(Collectors.toList());
        // 14:16:23,029  INFO StreamAPITest:85 - sortedList21 -> [User{id=4, name='curtis4', phone=15100001001, sex=null, height=180.1},
        // User{id=3, name='curtis3', phone=15100001002, sex=true, height=null},
        // User{id=2, name='curtis2', phone=15100001003, sex=false, height=180.2},
        // User{id=1, name='curtis1', phone=15100001004, sex=false, height=180.3}]
        LOGGER.info("sortedList21 -> {}", sortedList21);
        List<User> sortedList22 = sourceList.stream().filter(Objects::nonNull).filter(item -> item.getHeight() != null)
                .sorted(Comparator.comparing(User::getHeight)).collect(Collectors.toList());
        // 14:16:23,030  INFO StreamAPITest:88 - sortedList22 -> [User{id=4, name='curtis4', phone=15100001001, sex=null, height=180.1},
        // User{id=2, name='curtis2', phone=15100001003, sex=false, height=180.2},
        // User{id=1, name='curtis1', phone=15100001004, sex=false, height=180.3}]
        LOGGER.info("sortedList22 -> {}", sortedList22);


//        sourceList.sort(Comparator.nullsFirst(Comparator.comparing(User::getHeight)));
//        System.out.println(sourceList);
        // 升序排列，空值放在最前或者最后，由于使用nullsFirst或者nullsLast所以不需要过滤空值
        List<User> sortedList31 = sourceList.stream()
                .sorted(Comparator.nullsFirst(Comparator.comparing(User::getPhone))).collect(Collectors.toList());
        // 15:49:05,646  INFO StreamAPITest:101 - sortedIntList31 -> [null, null, User{id=4, name='curtis4', phone=15100001001, sex=null, height=180.1},
        // User{id=3, name='curtis3', phone=15100001002, sex=true, height=null},
        // User{id=2, name='curtis2', phone=15100001003, sex=false, height=180.2},
        // User{id=1, name='curtis1', phone=15100001004, sex=false, height=180.3}]
        LOGGER.info("sortedIntList31 -> {}", sortedList31);


        // 在这里Comparator.nullsFirst代表将集合中为null的元素排在前面，非null的参与排序，但是有的元素不为null而排序字段却有为null的，所以报空指针
//        List<User> sortedList32 = sourceList.stream().sorted(Comparator.nullsFirst(Comparator.comparing(User::getHeight))).collect(Collectors.toList());
//        // java.lang.NullPointerException
//        LOGGER.info("sortedIntList32 -> {}", sortedList32);


        // 升序排列，过滤空元素并优雅处理元素的空属性
        List<User> sortedList33 = sourceList.stream().filter(Objects::nonNull)
                .sorted(Comparator.comparing(User::getHeight, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
        // 15:49:05,647  INFO StreamAPITest:104 - sortedIntList32 -> [User{id=4, name='curtis4', phone=15100001001, sex=null, height=180.1},
        // User{id=2, name='curtis2', phone=15100001003, sex=false, height=180.2},
        // User{id=1, name='curtis1', phone=15100001004, sex=false, height=180.3},
        // User{id=3, name='curtis3', phone=15100001002, sex=true, height=null}, null, null]
        LOGGER.info("sortedList33 -> {}", sortedList33);


        // 升序排列，并优雅处理空元素和元素的空属性
        List<User> sortedList34 = sourceList.stream()
                .sorted(Comparator.nullsLast(Comparator.comparing(User::getHeight, Comparator.nullsLast(Comparator.naturalOrder()))))
                .collect(Collectors.toList());
        // 15:49:05,647  INFO StreamAPITest:104 - sortedIntList32 -> [User{id=4, name='curtis4', phone=15100001001, sex=null, height=180.1},
        // User{id=2, name='curtis2', phone=15100001003, sex=false, height=180.2},
        // User{id=1, name='curtis1', phone=15100001004, sex=false, height=180.3},
        // User{id=3, name='curtis3', phone=15100001002, sex=true, height=null}, null, null]
        LOGGER.info("sortedList34 -> {}", sortedList34);


        // 降序排列，并优雅处理空元素和元素的空属性
        List<User> sortedList35 = sourceList.stream()
                .sorted(Comparator.nullsLast(Comparator.comparing(User::getHeight, Comparator.nullsLast(Comparator.naturalOrder())).reversed()))
                .collect(Collectors.toList());
        // 19:14:00,816  INFO StreamAPITest:141 - sortedList35 -> [User{id=3, name='curtis3', phone=15100001002, sex=true, height=null},
        // User{id=1, name='curtis1', phone=15100001004, sex=false, height=180.3},
        // User{id=2, name='curtis2', phone=15100001003, sex=false, height=180.2},
        // User{id=4, name='curtis4', phone=15100001001, sex=true, height=180.1},
        // null, null]
        LOGGER.info("sortedList35 -> {}", sortedList35);


        // 连续排序，并优雅处理空元素和元素的空属性
        List<User> sortedList36 = sourceList.stream()
                .sorted(Comparator.nullsLast(Comparator.comparing(User::getSex)).reversed().thenComparing(User::getHeight, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
        // 19:11:47,047  INFO StreamAPITest:141 - sortedList35 -> [null, null,
        // User{id=4, name='curtis4', phone=15100001001, sex=true, height=180.1},
        // User{id=3, name='curtis3', phone=15100001002, sex=true, height=null},
        // User{id=2, name='curtis2', phone=15100001003, sex=false, height=180.2},
        // User{id=1, name='curtis1', phone=15100001004, sex=false, height=180.3}]
        LOGGER.info("sortedList36 -> {}", sortedList36);
    }

    @Test
    public void testStreamApiOfSort3() {
        List<Province> sourceList = Lists.newArrayList(
                new Province(130000, "河北省",
                        Lists.newArrayList(new City(130800, "承德市"),
                                new City(130600, "保定市"),
                                new City(130100, "石家庄市"))),
                new Province(140000, "山西省",
                        Lists.newArrayList(new City(140100, "太原市"),
                                new City(140300, "阳泉市"),
                                new City(140900, "忻州市")))
        );
        LOGGER.info("sourceList -> {}", sourceList);

        // 升序排列（需要过滤空值）
//        List<Province> sortedList21 = sourceList.stream().filter(Objects::nonNull)
//                .sorted(Comparator.comparing(Province::getProvinceCode).thenComparing(Comparator.comparing(Province::getCityList,Comparator.comparing(City::getCityCode)))).collect(Collectors.toList());
        // 14:16:23,029  INFO StreamAPITest:85 - sortedList21 -> [User{id=4, name='curtis4', phone=15100001001, sex=null, height=180.1},
        // User{id=3, name='curtis3', phone=15100001002, sex=true, height=null},
        // User{id=2, name='curtis2', phone=15100001003, sex=false, height=180.2},
        // User{id=1, name='curtis1', phone=15100001004, sex=false, height=180.3}]


    }
}

package com.curtis.apache.lang3;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-25
 * @email curtis.cai@outlook.com
 * @reference
 */
public class StringUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtilsTest.class);

    @Test
    public void testStringUtils() {
        // isNotBlank(final CharSequence cs)：判断指定字符串是否是NULL、空串或者空白字符
        boolean isNotBlank1 = StringUtils.isNotBlank(null);
        boolean isNotBlank2 = StringUtils.isNotBlank("");
        boolean isNotBlank3 = StringUtils.isNotBlank("  ");
        // 23:00:19,017  INFO StringUtilsTest:26 - isNotBlank1 -> false, isNotBlank1 -> false, isNotBlank1 -> false
        LOGGER.info("isNotBlank1 -> {}, isNotBlank1 -> {}, isNotBlank1 -> {}", isNotBlank1, isNotBlank2, isNotBlank3);

        // isNotEmpty(final CharSequence cs)：判断指定字符串是否是NULL、空串(不校验空白字符)
        boolean isNotEmpty1 = StringUtils.isNotEmpty(null);
        boolean isNotEmpty2 = StringUtils.isNotEmpty("");
        boolean isNotEmpty3 = StringUtils.isNotEmpty("  ");
        // 23:00:19,027  INFO StringUtilsTest:32 - isNotEmpty1 -> false, isNotEmpty2 -> false, isNotEmpty3 -> true
        LOGGER.info("isNotEmpty1 -> {}, isNotEmpty2 -> {}, isNotEmpty3 -> {}", isNotEmpty1, isNotEmpty2, isNotEmpty3);

        // 字符串分隔和连接
        // split(final String str, final String separatorChars)：字符串分隔
        // join(final Iterable<?> iterable, final String separator)：字符串连接
        String[] split = StringUtils.split("a&b&c", "&");
        // 23:11:19,951  INFO StringUtilsTest:41 - split -> [a, b, c]
        LOGGER.info("split -> {}", Arrays.toString(split));
        String join = StringUtils.join(Lists.newArrayList("a", "b", "c"), "&");
        // 23:11:19,961  INFO StringUtilsTest:44 - join -> a&b&c
        LOGGER.info("join -> {}", join);

        // 判断字符串是否以指定字符串或者指定字符串集合结尾
        // endsWith(final CharSequence str, final CharSequence suffix)：判断字符串是否以指定字符串结尾
        // endsWithIgnoreCase(final CharSequence str, final CharSequence suffix)：判断字符串是否以指定字符串结尾（忽略大小写）
        // endsWithAny(final CharSequence sequence, final CharSequence... searchStrings)：判断字符串是否以指定字符串集合结尾
        boolean endsWith = StringUtils.endsWith("test.txt.xlsx", "xlsx");
        LOGGER.info("endsWith -> {}", endsWith);
        boolean endsWithIgnoreCase = StringUtils.endsWithIgnoreCase("test.txt.xlsx", "XLSX");
        LOGGER.info("endsWithIgnoreCase -> {}", endsWithIgnoreCase);
        boolean endsWithAny1 = StringUtils.endsWithAny("abcxyz", new String[]{null, "xyz", "abc"});
        LOGGER.info("endsWithAny1 -> {}", endsWithAny1);
        boolean endsWithAny2 = StringUtils.endsWithAny("xyzabc", new String[]{null, "xyz", "abc"});
        LOGGER.info("endsWithAny2 -> {}", endsWithAny2);

        // 判断字符串是否包含指定字符串或者字符串集合
        // contains(final CharSequence seq, final CharSequence searchSeq)：判断字符串是否包含指定字符串
        // containsAnyIgnoreCase(final CharSequence cs, final CharSequence... searchCharSequences)：判断字符串是否包含指定字符串（忽略大小写）
        // containsAny(final CharSequence cs, final CharSequence... searchCharSequences)：判断字符串是否包含指定字符串集合
        boolean contains = StringUtils.contains("abc", "a");
        // 23:29:03,855  INFO StringUtilsTest:57 - contains -> true
        LOGGER.info("contains -> {}", contains);
        boolean containsAnyIgnoreCase = StringUtils.containsAnyIgnoreCase("XABCX", new String[]{null, "xyz", "abc"});
        // 23:29:27,189  INFO StringUtilsTest:62 - containsAnyIgnoreCase -> true
        LOGGER.info("containsAnyIgnoreCase -> {}", containsAnyIgnoreCase);
        boolean containsAny = StringUtils.containsAny("XabcX", new String[]{null, "xyz", "abc"});
        // 23:29:27,189  INFO StringUtilsTest:60 - containsAny -> true
        LOGGER.info("containsAny -> {}", containsAny);


        // 判断指定字符串是否与另外字符串或字符串集合中的一个相同
        // equals(final CharSequence cs1, final CharSequence cs2)：判断指定字符串是否与另外字符串相同
        // equalsAny(final CharSequence string, final CharSequence... searchStrings)：判断指定字符串是否与另外字符串集合中的一个相同
        // equalsAnyIgnoreCase(final CharSequence string, final CharSequence...searchStrings)：判断指定字符串是否与另外字符串相同（忽略大小写）
        boolean equals = StringUtils.equals("abc", "AbC");
        // 23:33:08,623  INFO StringUtilsTest:67 - equals -> false
        LOGGER.info("equals -> {}", equals);
        boolean equalsAnyIgnoreCase = StringUtils.equalsAnyIgnoreCase("abc", "AbC");
        // 23:33:08,623  INFO StringUtilsTest:71 - equalsAnyIgnoreCase -> true
        LOGGER.info("equalsAnyIgnoreCase -> {}", equalsAnyIgnoreCase);
        boolean equalsAny = StringUtils.equalsAny("abc", new String[]{null, "xyz", "abc"});
        // 23:33:08,623  INFO StringUtilsTest:69 - equalsAny -> true
        LOGGER.info("equalsAny -> {}", equalsAny);

        // 将指定文本中的指定字符串替换为其他字符串
        String replace = StringUtils.replace("aba", "a", null);
        String replace1 = StringUtils.replace("aba", "a", "");
        String s = StringUtils.replaceIgnoreCase("abA", "A", "");


        boolean ab2c = StringUtils.isAlpha("ab2c");
        StringUtils.isNumeric("12.3");
         StringUtils.isNumeric("-123");


        String s1 = StringUtils.substringAfter("abcba", "b");
        String s2 = StringUtils.substringAfter("abc", "c");
        String s8 = StringUtils.substringAfterLast("abcba", "b");
        String s9 = StringUtils.substringBefore("abcba", "b");
        String s10 = StringUtils.substringBeforeLast("abcba", "b");


        String s3 = StringUtils.stripToNull(" ab c ");
        String s11 = StringUtils.stripToNull("");
        String s12 = StringUtils.stripToNull("   ");
        String abc1 = StringUtils.stripToNull("abc");
        String s13 = StringUtils.stripToNull("  abc");
        String abc__ = StringUtils.stripToNull("abc  ");
        String s14 = StringUtils.stripToNull(" abc ");

        String s4 = StringUtils.trimToNull(null);
        String s5 = StringUtils.trimToNull("");
        String s6 = StringUtils.trimToNull("     ");
        String abc = StringUtils.trimToNull("abc");
        String s7 = StringUtils.trimToNull("    abc    ");

        byte[] byteList = StringUtils.getBytes("中国", StandardCharsets.UTF_8);
        String s15 = StringUtils.toEncodedString(byteList, StandardCharsets.UTF_8);


        //大小写转换
        StringUtils.upperCase("aBc");//---"ABC"
        StringUtils.lowerCase("aBc");//---"abc"



    }
}

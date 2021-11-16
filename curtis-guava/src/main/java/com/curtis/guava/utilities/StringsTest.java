package com.curtis.guava.utilities;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-09-15
 * @email curtis.cai@outlook.com
 * @reference
 */
public class StringsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringsTest.class);

    @Test
    public void testEmptyNull() {
        Assert.assertEquals(Strings.emptyToNull(""), null);
        Assert.assertEquals(Strings.emptyToNull(" "), " ");
        Assert.assertEquals(Strings.emptyToNull(null), null);

        Assert.assertEquals(Strings.nullToEmpty(null), "");
        Assert.assertEquals(Strings.nullToEmpty(""), "");

        Assert.assertEquals(Strings.isNullOrEmpty(""), true);
        Assert.assertEquals(Strings.isNullOrEmpty(null), true);
        Assert.assertEquals(Strings.isNullOrEmpty(" "), false);
    }

    @Test
    public void testPadStartOrEnd() {
        Assert.assertThat(Strings.padStart("11", 4, '0'), IsEqual.equalTo("0011"));
        Assert.assertThat(Strings.padStart("11111", 4, '0'),IsEqual.equalTo("11111"));

        Assert.assertThat(Strings.padEnd("22", 4, '0'),IsEqual.equalTo("2200"));
        Assert.assertThat(Strings.padStart("22222", 4, '0'),IsEqual.equalTo("22222"));
    }

    @Test
    public void testCharsets() {
        Charset charset = Charset.forName("UTF-8");
        Charset guavaUtf8 = Charsets.UTF_8;
        Charset javaUtf8 = StandardCharsets.UTF_8;
    }

    @Test
    public void testCharsets2() {
        Assert.assertThat(CharMatcher.inRange('0', '9').matchesAllOf("1209783"),IsEqual.equalTo(true));
        Assert.assertThat(CharMatcher.inRange('0', '9').matchesAllOf("1209x783"),IsEqual.equalTo(false));


        Assert.assertThat(CharMatcher.breakingWhitespace().removeFrom("   Hello World    "),IsEqual.equalTo("HelloWorld"));

        String s2 = CharMatcher.whitespace().or(CharMatcher.inRange('0', '9')).retainFrom("xxx1 23 4 xx 2 ");
        System.out.println(s2);
    }
}

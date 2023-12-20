package me.Yukimi_Ywx.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static me.Yukimi_Ywx.utils.StringUtils.getIp;
import static me.Yukimi_Ywx.utils.StringUtils.getWeekDay;
import static me.Yukimi_Ywx.utils.StringUtils.toCamelCase;
import static me.Yukimi_Ywx.utils.StringUtils.toCapitalizeCamelCase;
import static me.Yukimi_Ywx.utils.StringUtils.toUnderScoreCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringUtilsTest {

    @Test
    public void testToCamelCase() {
        assertNull(toCamelCase(null));
    }

    @Test
    public void testToCapitalizeCamelCase() {
        assertNull(StringUtils.toCapitalizeCamelCase(null));
        assertEquals("HelloWorld", toCapitalizeCamelCase("hello_world"));
    }

    @Test
    public void testToUnderScoreCase() {
        assertNull(StringUtils.toUnderScoreCase(null));
        assertEquals("hello_world", toUnderScoreCase("helloWorld"));
        assertEquals("\u0000\u0000", toUnderScoreCase("\u0000\u0000"));
        assertEquals("\u0000_a", toUnderScoreCase("\u0000A"));
    }

    @Test
    public void testGetWeekDay() {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
        assertEquals(simpleDateformat.format(new Date()), getWeekDay());
    }

    @Test
    public void testGetIP() {
        assertEquals("127.0.0.1", getIp(new MockHttpServletRequest()));
    }
}

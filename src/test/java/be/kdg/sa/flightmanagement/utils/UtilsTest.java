package be.kdg.sa.flightmanagement.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

/**
 * @author LukasVandenBossche on 06/10/2021
 * @project flightmanagement
 */

@SpringBootTest
class UtilsTest {
    @Test
    void testCalculateDate() {
        assertEquals(
                new Date(2021-1900, Calendar.JANUARY, 9, 12, 0),
                Utils.calculateDate(
                        new Date(2021-1900, Calendar.JANUARY, 9, 13, 45),
                        "09", "1200"));
        assertEquals(
                new Date(2021-1900, Calendar.JANUARY, 9, 23, 55),
                Utils.calculateDate(
                        new Date(2021-1900, Calendar.JANUARY, 10, 0, 2),
                        "09", "2355"));
        assertEquals(
                new Date(2021-1900, Calendar.JANUARY, 9, 0, 15),
                Utils.calculateDate(
                        new Date(2021-1900, Calendar.JANUARY, 8, 23, 58),
                        "09", "0015"));
        assertEquals(
                new Date(2021-1900, Calendar.JANUARY, 10, 0, 15),
                Utils.calculateDate(
                        new Date(2021-1900, Calendar.JANUARY, 9, 23, 58),
                        "09", "0015"));
    }
}
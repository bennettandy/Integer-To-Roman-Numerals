package com.avsoftware.integertoromannumerals

import com.avsoftware.integertoromannumerals.roman.integerToRoman
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RomanNumeralGeneratorTest {
    @Test
    fun test_decimal_to_roman() {

        assertEquals(
            "MMXXV", integerToRoman(value = 2025)
        )

        assertEquals(
            "CXX", integerToRoman(value = 120)
        )

        assertEquals(
            "XXIII", integerToRoman(value = 23)
        )

        assertEquals(
            "XLII", integerToRoman(value = 42)
        )

        assertEquals(
            "I", integerToRoman(value = 1)
        )

        assertEquals(
            "", integerToRoman(value = 0)
        )
    }

}
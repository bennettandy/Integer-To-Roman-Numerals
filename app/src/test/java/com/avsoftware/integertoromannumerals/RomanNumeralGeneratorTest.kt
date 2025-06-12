package com.avsoftware.integertoromannumerals

import com.avsoftware.integertoromannumerals.roman.integerToRoman
import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.Assertions.assertEquals


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RomanNumeralGeneratorTest : FunSpec({

    test("test integerToRoman function") {

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

        // todo: fix this case
//        assertEquals(
//            "", integerToRoman(value = 0)
//        )
    }

})

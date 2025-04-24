package com.avsoftware.integertoromannumerals.roman

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class RomanDigitTest : FunSpec({

    test("romanDigits list should contain expected entries") {
        romanDigits.size shouldBe 28 // Number of entries in the list
        romanDigits.take(3).map { it.decimalValue to it.romanDigit } shouldContainExactly listOf(
            1_000_000_000 to "(M)",
            500_000_000 to "(D)",
            100_000_000 to "(C)"
        )
    }

    test("romanDigits should be sorted in descending order") {
        romanDigits.map { it.decimalValue } shouldBe romanDigits.map { it.decimalValue }.sortedDescending()
    }
})
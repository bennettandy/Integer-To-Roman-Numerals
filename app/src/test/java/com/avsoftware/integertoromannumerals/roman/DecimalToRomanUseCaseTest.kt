package com.avsoftware.integertoromannumerals.roman

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DecimalToRomanUseCaseTest : FunSpec({

    // Instantiate the use case directly (no external dependencies in this case)
    val useCase: DecimalToRomanUseCase = DecimalToRomanImpl()

    test("should convert positive decimal strings to Roman numerals") {
        useCase(1) shouldBe "I"
        useCase(23) shouldBe "XXIII"
        useCase(42) shouldBe "XLII"
        useCase(120) shouldBe "CXX"
        useCase(2025) shouldBe "MMXXV"
    }

    test("should return empty string for zero") {
        useCase(0) shouldBe ""
    }

    test("should return empty string for negative numbers") {
        useCase(-1) shouldBe ""
    }

    test("should handle large numbers correctly") {
        useCase(1000) shouldBe "M"
        useCase(500000) shouldBe "D̅"
        useCase(1000000) shouldBe "(I)"
    }

})
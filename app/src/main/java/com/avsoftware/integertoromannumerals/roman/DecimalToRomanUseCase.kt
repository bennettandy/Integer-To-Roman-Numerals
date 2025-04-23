package com.avsoftware.integertoromannumerals.roman

import javax.inject.Inject

interface DecimalToRomanUseCase {
    operator fun invoke(decimal: Long): String
}

internal class DecimalToRomanImpl @Inject constructor(): DecimalToRomanUseCase {

    override fun invoke(decimal: Long): String {
        return decimal.takeIf { it > 0 }?.let { integerToRoman(it) } ?: ""
    }
}
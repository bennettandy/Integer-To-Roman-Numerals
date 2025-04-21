package com.avsoftware.integertoromannumerals.roman

import javax.inject.Inject

interface DecimalToRomanUseCase {
    operator fun invoke(decimalString: String): String
}


internal class DecimalToRomanImpl @Inject constructor(): DecimalToRomanUseCase {

    override fun invoke(decimalString: String): String {
        return decimalString.toLongOrNull()?.takeIf { it > 0 }?.let { integerToRoman(it) } ?: ""
    }
}
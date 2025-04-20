package com.avsoftware.integertoromannumerals.roman

data class RomanDigit(val decimalValue: Int, val romanDigit: String)

val romanDigits = listOf(
    RomanDigit(1000, "M"),
    RomanDigit(500, "D"),
    RomanDigit(100, "C"),
    RomanDigit(50, "L"),
    RomanDigit(10, "X"),
    RomanDigit(9, "IX"),
    RomanDigit(8, "VIII"),
    RomanDigit(7, "VII"),
    RomanDigit(6, "VI"),
    RomanDigit(5, "V"),
    RomanDigit(4, "IV"),
    RomanDigit(3, "III"),
    RomanDigit(2, "II"),
    RomanDigit(1, "I"),
)

fun integerToRoman(value: Int, acc: String = ""): String {

    if (value == 0) return acc

    val digit = romanDigits.firstOrNull() { it.decimalValue <= value }

    return integerToRoman(value - digit!!.decimalValue, acc + digit.romanDigit)
}


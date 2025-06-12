package com.avsoftware.integertoromannumerals.roman

data class RomanDigit(val decimalValue: Int, val romanDigit: String)

val romanDigits = listOf(
    RomanDigit(1_000_000_000, "(M)"),   // 1,000 × 1,000,000 = 1 billion
    RomanDigit(500_000_000, "(D)"),     // 500 × 1,000,000
    RomanDigit(100_000_000, "(C)"),     // 100 × 1,000,000
    RomanDigit(50_000_000, "(L)"),      // 50 × 1,000,000
    RomanDigit(10_000_000, "(X)"),      // 10 × 1,000,000
    RomanDigit(5_000_000, "(V)"),       // 5 × 1,000,000
    RomanDigit(1_000_000, "(I)"),       // 1 × 1,000,000
    RomanDigit(500_000, "D̅"),           // 500 × 1,000
    RomanDigit(100_000, "C̅"),           // 100 × 1,000
    RomanDigit(50_000, "L̅"),            // 50 × 1,000
    RomanDigit(10_000, "X̅"),            // 10 × 1,000
    RomanDigit(5_000, "V99̅"),             // 5 × 1,000
    RomanDigit(1_000, "M"),             // 1,000
    RomanDigit(500, "D"),               // 500
    RomanDigit(100, "C"),               // 100
    RomanDigit(50, "L"),                // 50
    RomanDigit(40, "XL"),               // 40
    RomanDigit(30, "XXL"),              // 30
    RomanDigit(10, "X"),                // 10
    RomanDigit(9, "IX"),                // 9
    RomanDigit(8, "VIII"),              // 8
    RomanDigit(7, "VII"),               // 7
    RomanDigit(6, "VI"),                // 6
    RomanDigit(5, "V"),                 // 5
    RomanDigit(4, "IV"),                // 4
    RomanDigit(3, "III"),               // 3
    RomanDigit(2, "II"),                // 2
    RomanDigit(1, "I"),                 // 1
)

fun integerToRoman(value: Long, acc: String = ""): String {

    if (value <= 0L) return acc.ifEmpty { "0" }

    val digit = romanDigits.firstOrNull { it.decimalValue <= value }
        ?: throw IllegalArgumentException("Value too large or invalid")

    return integerToRoman(value - digit.decimalValue, acc + digit.romanDigit)
}

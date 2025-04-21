package com.avsoftware.integertoromannumerals

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength

class ExampleTest : FunSpec({

    test("my first test") {
        1 + 2 shouldBe 3
    }

})

class NestedTestExamples : DescribeSpec({

    describe("an outer test") {

        it("an inner test") {
            1 + 2 shouldBe 3
        }

        it("an inner test too!") {
            3 + 4 shouldBe 7
        }
    }

})

class DynamicTests : FunSpec({

    listOf(
        "sam",
        "pam",
        "tim",
    ).forEach {
        test("$it should be a three letter name") {
            it.shouldHaveLength(3)
        }
    }
})

class Callbacks : FunSpec({

    beforeEach {
        println("Hello from $it")
    }

    test("sam should be a three letter name") {
        "sam".shouldHaveLength(3)
    }

    afterEach {
        println("Goodbye from $it")
    }
})
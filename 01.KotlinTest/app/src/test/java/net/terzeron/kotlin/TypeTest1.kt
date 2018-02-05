package net.terzeron.kotlin

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TypeTest1 {
    @Test
    fun testHelloWorld() {
        println("Hello world!!!")
    }

    @Test
    fun testTypes() {
        val immutable: Int = 10
        var mutable: String = "mutable"
        mutable = "other value"

        val inferredValue = "String value"
        val nullable: String? = null
        val funcType: (Int) -> String = { x -> x.toString() }
        println(funcType(10))
        val list: List<Int> = listOf(1, 2, 3)
        println(list)
        val mutableList: MutableList<Int> = mutableListOf(1, 2, 3)
        val arr: Array<Int> = arrayOf(10, 20, 30)
        println(arr)
        val set: Set<Int> = setOf(1, 2, 1, 2, 3, 1, 2, 4)
        println(set)
        val pair: Pair<String, Double> = "key" to 10.0
        println(pair)
        val map: Map<String, Int> = mapOf(
                "one" to 1,
                "two" to 2
        )
        println(map)
    }
}


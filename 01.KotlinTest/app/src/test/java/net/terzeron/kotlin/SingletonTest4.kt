package net.terzeron.kotlin

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by terzeron on 2018. 1. 29..
 */
class SingletonTest4 {
    // 싱글턴은 object임
    object Singleton {
        val name: String = "single"
        fun printName() = print(name)
    }

    @Test
    fun testObject() {
        assertEquals("single", Singleton.name)
    }
}

package net.terzeron.kotlin

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by terzeron on 2018. 1. 29..
 */
class CompanionTest10 {
    class CompanionObj {
        // 동반 객체
        // static member들을 모아둔 객체
        companion object {
            var name: String = "shared"
        }

        fun changeName(newName: String) {
            name = newName
        }

        fun myName() = name
    }

    @Test
    fun testCompanionObj() {
        val obj1 = CompanionObj()
        val obj2 = CompanionObj()

        assertEquals("shared", obj1.myName())
        assertEquals("shared", obj2.myName())

        obj1.changeName("changed")
        assertEquals("changed", obj1.myName())
        assertEquals("changed", obj2.myName())
    }
}

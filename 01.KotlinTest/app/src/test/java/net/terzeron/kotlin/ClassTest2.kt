package net.terzeron.kotlin

import org.junit.Assert
import org.junit.Test

/**
 * Created by terzeron on 2018. 1. 29..
 */

class ClassTest2 {
    // 인터페이스
    interface Named {
        val name: String
        fun uppercase(): String
    }

    // 추상클래스
    open class Person(val name: String, var age: Int) {
        fun increaseAge(x: Int) {
            age += x
        }
    }

    class Student(name: String, age: Int, var grade: Int) : Person(name, age), Named {
        override fun uppercase(): String {
            return name.toUpperCase()
        }
    }

    @Test
    fun testClassAndInterface() {
        val person: Named = Student("kwseo", 28, 6)
        Assert.assertEquals("KWSEO", person.uppercase())
    }
}
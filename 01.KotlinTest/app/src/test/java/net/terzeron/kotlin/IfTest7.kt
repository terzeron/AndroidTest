package net.terzeron.kotlin

import org.junit.Test

/**
 * Created by terzeron on 2018. 1. 29..
 */
class IfTest7 {
    fun checkIfElse1(value: Int): String {
        if (value > 10) {
            return "greater than 10"
        } else if (value == 10) {
            return "equal"
        } else {
            return "less than 10"
        }
    }

    fun checkIfElse2(value: Int): String {
        return if (value > 10) {
            "greater than 10"
        } else if (value == 10) {
            "equal"
        } else {
            "less than 10"
        }
    }

    @Test
    fun testIfElse() {
        println(checkIfElse1(10))
        println(checkIfElse2(20))
    }
}
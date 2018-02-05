package net.terzeron.kotlin

import android.util.Log
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * Created by terzeron on 2018. 1. 29..
 */
class DataClassTest5 {
    // data class는 getter, setter를 정의하면 필드를 함수처럼 사용할 수 있음
    data class Log(var date: LocalDateTime, val level: String, val msg: String) {
        val epochSeconds: Long
            get() = date.toEpochSecond(ZoneOffset.UTC)
    }

    @Test
    fun testDataClass() {
        val now = LocalDateTime.now()
        val log = Log(now, "INFO", "SUCCESS")
        val seconds = log.epochSeconds
        assertEquals(now.toEpochSecond(ZoneOffset.UTC), seconds)

        val str = log.toString()
        val hash = log.hashCode()
        val isEqual = log == Log(LocalDateTime.now(), "ERROR", "FAILED")
        assertFalse(isEqual)

        val clone = log.copy(date = LocalDateTime.now())
        assertEquals(log.level, clone.level)
        assertEquals(log.msg, clone.msg)
        assertTrue(log.date != clone.date)

        val (date, level, msg) = log
        assertEquals(log.date, date)
        assertEquals(log.level, level)
        assertEquals(log.msg, msg)
    }
}
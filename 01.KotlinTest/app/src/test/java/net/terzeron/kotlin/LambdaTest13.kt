package net.terzeron.kotlin

import org.junit.Test
import java.io.File
import java.io.FileInputStream
import java.nio.charset.Charset
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by terzeron on 2018. 1. 30..
 */
class LambdaTest13 {
    fun threadSafeFunction() = synchronized(ReentrantLock()) {
        println("thread-safe function")
    }

    // forEachLine을 사용해서 행 단위로 처리
    @Test
    fun testForEachLine() {
        println("testForEachLine()")
        File("./sample.log").forEachLine {
            if (it.startsWith("INFO")) {
                println("INFO log = $it")
            } else {
                println(it)
            }
        }
    }

    @Test
    fun testUse() {
        println("testUse()")
        FileInputStream(File("./sample.log")).use {
            var content = it.readBytes().toString(Charset.defaultCharset())
            println(content)
        }

        // use를 사용하면 리소스를 해제할 필요없음
    }
}
package dev.oblac.voidkt.monad

import java.util.concurrent.atomic.AtomicInteger

// Effect interface (algebra)
interface Counter {
    fun increment()
    fun get(): Int
}

interface Logger {
    fun log(message: String)
}

// Business logic using context parameters
context(counter: Counter, logger: Logger)
fun greet(name: String): String {
    counter.increment()
    val count = counter.get()
    val msg = "Hello, $name!"
    logger.log("$msg (operations: $count)")
    return msg
}

// Concrete interpreter using AtomicInteger
class AtomicCounter(private val ref: AtomicInteger = AtomicInteger(0)) : Counter {
    override fun increment() {
        ref.incrementAndGet()
    }
    override fun get(): Int = ref.get()
}
class ConsoleLogger : Logger {
    override fun log(message: String) {
        println("[LOG] $message")
    }
}

// Run the program
fun main() {
    val counter = AtomicCounter()
    val logger = ConsoleLogger()
    with(counter) {
        with(logger) {
            val message = greet("World")
            println("Result: $message")
        }
    }
    context(counter, logger) {
        val message = greet("World")
        println("Result: $message")
    }
}
package cn.example.ddd.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

@ExperimentalCoroutinesApi
interface CoroutineTestSupport {
    val testDispatcher: TestDispatcher

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    companion object Delegator : CoroutineTestSupport {
        override val testDispatcher = StandardTestDispatcher()
    }
}

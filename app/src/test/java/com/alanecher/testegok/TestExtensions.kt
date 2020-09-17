package com.alanecher.testegok

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

inline fun <reified T> mock(): T =
    Mockito.mock(T::class.java)

// To avoid having to use backticks for "when"
fun <T> whenever(methodCall: T): OngoingStubbing<T> =
    Mockito.`when`(methodCall)
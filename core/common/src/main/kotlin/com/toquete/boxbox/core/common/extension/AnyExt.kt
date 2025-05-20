package com.toquete.boxbox.core.common.extension

import java.io.FileNotFoundException

fun Any.readPath(path: String): String {
    return javaClass.classLoader?.getResource(path)
        ?.readText()
        ?: throw FileNotFoundException("File was not found in $this")
}

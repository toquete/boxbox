package com.toquete.boxbox.core.common.extension

import java.io.FileNotFoundException

fun String.readPath(): String {
    return ClassLoader.getSystemResource(this)
        ?.readText()
        ?: throw FileNotFoundException("File was not found in $this")
}

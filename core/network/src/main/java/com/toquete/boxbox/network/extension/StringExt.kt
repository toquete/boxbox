package com.toquete.boxbox.network.extension

import java.io.FileNotFoundException

internal fun String.readPath(): String {
    return ClassLoader.getSystemResource(this)
        ?.readText()
        ?: throw FileNotFoundException("File was not found in $this")
}
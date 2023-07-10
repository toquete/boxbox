package com.toquete.boxbox.plugins

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ManagedVirtualDevice
import org.gradle.kotlin.dsl.invoke

internal fun configureGradleManagedDevices(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.testOptions {
        managedDevices {
            devices {
                maybeCreate("pixel2api30", ManagedVirtualDevice::class.java).apply {
                    device = "Pixel 2"
                    apiLevel = 30
                    systemImageSource = "aosp-atd"
                }
            }
        }
    }
}

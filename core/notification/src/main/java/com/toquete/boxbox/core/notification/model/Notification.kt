package com.toquete.boxbox.core.notification.model

import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.toquete.boxbox.core.ui.R as uiR

data class Notification private constructor(
    val id: Int,
    val channel: Channel,
    val title: String?,
    val text: String?,
    val priority: Int,
    val category: String?,
    val style: NotificationCompat.Style?,
    @DrawableRes val smallIcon: Int = uiR.drawable.ic_app_icon
) {

    class Builder {
        private var title: String? = null
        private var text: String? = null
        private var priority: Int = NotificationCompat.PRIORITY_DEFAULT
        private var category: String? = null
        private var style: NotificationCompat.Style? = null

        fun setTitle(title: String) = apply { this.title = title }
        fun setText(text: String) = apply { this.text = text }
        fun setPriority(priority: Int) = apply { this.priority = priority }
        fun setCategory(category: String) = apply { this.category = category }
        fun setStyle(style: NotificationCompat.Style) = apply { this.style = style }

        fun build(id: Int, channel: Channel): Notification {
            return Notification(
                id = id,
                channel = channel,
                title = title,
                text = text,
                priority = priority,
                category = category,
                style = style
            )
        }
    }
}

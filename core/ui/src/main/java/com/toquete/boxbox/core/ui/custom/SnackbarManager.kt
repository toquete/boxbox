package com.toquete.boxbox.core.ui.custom

import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class Message(
    val id: Long,
    @StringRes val messageId: Int,
    val duration: SnackbarDuration,
    val withDismissAction: Boolean
)

/**
 * Class responsible for managing Snackbar messages to show on the screen
 */
object SnackbarManager {

    private val _messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val messages: StateFlow<List<Message>> get() = _messages.asStateFlow()

    fun showMessage(
        @StringRes messageTextId: Int,
        duration: SnackbarDuration = SnackbarDuration.Short,
        withDismissAction: Boolean = false
    ) {
        _messages.update { currentMessages ->
            currentMessages + Message(
                id = UUID.randomUUID().mostSignificantBits,
                messageId = messageTextId,
                duration = duration,
                withDismissAction = withDismissAction
            )
        }
    }

    fun setMessageShown(messageId: Long) {
        _messages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }
}

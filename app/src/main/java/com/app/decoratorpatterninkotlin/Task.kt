package com.app.decoratorpatterninkotlin

import android.util.Log
import com.app.decoratorpatterninkotlin.enums.MessengerApp
import com.app.decoratorpatterninkotlin.enums.MessengerApp.FACEBOOK
import com.app.decoratorpatterninkotlin.enums.MessengerApp.INSTAGRAM

class Task {
  
  private val notifier: Notifier by lazy { Notifier()}

  fun send(messageText: String, messengerList: List<MessengerApp>): String {
    return sendingProcess(messageText, messengerList)
  }

  fun sendingProcess(messageText: String, messengerList: List<MessengerApp>): String {
    var messengerKit: Messenger? = null
    messengerList.mapIndexed { index, messengerApp ->
      messengerKit = if (index == 0) selectFirstMessenger(messengerApp)
      else {
        messengerKit?.let {
          selectOtherMessengers(messengerApp, it)
        }
      }
    }

    messengerKit?.let {
      val develop: Messenger = it
      return develop.sendMessage(messageText)
    }
    return ""
  }

  private fun selectFirstMessenger(messengerApp: MessengerApp): Messenger {
    return when (messengerApp) {
      FACEBOOK -> Facebook(notifier)
      INSTAGRAM -> Instagram (notifier)
      else -> Telegram(notifier)
    }
  }

  private fun selectOtherMessengers(messengerApp: MessengerApp, messengerKit: Messenger): Messenger {
    return when (messengerApp) {
      FACEBOOK -> Facebook(messengerKit)
      INSTAGRAM -> Instagram (messengerKit)
      else -> Telegram(messengerKit)
    }
  }
}
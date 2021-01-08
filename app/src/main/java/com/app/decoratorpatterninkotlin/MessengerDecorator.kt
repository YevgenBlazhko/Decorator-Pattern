package com.app.decoratorpatterninkotlin

open class MessengerDecorator(private val messenger: Messenger) : Messenger {

  override fun sendMessage(message: String): String {
    return messenger.sendMessage(message)
  }
}
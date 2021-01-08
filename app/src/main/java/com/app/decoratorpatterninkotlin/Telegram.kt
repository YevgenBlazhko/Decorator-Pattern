package com.app.decoratorpatterninkotlin

class Telegram(messenger: Messenger) : MessengerDecorator(messenger) {

  private fun sendViaTelegram(): String {
    return "Telegram\n"
  }

  override fun sendMessage(message: String): String {
    return super.sendMessage(message) + sendViaTelegram()
  }
}
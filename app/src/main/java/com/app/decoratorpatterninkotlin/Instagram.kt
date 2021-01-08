package com.app.decoratorpatterninkotlin

class Instagram(messenger: Messenger) : MessengerDecorator(messenger) {

  private fun sendViaInstagram(): String {
    return "Instagram\n"
  }

  override fun sendMessage(message: String): String {
    return super.sendMessage(message) + sendViaInstagram()
  }
}
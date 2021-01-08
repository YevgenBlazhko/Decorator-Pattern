package com.app.decoratorpatterninkotlin

class Facebook(messenger: Messenger) : MessengerDecorator(messenger) {

  private fun sendViaFacebook(): String {
    return "Facebook\n"
  }

  override fun sendMessage(message: String): String {
    return super.sendMessage(message) + sendViaFacebook()
  }
}
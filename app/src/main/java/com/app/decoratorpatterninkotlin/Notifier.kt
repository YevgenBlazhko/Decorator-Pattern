package com.app.decoratorpatterninkotlin

class Notifier: Messenger {
  override fun sendMessage(message: String): String {
    return "Send message\n\"$message\"\nvia:\n"
  }
}
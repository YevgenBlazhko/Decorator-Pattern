package com.app.decoratorpatterninkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.addTextChangedListener
import com.app.decoratorpatterninkotlin.enums.MessengerApp
import com.app.decoratorpatterninkotlin.enums.MessengerApp.TELEGRAM
import com.app.decoratorpatterninkotlin.enums.MessengerApp.FACEBOOK
import com.app.decoratorpatterninkotlin.enums.MessengerApp.INSTAGRAM

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

  private val task: Task by lazy { Task() }
  private val facebookSw: SwitchCompat by lazy { findViewById(R.id.facebook_sw) }
  private val instagramSw: SwitchCompat by lazy { findViewById(R.id.instagram_sw) }
  private val telegramSw: SwitchCompat by lazy { findViewById(R.id.telegram_sw) }
  private val sendBtn: Button by lazy { findViewById(R.id.send_btn) }
  private val summaryTv: TextView by lazy { findViewById(R.id.summary_tv) }
  private val messageEt: TextView by lazy { findViewById(R.id.message_et)}

  private val messengerList: MutableList<MessengerApp> = mutableListOf()
  private var textForSending = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    facebookSw.setOnCheckedChangeListener(this)
    telegramSw.setOnCheckedChangeListener(this)
    instagramSw.setOnCheckedChangeListener(this)

    sendBtn.setOnClickListener {
      val response = task.send(textForSending, messengerList)
      summaryTv.text = response
    }

    messageEt.addTextChangedListener {
      textForSending = it.toString().trim()
      sendBtnStateHandler()
    }

  }

  override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
    when (buttonView?.id) {
      R.id.facebook_sw -> FACEBOOK.handleMessengerList(isChecked)
      R.id.instagram_sw -> INSTAGRAM.handleMessengerList(isChecked)
      else -> TELEGRAM.handleMessengerList(isChecked)
    }
    sendBtnStateHandler()
  }

  private fun MessengerApp.handleMessengerList(isChecked: Boolean) {
    if (isChecked) messengerList.add(this) else messengerList.remove(this)
  }

  private fun sendBtnStateHandler() {
    sendBtn.isEnabled = isSendingAvailable()
  }

  private fun isSendingAvailable(): Boolean {
    return (messengerList.isNotEmpty() && textForSending.isNotEmpty())
  }
}
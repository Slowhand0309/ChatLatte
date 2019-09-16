package com.github.slowhand.chatlatte.config

import android.graphics.Bitmap
import android.graphics.Color

class ChatLatteConfig {
    // placeholder
    var placeholder: String = ""
    var placeholderColor: Int = Color.parseColor("#DDDDDD")

    // scroll to end position via send text
    var autoScrollEnabled = true

    // clear text field via send text
    var autoClearTextEnabled = true

    // set chat message view color
    var chatBackgroundColor: Int = Color.TRANSPARENT

    // set input text area color
    var inputTextContainerColor: Int = Color.TRANSPARENT

    // send button image
    var sendButtonImage: Bitmap? = null

    // gallery button image
    var galleryButtonImage: Bitmap? = null

}
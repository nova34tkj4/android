package com.example.bythen.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.example.bythen.ui.theme.ByteBlack1000
import com.example.bythen.ui.theme.ByteBlack400
import com.example.bythen.ui.theme.ByteWhite1000
import com.example.bythen.ui.theme.TotoroGray100

@Composable
fun ByteButton(
    modifier: Modifier = Modifier,
    text: String = "",
    buttonState: ByteButtonState,
    onClick: () -> Unit
) {
    fun getButtonBackground(): Color {
        return when (buttonState) {
            ByteButtonState.FILL -> ByteBlack1000
            ByteButtonState.UNDERLINE -> Color.Transparent
            ByteButtonState.BORDER -> Color.Transparent
            ByteButtonState.DISABLED -> TotoroGray100
        }
    }

    fun getTextColor(): Color {
        return when (buttonState) {
            ByteButtonState.FILL -> ByteWhite1000
            ByteButtonState.UNDERLINE -> ByteBlack1000
            ByteButtonState.BORDER -> ByteBlack1000
            ByteButtonState.DISABLED -> ByteBlack400
        }
    }

    fun getTextDecoration(): TextDecoration {
        return when (buttonState) {
            ByteButtonState.FILL -> TextDecoration.None
            ByteButtonState.UNDERLINE -> TextDecoration.Underline
            ByteButtonState.BORDER -> TextDecoration.None
            ByteButtonState.DISABLED -> TextDecoration.None
        }
    }

   Button(
       modifier = modifier.background(getButtonBackground()),
       colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
       onClick = onClick
   ){
       Row(
           verticalAlignment = Alignment.CenterVertically
       ) {
           if (text.isNotEmpty()) {
               Text(
                   text = text,
                   color = getTextColor(),
                   textDecoration = getTextDecoration()
               )
           }
       }
   }
}

enum class ByteButtonState {
    FILL,
    UNDERLINE,
    BORDER,
    DISABLED
}
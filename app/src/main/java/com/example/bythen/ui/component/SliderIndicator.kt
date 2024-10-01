package com.example.bythen.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bythen.ui.theme.ByteBlack1000
import com.example.bythen.ui.theme.ByteBlack200

@Composable
fun SliderIndicator(
    modifier: Modifier,
    sliderCount: Int,
    activeSlider: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        (0..< sliderCount).forEach { i ->
            Box(
                modifier = Modifier.height(3.dp).weight(1f)
                    .background(
                        if (i == activeSlider) ByteBlack1000 else ByteBlack200
                    )
            )
        }
    }
}
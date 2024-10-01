package com.example.bythen.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bythen.ui.component.ByteButton
import com.example.bythen.ui.component.ByteButtonState
import com.example.bythen.ui.component.SliderIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier,
    assets: List<Int>,
    backgrounds: List<Color>,
    innerPadding: PaddingValues,
    onConnectWalletClick: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        assets.size
    })
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        HorizontalPager(
            modifier = modifier.fillMaxSize(),
            state = pagerState
        ) { page ->
            // Our page content
            Box(
                Modifier.fillMaxSize().background(backgrounds[page]).padding(0.dp, 52.dp)
            ) {
                Image(
                    painter = painterResource(assets[page]),
                    contentDescription = "Illustration $page",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
        }

        SliderIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, innerPadding.calculateTopPadding() + 24.dp, 16.dp, 0.dp),
            sliderCount = assets.size,
            activeSlider = pagerState.currentPage,
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp, 24.dp).align(Alignment.BottomCenter)
        ) {
            ByteButton(
                modifier = Modifier.fillMaxWidth().padding(0.dp, 12.dp),
                text = "CONNECT WALLET",
                buttonState = ByteButtonState.FILL
            ) {
                onConnectWalletClick.invoke()
            }

            ByteButton(
                modifier = Modifier.fillMaxWidth(),
                text = "OR CONTINUE WITH EMAIL",
                buttonState = ByteButtonState.UNDERLINE
            ){

            }
        }

    }
}
package com.example.campusconnect.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.campusconnect.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    var startZoomOutAnimation by remember { mutableStateOf(false) }

    // 1. Bounce/Y-offset Animation
    val infiniteTransition = rememberInfiniteTransition(label = "bounce")
    val bounceOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -30f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bounceOffset"
    )

    // 2. Scale up/Zoom in transition when entering login page
    val scale by animateFloatAsState(
        targetValue = if (startZoomOutAnimation) 8f else 1f,
        animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing),
        label = "zoomInScale"
    )

    LaunchedEffect(Unit) {
        delay(2500) // Keep bounce animation for 2.5 seconds
        startZoomOutAnimation = true
        delay(4500) // Wait for zoom in animation to finish completely
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Matches your provided logo's background perfectly
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "CampusConnect Logo",
            modifier = Modifier
                .size(240.dp)
                .offset(y = if (startZoomOutAnimation) 0.dp else bounceOffset.dp)
                .scale(scale)
        )
    }
}

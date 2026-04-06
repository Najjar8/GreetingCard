package com.example.greetingcard.activities



import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.greetingcard.ui.theme.OnPrimary
import com.example.greetingcard.ui.theme.Primary
import com.example.greetingcard.ui.theme.PrimaryContainer
import com.example.greetingcard.ui.theme.SecondaryContainer
import com.example.greetingcard.ui.theme.TertiaryFixedDim
import kotlinx.coroutines.delay


// ─────────────────────────────────────────────
//  Splash Screen Composable
// ─────────────────────────────────────────────
@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit = {}
) {
    // ── Animation states ──────────────────────
    var animationStarted by remember { mutableStateOf(false) }

    // Logo scale: pops in from 0.6 → 1.0
    val logoScale by animateFloatAsState(
        targetValue  = if (animationStarted) 1f else 0.6f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness    = Spring.StiffnessMedium
        ),
        label = "logoScale"
    )

    // Title alpha: fades in
    val titleAlpha by animateFloatAsState(
        targetValue   = if (animationStarted) 1f else 0f,
        animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing),
        label = "titleAlpha"
    )

    // Tagline offset slides up
    val taglineOffsetY by animateFloatAsState(
        targetValue   = if (animationStarted) 0f else 24f,
        animationSpec = tween(durationMillis = 800, delayMillis = 300, easing = FastOutSlowInEasing),
        label = "taglineOffset"
    )

    // Accent line width expands
    val lineWidth by animateFloatAsState(
        targetValue   = if (animationStarted) 1f else 0f,
        animationSpec = tween(durationMillis = 600, delayMillis = 400, easing = FastOutSlowInEasing),
        label = "lineWidth"
    )

    // Dot pulse infinitely
    val dotTransition = rememberInfiniteTransition(label = "dotPulse")
    val dot1Alpha by dotTransition.animateFloat(
        initialValue  = 0.4f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation  = tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dot1"
    )
    val dot2Alpha by dotTransition.animateFloat(
        initialValue  = 0.4f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation  = tween(600, delayMillis = 200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dot2"
    )
    val dot3Alpha by dotTransition.animateFloat(
        initialValue  = 0.4f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation  = tween(600, delayMillis = 400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dot3"
    )

    // ── Side effects ──────────────────────────
    LaunchedEffect(Unit) {
        animationStarted = true
        delay(2800L)     // total splash display time
        onSplashFinished()
    }

    // ── UI ───────────────────────────────────
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Primary, PrimaryContainer),
                    start  = Offset(0f, 0f),
                    end    = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        // ── Decorative blobs ──────────────────
        Box(
            modifier = Modifier
                .size(320.dp)
                .offset(x = (-80).dp, y = (-80).dp)
                .clip(RoundedCornerShape(50))
                .background(PrimaryContainer.copy(alpha = 0.35f))
                .blur(80.dp)
        )
        Box(
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 60.dp, y = 60.dp)
                .clip(RoundedCornerShape(50))
                .background(SecondaryContainer.copy(alpha = 0.15f))
                .blur(80.dp)
        )

        // ── Main content ──────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo card
            Box(
                modifier = Modifier
                    .scale(logoScale)
                    .size(96.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(OnPrimary.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Hub,
                    contentDescription = "App Logo",
                    tint = OnPrimary,
                    modifier = Modifier.size(52.dp)
                )
            }

            Spacer(Modifier.height(32.dp))

            // App name
            Text(
                text  = "The Greeting Card",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight    = FontWeight.ExtraBold,
                    letterSpacing = (-0.5).sp,
                    fontSize      = 34.sp
                ),
                color     = OnPrimary.copy(alpha = titleAlpha),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))

            // Accent line — expands outward from center
            Box(
                modifier = Modifier
                    .width(48.dp * lineWidth)
                    .height(4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(TertiaryFixedDim)
            )
        }

        // ── Bottom section ────────────────────
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Tagline
            Text(
                text  = "Empower your mind",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight    = FontWeight.SemiBold,
                    letterSpacing = 3.sp,
                    fontSize      = 11.sp
                ),
                color = OnPrimary.copy(alpha = 0.7f * titleAlpha),
                modifier = Modifier.offset(y = taglineOffsetY.dp)
            )

            // Loading dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment     = Alignment.CenterVertically
            ) {
                LoadingDot(alpha = dot1Alpha)
                LoadingDot(alpha = dot2Alpha)
                LoadingDot(alpha = dot3Alpha)
            }
        }

        // ── Bottom shimmer edge ───────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            OnPrimary.copy(alpha = 0.25f),
                            Color.Transparent
                        )
                    )
                )
        )
    }
}

@Composable
private fun LoadingDot(alpha: Float) {
    Box(
        modifier = Modifier
            .size(8.dp)
            .clip(RoundedCornerShape(50))
            .background(OnPrimary.copy(alpha = alpha * 0.8f))
    )
}

// ─────────────────────────────────────────────
//  Preview
// ─────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    MaterialTheme {
        SplashScreen()
    }
}
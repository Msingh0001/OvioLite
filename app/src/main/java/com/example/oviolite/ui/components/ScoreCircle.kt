package com.example.oviolite.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oviolite.ui.theme.OvioColors

/**
 * The calm-score circle, ported from ModernScoreCircle.swift.
 * When [breathing] is true it slowly grows and shrinks, like the iOS version.
 */
@Composable
fun ScoreCircle(
    score: Int?,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    breathing: Boolean = false,
) {
    // TODO 4 (the important one): Honest scores.
    //  When we have NO data, this shows "0". That's a lie: it tells the user
    //  the place is packed, when really we just don't know.
    //  Change it so a null score shows "?" in a neutral colour instead.
    //  OVIO's rule: showing "?" is better than a made-up number.
    val shownScore = score ?: 0
    val text = shownScore.toString()
    val tint = OvioColors.scoreColor(shownScore)

    // An infinite animation: 1.0 → 1.04 → 1.0, forever. ~3.2 s per breath.
    val pulse by rememberInfiniteTransition(label = "breath").animateFloat(
        initialValue = 1f,
        targetValue = if (breathing) 1.04f else 1f,
        animationSpec = infiniteRepeatable(tween(1600), RepeatMode.Reverse),
        label = "pulse",
    )

    Box(
        modifier = modifier
            .size(size)
            .scale(pulse)
            .background(tint.copy(alpha = 0.14f), CircleShape)
            .border(1.5.dp, tint.copy(alpha = 0.60f), CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = (size.value * 0.36f).sp,
                color = tint,
            ),
        )
    }
}

package com.example.oviolite.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oviolite.ui.theme.OvioColors

/** The five tabs, in the same order as the iOS app. */
enum class OvioTab(val label: String, val icon: ImageVector?) {
    Home("Home", Icons.Outlined.Home),
    Discover("Discover", Icons.Outlined.Search),
    Concierge("OVIO", null),               // drawn by hand below: the concierge bell
    Sanctuaries("Sanctuaries", Icons.Outlined.FavoriteBorder),
    Profile("Profile", Icons.Outlined.Person),
}

/** The floating capsule tab bar from LovaTabBar.swift. */
@Composable
fun OvioTabBar(
    selected: OvioTab,
    onSelect: (OvioTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(30.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp)
            .clip(shape)
            .background(OvioColors.TabBar, shape)
            .border(0.7.dp, Color.White.copy(alpha = 0.075f), shape),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        OvioTab.entries.forEach { tab ->
            val active = tab == selected
            val color = if (active) OvioColors.Champagne else Color(0xFFB8B0A3).copy(alpha = 0.62f)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null, // no ripple: matches the calm iOS feel
                    ) { onSelect(tab) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                if (tab.icon != null) {
                    Icon(tab.icon, contentDescription = null, tint = color, modifier = Modifier.size(19.dp))
                } else {
                    ConciergeBell(color, Modifier.size(19.dp))
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    tab.label,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 9.5.sp,
                        color = if (active) OvioColors.Champagne else Color.White.copy(alpha = 0.46f),
                    ),
                    maxLines = 1,
                )
            }
        }
    }
}

/**
 * The concierge bell icon, drawn with lines and curves.
 * A direct translation of ConciergeBellShape in LovaTabBar.swift. Compare them side by side!
 */
@Composable
private fun ConciergeBell(color: Color, modifier: Modifier = Modifier) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height
        val stroke = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round)

        // the little knob on top
        drawCircle(color, radius = w * 0.07f, center = Offset(w * 0.5f, h * 0.15f), style = stroke)

        val path = Path().apply {
            // the dome
            moveTo(w * 0.17f, h * 0.68f)
            quadraticBezierTo(w * 0.50f, h * 0.22f, w * 0.83f, h * 0.68f)
            // the rim
            moveTo(w * 0.12f, h * 0.70f)
            lineTo(w * 0.88f, h * 0.70f)
            // the base
            moveTo(w * 0.05f, h * 0.87f)
            lineTo(w * 0.95f, h * 0.87f)
        }
        drawPath(path, color, style = stroke)
    }
}

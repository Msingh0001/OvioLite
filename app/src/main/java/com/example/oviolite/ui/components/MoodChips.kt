package com.example.oviolite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.oviolite.data.Mood
import com.example.oviolite.ui.theme.OvioColors

/**
 * A row of glass "pills", one per Mood.
 * Notice that it loops over Mood.entries, so it never needs editing when a mood is added.
 */
@Composable
fun MoodChips(
    selected: Mood?,
    onSelect: (Mood) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Mood.entries.forEach { mood ->
            val isOn = mood == selected
            val shape = RoundedCornerShape(50)
            Text(
                text = mood.label,
                style = MaterialTheme.typography.labelLarge,
                color = if (isOn) OvioColors.WarmIvory else OvioColors.TextSecondary,
                modifier = Modifier
                    .clip(shape)
                    .background(if (isOn) OvioColors.Accent.copy(alpha = 0.18f) else Color.Black.copy(alpha = 0.35f), shape)
                    .border(
                        width = if (isOn) 1.dp else 0.5.dp,
                        color = if (isOn) OvioColors.Champagne.copy(alpha = 0.70f) else Color.White.copy(alpha = 0.14f),
                        shape = shape,
                    )
                    .clickable { onSelect(mood) }
                    .padding(horizontal = 16.dp, vertical = 9.dp),
            )
        }
    }
}

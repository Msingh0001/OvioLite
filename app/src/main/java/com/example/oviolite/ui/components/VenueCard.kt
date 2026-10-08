package com.example.oviolite.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.oviolite.data.Venue
import com.example.oviolite.ui.theme.Elevation
import com.example.oviolite.ui.theme.OvioColors
import com.example.oviolite.ui.theme.ovioElevated
import com.example.oviolite.ui.theme.ovioSurface

/**
 * The big "Tonight's pick" card on Home. This is the screen's ONE primary element,
 * so it's the only thing that gets the gold rim and glow.
 */
@Composable
fun HeroVenueCard(
    venue: Venue,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .ovioElevated(Elevation.Primary, corner = 20.dp)
            .clickable(onClick = onOpen)
            .padding(18.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = venue.category.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = OvioColors.Champagne.copy(alpha = 0.85f),
                )
                Spacer(Modifier.height(6.dp))
                Text(venue.name, style = MaterialTheme.typography.titleLarge, color = OvioColors.TextPrimary)
                Spacer(Modifier.height(4.dp))
                Text(
                    "${venue.neighborhood} · ${venue.distance}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OvioColors.TextMuted,
                )
            }
            ScoreCircle(score = venue.calmScore, size = 64.dp, breathing = true)
        }

        Spacer(Modifier.height(14.dp))
        Text(venue.whyItFits, style = MaterialTheme.typography.bodyLarge, color = OvioColors.TextBody)
        Spacer(Modifier.height(6.dp))
        Text(venue.quietWindow, style = MaterialTheme.typography.bodyMedium, color = OvioColors.Champagne)

        Spacer(Modifier.height(10.dp))
        HorizontalDivider(thickness = 0.5.dp, color = Color.White.copy(alpha = 0.08f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Why it fits",
                style = MaterialTheme.typography.bodyMedium,
                color = OvioColors.WarmIvory.copy(alpha = 0.86f),
                modifier = Modifier.weight(1f),
            )
            SaveButton(isSaved, onToggleSave)
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = OvioColors.Champagne.copy(alpha = 0.62f),
            )
        }
    }
}

/** A quieter row for the rest of the list. */
@Composable
fun VenueRow(
    venue: Venue,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .ovioSurface(corner = 16.dp)
            .clickable(onClick = onOpen)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        ScoreCircle(score = venue.calmScore, size = 46.dp)
        Column(Modifier.weight(1f)) {
            Text(
                venue.name,
                style = MaterialTheme.typography.titleMedium,
                color = OvioColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                "${venue.category} · ${venue.distance}",
                style = MaterialTheme.typography.bodyMedium,
                color = OvioColors.TextMuted,
            )
        }
        SaveButton(isSaved, onToggleSave)
    }
}

@Composable
fun SaveButton(isSaved: Boolean, onToggle: () -> Unit) {
    IconButton(onClick = onToggle) {
        Icon(
            imageVector = if (isSaved) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = if (isSaved) "Remove from sanctuaries" else "Save to sanctuaries",
            tint = if (isSaved) OvioColors.Champagne else OvioColors.TextFaint,
            modifier = Modifier.size(20.dp),
        )
    }
}

/** Small uppercase section label, e.g. "TONIGHT'S PICK". */
@Composable
fun SectionLabel(text: String, modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text.uppercase(), style = MaterialTheme.typography.labelSmall, color = OvioColors.Champagne.copy(alpha = 0.8f))
        Spacer(Modifier.width(10.dp))
        HorizontalDivider(Modifier.weight(1f), thickness = 0.5.dp, color = OvioColors.Bronze.copy(alpha = 0.35f))
    }
}

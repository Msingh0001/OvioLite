package com.example.oviolite.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.oviolite.data.Venue
import com.example.oviolite.ui.components.SaveButton
import com.example.oviolite.ui.components.ScoreCircle
import com.example.oviolite.ui.components.SectionLabel
import com.example.oviolite.ui.theme.OvioColors
import com.example.oviolite.ui.theme.ovioPrimaryAction
import com.example.oviolite.ui.theme.ovioSurface

@Composable
fun DetailScreen(
    venue: Venue,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(OvioColors.Background, OvioColors.Ground)))
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(bottom = 120.dp),
    ) {
        // Top bar: back + save
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = OvioColors.Champagne)
            }
            Spacer(Modifier.weight(1f))
            SaveButton(isSaved, onToggleSave)
        }

        Spacer(Modifier.height(12.dp))
        Text(
            venue.category.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = OvioColors.Champagne,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))
        Text(
            venue.name,
            style = MaterialTheme.typography.headlineMedium,
            color = OvioColors.TextPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            "${venue.neighborhood} · ${venue.distance}",
            style = MaterialTheme.typography.bodyMedium,
            color = OvioColors.TextMuted,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(28.dp))
        ScoreCircle(
            score = venue.calmScore,
            size = 120.dp,
            breathing = true,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Spacer(Modifier.height(10.dp))
        Text(
            "CALM SCORE",
            style = MaterialTheme.typography.labelSmall,
            color = OvioColors.TextFaint,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(30.dp))
        InfoCard(title = "Why it fits", body = venue.whyItFits)
        Spacer(Modifier.height(12.dp))
        InfoCard(title = "Calmest time", body = venue.quietWindow)
        Spacer(Modifier.height(12.dp))
        InfoCard(title = "Good for", body = venue.moods.joinToString(" · ") { it.label })

        Spacer(Modifier.height(28.dp))
        // The ONE primary action on this screen.
        Text(
            "Take me there",
            style = MaterialTheme.typography.titleMedium,
            color = OvioColors.WarmIvory,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .ovioPrimaryAction()
                .clickable {
                    // TODO 5 (stretch): Open Google Maps with directions to this place.
                    //  Look up "Android Intent ACTION_VIEW geo URI".
                    //  Hint: Uri.parse("geo:0,0?q=" + Uri.encode(venue.name))
                    Toast.makeText(context, "Directions are your job: see TODO 5", Toast.LENGTH_SHORT).show()
                }
                .padding(vertical = 16.dp),
        )
    }
}

@Composable
private fun InfoCard(title: String, body: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .ovioSurface()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        SectionLabel(title)
        Text(body, style = MaterialTheme.typography.bodyLarge, color = OvioColors.TextBody)
    }
}

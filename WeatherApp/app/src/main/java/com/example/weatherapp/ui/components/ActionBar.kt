package com.example.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.ColorGradient1
import com.example.weatherapp.ui.theme.ColorGradient2
import com.example.weatherapp.ui.theme.ColorGradient3
import com.example.weatherapp.ui.theme.ColorSurface
import com.example.weatherapp.ui.theme.ColorTextPrimary
import com.example.weatherapp.ui.theme.ColorTextSecondary

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    location: String,
    onFavoritesClick: () -> Unit,
    isFavorited: Boolean,
    onToggleUnit: () -> Unit,
    currentUnit: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 96.dp)
            .padding(horizontal = 30.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ControlButton(onClick = onFavoritesClick, isFavorited = isFavorited)
            LocationInfo(
                modifier = Modifier.padding(top = 10.dp),
                location = location
            )
            IconButton(onClick = onToggleUnit, modifier = modifier.size(48.dp)) {
                Text(text = if (currentUnit == "metric") "°C" else "°F",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun ControlButton(onClick: () -> Unit, modifier: Modifier = Modifier, isFavorited: Boolean) {
    Surface(
        onClick = onClick,
        color = ColorSurface,
        shape = CircleShape,
        modifier = modifier
            .size(48.dp)
            .customShadow(
                color = Color.Black,
                alpha = 0.15f,
                shadowRadius = 16.dp,
                borderRadius = 48.dp,
                offsetY = 4.dp
            ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isFavorited) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                contentDescription = "Favorites",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Composable
private fun LocationInfo(
    modifier: Modifier = Modifier,
    location: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_location_pin),
                contentDescription = null,
                modifier = Modifier.height(18.dp),
                contentScale = ContentScale.FillHeight
            )
            Text(
                text = location,
                style = MaterialTheme.typography.titleLarge,
                color = ColorTextPrimary,
                fontWeight = FontWeight.Bold
            )
        }
        ProgressBar()
    }
}

@Composable
private fun ProgressBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    0f to ColorGradient1,
                    0.25f to ColorGradient2,
                    1f to ColorGradient3
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                vertical = 2.dp,
                horizontal = 10.dp
            )
    ) {
        Text(
            text = "Updating •",
            style = MaterialTheme.typography.labelSmall,
            color = ColorTextSecondary.copy(alpha = 0.7f)
        )
    }
}

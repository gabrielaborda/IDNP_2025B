package com.example.lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab06.ui.theme.Lab06Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab06Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CircleAnimationScreen()
                }
            }
        }
    }
}

@Composable
fun CircleAnimationScreen() {
    // Estado que controla si el círculo está expandido o no
    var expanded by remember { mutableStateOf(false) }

    // Animación del radio del círculo (usamos animateDpAsState)
    val circleSize by animateDpAsState(
        targetValue = if (expanded) 200.dp else 80.dp,
        animationSpec = tween(durationMillis = 800),
        label = "circleAnimation"
    )

    // Diseño de la pantalla
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier.size(300.dp)) {
            drawCircle(
                color = Color(0xFF4CAF50),
                radius = circleSize.toPx() / 2
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { expanded = !expanded }) {
            Text(if (expanded) "Reducir" else "Expandir")
        }
    }
}

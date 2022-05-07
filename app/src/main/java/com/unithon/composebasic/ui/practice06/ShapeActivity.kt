package com.unithon.composebasic.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.unithon.composebasic.ui.ui.theme.ComposeBasicTheme
import java.lang.Math.cos
import java.lang.Math.sin
import kotlin.random.Random

class ShapeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ShapeContainer()
                }
            }
        }
    }
}

@Composable
fun ShapeContainer() {

    var polySides by remember {
        mutableStateOf(3)
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
//        DummyBox(modifier = Modifier.clip(RectangleShape))
        DummyBox(modifier = Modifier.clip(CircleShape))
        DummyBox(modifier = Modifier.clip(RoundedCornerShape(20.dp)))
        DummyBox(modifier = Modifier.clip(CutCornerShape(20.dp)))
        DummyBox(modifier = Modifier.clip(TriangleShape()))
        DummyBox(modifier = Modifier.clip(PolyShape(polySides, 100f)))

        Text(text = "polySides: $polySides")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                polySides += 1
            }) {
                Text(text = "polySides + 1")
            }
            Button(onClick = {
                polySides = 3
            }) {
                Text(text = "polySides = 3")
            }
        }
    }
}

@Composable
fun DummyBox(modifier: Modifier) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)

    val randomColor = Color(red, green, blue)

    Box(
        modifier = modifier
            .background(randomColor)
            .size(100.dp)
    )
}

class TriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path = path)
    }
}

class PolyShape(private val sides: Int, private val radius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply {
            this.polygon(sides, radius, size.center)
        })
    }
}

fun Path.polygon(sides: Int, radius: Float, center: Offset) {
    val angle = 2.0 * Math.PI / sides
    moveTo(
        x = center.x + (radius * cos(0.0)).toFloat(),
        y = center.y + (radius * kotlin.math.sin(0.0)).toFloat()
    )
    for (i in 1 until sides) {
        lineTo(
            x=center.x+(radius * kotlin.math.cos(angle * i)).toFloat(),
            y=center.x+(radius * kotlin.math.sin(angle * i)).toFloat()
        )
    }
    close()
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    ComposeBasicTheme {
        ShapeContainer()
    }
}
package com.unithon.composebasic.ui.practice.practice03.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = com.unithon.composebasic.ui.practice.practice04.ui.theme.Purple200,
    primaryVariant = com.unithon.composebasic.ui.practice.practice04.ui.theme.Purple700,
    secondary = com.unithon.composebasic.ui.practice.practice04.ui.theme.Teal200
)

private val LightColorPalette = lightColors(
    primary = com.unithon.composebasic.ui.practice.practice04.ui.theme.Purple500,
    primaryVariant = com.unithon.composebasic.ui.practice.practice04.ui.theme.Purple700,
    secondary = com.unithon.composebasic.ui.practice.practice04.ui.theme.Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ComposeBasicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
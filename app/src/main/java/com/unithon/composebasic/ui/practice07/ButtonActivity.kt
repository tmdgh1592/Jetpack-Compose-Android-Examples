package com.unithon.composebasic.ui.practice07

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.unithon.composebasic.ui.practice07.ui.theme.ComposeBasicTheme
import com.unithon.composebasic.ui.practice07.util.drawColoredShadow

class ButtonActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ButtonsContainer()
                }
            }
        }
    }
}


//onClick: () -> Unit,
//modifier: Modifier = Modifier,
//enabled: Boolean = true,
//interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//elevation: ButtonElevation? = ButtonDefaults.elevation(),
//shape: Shape = MaterialTheme.shapes.small,
//border: BorderStroke? = null,
//colors: ButtonColors = ButtonDefaults.buttonColors(),
//contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
//content: @Composable RowScope.() -> Unit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ButtonsContainer() {

    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Yellow, Color.Red))
    
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressStatusTitle = if (isPressed) "버튼을 누르고 있다." else "버튼에서 손을 땠다."

    val interactionSourceForSecond = remember { MutableInteractionSource() }
    val isPressedForSecond by interactionSourceForSecond.collectIsPressedAsState()
    val size = if (isPressedForSecond) 0.dp else 20.dp
    
    val pressedBtnRadiusWithAnim: Dp by animateDpAsState(targetValue = size)


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            elevation = ButtonDefaults.elevation(0.dp),
            enabled = true,
            onClick = {
                Log.d("TAG", "ButtonsContainer: 버튼 1 클릭")
            },
        ) {
            Text(text = "버튼 1")
        }

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = false,
            onClick = {
                Log.d("TAG", "ButtonsContainer: 버튼 2 클릭")
            },
        ) {
            Text(text = "버튼 2")
        }

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            shape = CircleShape,
            onClick = {
                Log.d("TAG", "ButtonsContainer: 버튼 3 클릭")
            },
        ) {
            Text(text = "버튼 3")
        }

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            border = BorderStroke(4.dp, Color.Yellow),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                Log.d("TAG", "ButtonsContainer: 버튼 4 클릭")
            },
        ) {
            Text(text = "버튼 4")
        }

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            contentPadding = PaddingValues(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                Log.d("TAG", "ButtonsContainer: 버튼 5 클릭")
            },
        ) {
            Text(text = "버튼 5")
        }


//        color: Color,
//        alpha: Float = 0.2f,
//        borderRadius: Dp = 0.dp,
//        shadowRadius: Dp = 20.dp,
//        offsetY: Dp = 0.dp,
//        offsetX: Dp = 0.dp
        Button(
            modifier = Modifier.drawColoredShadow(
                color = Color.Red,
                alpha = 0.5f,
                borderRadius = 0.dp,
                shadowRadius = 20.dp,
                offsetX = 0.dp,
                offsetY = 0.dp
            ),
            interactionSource = interactionSource,
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.LightGray
            ),
            enabled = false,
            border = BorderStroke(4.dp, buttonBorderGradient),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                Log.d("TAG", "ButtonsContainer: 버튼 6 클릭")
            },
        ) {
            Text(text = "버튼 6", style = TextStyle(color = Color.White))
        }

        Button(
            modifier = Modifier.drawColoredShadow(
                color = Color.Red,
                alpha = 0.5f,
                borderRadius = 10.dp,
                shadowRadius = pressedBtnRadiusWithAnim,
                offsetX = 0.dp,
                offsetY = 0.dp
            ),
            interactionSource = interactionSourceForSecond,
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.LightGray
            ),
            enabled = true,
            border = BorderStroke(4.dp, buttonBorderGradient),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                Log.d("TAG", "ButtonsContainer: 버튼 6 클릭")
            },
        ) {
            Text(text = "버튼 6", style = TextStyle(color = Color.White))
        }

        Text(text = "$pressStatusTitle")
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    ComposeBasicTheme {
        ButtonsContainer()
    }
}
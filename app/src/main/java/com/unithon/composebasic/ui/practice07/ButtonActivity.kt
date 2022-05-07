package com.unithon.composebasic.ui.practice07

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unithon.composebasic.ui.practice07.ui.theme.ComposeBasicTheme

class ButtonActivity : ComponentActivity() {
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

@Composable
fun ButtonsContainer() {

    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Yellow, Color.Red))

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
            contentPadding = PaddingValues(top=20.dp, bottom=10.dp, start=20.dp, end=10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                Log.d("TAG", "ButtonsContainer: 버튼 5 클릭")
            },
        ) {
            Text(text = "버튼 5")
        }

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            colors=ButtonDefaults.buttonColors(
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
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    ComposeBasicTheme {
        ButtonsContainer()
    }
}
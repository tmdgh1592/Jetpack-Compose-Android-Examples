package com.unithon.composebasic.ui.practice04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unithon.composebasic.ui.practice03.DummyBox
import com.unithon.composebasic.ui.practice04.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    BoxWithConstraintContainer()
                }
            }
        }
    }
}


// BoxWithConstraint
// BoxWithConstraintsScope 를 사용하여 Box 의 크기에 따라 설정이 가능함.

@Composable
fun BoxWithConstraintContainer(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (this.minHeight > 400.dp) {
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        } else{
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Yellow)
        }
        Column() {
            Text(text = "minHeight : ${this@BoxWithConstraints.minHeight}")
            Text(text = "maxHeight : ${this@BoxWithConstraints.maxHeight}")
        }

//        Column() {
//            BoxWithConstraintItem(modifier = modifier
//                .size(200.dp)
//                .background(Color.Yellow))
//            BoxWithConstraintItem(modifier = modifier
//                .size(300.dp)
//                .background(Color.Green))
//        }

    }
}


@Composable
fun BoxWithConstraintItem(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        if (this.minWidth > 200.dp) {
            Text(text = "이것은 큰 상자이다")
        } else {
            Text(text = "이것은 작은 상자이다")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeBasicTheme {
        BoxWithConstraintContainer()
    }
}
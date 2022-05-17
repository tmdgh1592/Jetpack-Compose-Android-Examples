package com.unithon.composebasic.ui.practice.practice02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unithon.composebasic.ui.practice.practice02.ui.theme.ComposeBasicTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Container()
                }
            }
        }
    }
}

// Row 의 핵심 arrangement, alignment
// arrangement 는 Row, Column 같은 요소들이 들어가는
// 컨테이너 성격의 Composable 에서 요소들의 아이템을 정렬할 때 사용됩니다.

// Arrangement.Start : 왼쪽으로
// Arrangement.End : 오른쪽으로
// Arrangement.SpaceBetween : 공간 모두 차지
// Arrangement.SpaceAround : 빈 공간을 남겨두기
// Arrangement.Center : 요소들에 넣기
// Arrangement.SpaceBetween : 사이에 공간을 밀어넣기
// Arrangement.SpaceEvenly : 요소들 사이에 공간을 똑같이 하기


// alignment 는 말 그대로 해당 컨테이너 안에 들어있는 요소들의 위치를 어떠한 방향으로 정렬할지 설정합니다.
// linearlayout 에서 gravity 의 역할을 수행합니다.
// Alignment.Bottom : 컨테이너의 아래에 두기
// Alignment.Top : 컨테이너의 위에 두기
// 현재는 Row Composable 안에서 align 이 들어가기 때문에 Center Vertically
// Alignment.CenterVertically : 컨테이너의 수직방향으로 중앙에 두기


@Composable
fun Container(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}

@Composable
fun DummyBox(modifier: Modifier = Modifier) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    val randomColor = Color(red, green, blue)

    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor))
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeBasicTheme {
        Container()
    }
}
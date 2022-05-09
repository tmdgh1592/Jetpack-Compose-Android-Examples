package com.unithon.composebasic.ui.practice09

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.unithon.composebasic.ui.practice09.ui.theme.ComposeBasicTheme
import kotlinx.coroutines.launch

class SnackbarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MySnackbar()
                }
            }
        }
    }
}

@Composable
fun MySnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val buttonTitle: (SnackbarData?) -> String = { snackbarData ->
        if (snackbarData != null) "스낵바 숨기기" else "스낵바 보여주기"
    }
    val buttonColor: (SnackbarData?) -> Color = { snackbarData ->
        if (snackbarData != null) Color.Black else Color.Blue
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 스낵바가 보여지고 있으면 currentSnackbarData가 null이 아님.
        // 스낵바에 대한 정보를 가지고 있음
        Button(colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor(snackbarHostState.currentSnackbarData),
            contentColor = Color.White
        ), onClick = {
            // 스낵바가 보여지고 있으면
            // 버튼을 눌렀을 때 스낵바를 다시 닫음.
            if (snackbarHostState.currentSnackbarData != null) {
                snackbarHostState.currentSnackbarData?.dismiss()
                return@Button
            }

            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    "오늘도 빡코딩?!",
                    actionLabel = "확인",
                    duration = SnackbarDuration.Short
                ).let {
                    when (it) {
                        SnackbarResult.Dismissed -> Log.d("TAG", "MySnackbar: 스낵바 닫아짐")
                        SnackbarResult.ActionPerformed -> Log.d("TAG","MySnackbar: 스낵바 확인 버튼 클릭")
                    }
                }

            }
        }) {
            Text(text = buttonTitle(snackbarHostState.currentSnackbarData))
        }

        // 스낵바가 보여지는 부분
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview9() {
    ComposeBasicTheme {
        MySnackbar()
    }
}
package com.unithon.composebasic.ui.practice.practice08

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unithon.composebasic.R
import com.unithon.composebasic.ui.practice.practice08.ui.theme.ComposeBasicTheme

class CheckBoxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CheckBoxContainer()
                }
            }
        }
    }
}


//checked: Boolean,
//onCheckedChange: ((Boolean) -> Unit)?,
//modifier: Modifier = Modifier,
//enabled: Boolean = true,
//interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//colors: CheckboxColors = CheckboxDefaults.colors()

@Composable
fun CheckBoxContainer() {

    val checkedStatusForFirst = remember { mutableStateOf(false) }
    val checkedStatusForSecond = remember { mutableStateOf(false) }
    val checkedStatusForThird = remember { mutableStateOf(false) }
//    val checkedStatusForForth = remember { mutableStateOf(false) }

    val checkedStatesArray = listOf(
        checkedStatusForFirst,
        checkedStatusForSecond,
        checkedStatusForThird,
    )

    val allBoxChecked: (Boolean) -> Unit = { isAllBoxChecked ->
        Log.d("TAG", "CheckBoxContainer: : $isAllBoxChecked")
        checkedStatesArray.forEach { it.value = isAllBoxChecked }
    }
    val checkedStatusForForth: Boolean = checkedStatesArray.all { it.value }

//    var checkedStatusForSecond by remember { mutableStateOf(false) }
//    val (checkedStatusForThird, setCheckedStatusForThird) = remember { mutableStateOf(false) }
//    val (checkedStatusForFourth, setCheckedStatusForFourth) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CheckBoxWithTitle(title = "1번 확인사항", checkedStatusForFirst)
        CheckBoxWithTitle(title = "2번 확인사항", checkedStatusForSecond)
        CheckBoxWithTitle(title = "3번 확인사항", checkedStatusForThird)
//        Checkbox(checked = checkedStatusForSecond,
//            enabled = true,
//            onCheckedChange = { isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                checkedStatusForSecond = isChecked
//            })
//
//        Checkbox(checked = checkedStatusForThird,
//            enabled = true,
//            onCheckedChange = { isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                setCheckedStatusForThird.invoke(isChecked)
//            })
        Spacer(modifier = Modifier.height(10.dp))
        AllAgreeCheckBox(title = "모두 동의하십니까?", checkedStatusForForth, allBoxChecked)
        Spacer(modifier = Modifier.height(10.dp))
        MyCustomCheckBox(title = "커스텀 체크박스입니다", withRipple = true)
        MyCustomCheckBox(title = "커스텀 체크박스입니다")
//        checkedColor: Color = MaterialTheme.colors.secondary,
//        uncheckedColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
//        checkmarkColor: Color = MaterialTheme.colors.surface,
//        disabledColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
//        disabledIndeterminateColor: Color = checkedColor.copy(alpha = ContentAlpha.disabled)
//        Checkbox(
//            checked = checkedStatusForFourth,
//            enabled = true,
//            onCheckedChange = { isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                setCheckedStatusForFourth.invoke(isChecked)
//            },
//            colors = CheckboxDefaults.colors(
//                checkedColor = Color.Red,
//                uncheckedColor = Color(0xFFEF9A9A),
//                checkmarkColor = Color.Black,
//                disabledColor = Color(0xFF90CAF9)
//            )
//        )
    }
}

@Composable
fun CheckBoxWithTitle(
    title: String,
    isCheckedState: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(checked = isCheckedState.value,
            enabled = true,
            onCheckedChange = { isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
                isCheckedState.value = isChecked
            })
        Text(text = title)
    }
}

@Composable
fun AllAgreeCheckBox(
    title: String,
    shouldChecked: Boolean,
    allBoxChecked: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(
            checked = shouldChecked,
            enabled = true,
            onCheckedChange = { isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                isCheckedState.value = isChecked
                allBoxChecked(isChecked)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color(0xFFEF9A9A),
                checkmarkColor = Color.Black,
                disabledColor = Color(0xFF90CAF9)
            )
        )
        Text(text = title)
    }
}

@Composable
fun MyCustomCheckBox(
    title: String,
    withRipple: Boolean = false
) {
    val (isChecked, setChecked) = remember { mutableStateOf(false) }
    val togglePainter = if (isChecked) R.drawable.ic_checked_icon else R.drawable.ic_unchecked_icon
    val checkedInfoString = if (isChecked) "체크됨" else "체크 안 됨"


//        bounded: Boolean = true, // true -> ripple이 클릭한 곳 기준에서 퍼짐, false -> ripple이 중앙에서 시작해서 퍼짐
//        radius: Dp = Dp.Unspecified, // ripple이 퍼질 반경
//        color: Color = Color.Unspecified // ripple의 색상

    val rippleEffect = if (withRipple) rememberRipple(
        radius = 30.dp,
        bounded = false,
        color = Color.Blue
    ) else null

    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .size(80.dp)
            .padding(10.dp)
            .clickable(
                indication = rippleEffect,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                setChecked(!isChecked)
                Log.d("TAG", "MyCustomCheckBox: 클릭이 되었다!")
            },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = togglePainter),
                contentDescription = null
            )

        }
//        Checkbox(checked = isChecked,
//            enabled = true,
//            onCheckedChange = { isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                setChecked(isChecked)
//            })
        Text(text = "$title / $checkedInfoString")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview8() {
    ComposeBasicTheme {
        CheckBoxContainer()
    }
}
package com.unithon.composebasic.ui.practice05

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unithon.composebasic.R
import com.unithon.composebasic.ui.practice05.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AnnotatedClickableText()
                }
            }
        }
    }
}

//text: String,
//modifier: Modifier = Modifier,
//color: Color = Color.Unspecified,
//fontSize: TextUnit = TextUnit.Unspecified,
//fontStyle: FontStyle? = null,
//fontWeight: FontWeight? = null,
//fontFamily: FontFamily? = null,
//letterSpacing: TextUnit = TextUnit.Unspecified,
//textDecoration: TextDecoration? = null,
//textAlign: TextAlign? = null,
//lineHeight: TextUnit = TextUnit.Unspecified,
//overflow: TextOverflow = TextOverflow.Clip,
//softWrap: Boolean = true,
//maxLines: Int = Int.MAX_VALUE,
//onTextLayout: (TextLayoutResult) -> Unit = {},
//style: TextStyle = LocalTextStyle.current


@Composable
fun AnnotatedClickableText(context: Context = LocalContext.current) {
    val annotatedText = buildAnnotatedString {
        append("Click ")

        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(tag = "URL",
            annotation = "https://developer.android.com")
        withStyle(style = SpanStyle(color = Color.Blue,
            fontWeight = FontWeight.Bold)) {
            append("here")
        }
        pop()

    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText.getStringAnnotations(tag = "URL", start = offset,
                end = offset)
                .firstOrNull()?.let { annotation ->
                    // If yes, we log its value
                    Toast.makeText(context, "${annotation.item}", Toast.LENGTH_SHORT).show()
                }
        }
    )
}

@Composable
fun TextContainer() {
    val name = "Klein"
    val words = stringResource(id = R.string.dummy_short_text)
    val wordsArray = words.split(" ")

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "안녕하세요? 오늘도 빡코딩~! $name",
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = "안녕하세요? 오늘도 빡코딩~! $name",
            style = TextStyle(textAlign = TextAlign.Start),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = stringResource(id = R.string.dummy_short_text),
            maxLines = 3,
            fontWeight = FontWeight.W200,
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                textAlign = TextAlign.Justify,
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough,
                        TextDecoration.Underline
                    )
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = stringResource(id = R.string.dummy_short_text),
            style = TextStyle(textAlign = TextAlign.Start),
            overflow = TextOverflow.Visible,
            maxLines = 3,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
            fontFamily = FontFamily(Font(R.font.cafe24, weight = FontWeight.ExtraBold)),
            lineHeight = 40.sp
        )
        Text(text = buildAnnotatedString {
            append("안녕하세요?")
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            ) {
                append("개발하는 BuNa 입니다!")
            }
            withStyle(
                style = SpanStyle(color = Color.Red)
            ) {
                append("빡!코딩")
            }
        })
        Text(text = buildAnnotatedString {
            wordsArray.forEach {
                if (it.contains("낙원")) {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )
                    ) {
                        append("$it ")
                    }
                } else {
                    append("$it ")
                }
            }
        })

        val context = LocalContext.current
        ClickableText(text = AnnotatedString("Click Me!"), onClick = { offset->
            Toast.makeText(context, "$offset is Clicked!", Toast.LENGTH_SHORT).show()
        })

        SelectionContainer(modifier = Modifier
            .background(Color.Yellow)
            .padding(3.dp)) {
            Column {
                Text(text = "선택이 가능한 텍스트입니다!")
                DisableSelection {
                    Text(text = "선택이 가능한 텍스트입니다!\n".repeat(50), maxLines = 2)
                }
                Text(text = "선택이 가능한 텍스트입니다!")
            }
        }

        Text(
            text = stringResource(id = R.string.dummy_long_text),
            style = TextStyle(lineHeight = 20.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    ComposeBasicTheme {
        TextContainer()
    }
}
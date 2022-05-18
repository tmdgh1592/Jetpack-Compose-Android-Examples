package com.unithon.composebasic.ui.jetpack.day1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unithon.composebasic.ui.jetpack.day1.ui.theme.ComposeBasicTheme
import com.unithon.composebasic.ui.ui.theme.Green200
import com.unithon.composebasic.ui.ui.theme.Red200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

//@Composable
//private fun ContentView() {
//    Surface(color = Green200, modifier = Modifier) {
//        //Greeting("Android")
//        MyColumn(modifier = Modifier.padding(10.dp))
//    }
//}

//@Composable
//private fun Greeting(name: String) {
//    Surface(color = MaterialTheme.colors.primary) {
//        Column(modifier = Modifier.padding(24.dp)) {
//            Text(text = "Hello,")
//            Text(text = name)
//        }
//    }
//}

@Composable
private fun Greeting(name: String) {

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(
                onClick = { /* TODO */ }
            ) {
                Text("Show more")
            }
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "오늘도 $name!")
//}

//@Composable
//fun MyColumn(names: List<String> = listOf("바나나", " 딸기", "포도"), modifier: Modifier) {
//    Column(modifier = modifier) {
//        names.forEach {
//            FruitView(name = it)
//        }
//    }
//}

//@Composable
//fun FruitView(name: String) {
//    Surface(color = Red200,
//        modifier = Modifier.clickable {
//            Log.d("TAG", "FruitView: $name")
//        }) {
//        Text(
//            text = name,
//            modifier = Modifier.padding(10.dp)
//        )
//    }
//}


//@Composable
//private fun Greeting(name: String) {
//    Surface(color = MaterialTheme.colors.primary) {
//        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
//    }
//}

//@Composable
//private fun HelloWorld(modifier: Modifier) {
//    Surface(color = Green200, modifier = modifier) {
//        Text(text = "HELLO WORLD", color = Color.White, modifier = Modifier.padding(all = 30.dp))
//    }
//}

//@Composable
//fun HelloWorld() {
//    Text(text = "HELLO WORLD")
//}

@Composable
fun MyApp(names: List<String> = listOf("World", "Compose")) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Preview(showBackground = true, name = "Text Preview", )
@Composable
fun DefaultPreview13() {
    ComposeBasicTheme {
        MyApp()
    }
}
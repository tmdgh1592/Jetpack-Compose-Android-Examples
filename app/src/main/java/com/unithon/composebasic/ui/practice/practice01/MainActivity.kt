package com.unithon.composebasic.ui.practice.practice01

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.unithon.composebasic.R
import com.unithon.composebasic.ui.practice.practice01.utils.DummyDataProvider
import com.unithon.composebasic.ui.practice.practice01.utils.RandomUser
import com.unithon.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                ContentView()
            }
        }
    }
}

@Composable
fun ContentView() {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(backgroundColor = Color.White,
            topBar = { MyAppBar() }
        ) {
            RandomUserListView(randomUsers = DummyDataProvider.userList)
        }
    }
}

@Composable
fun MyAppBar() {
    TopAppBar(
        elevation = 10.dp,
        modifier = Modifier.height(56.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Text(
            text = stringResource(id = R.string.practice_01_app_name),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black
        )
    }
}


@Composable
fun RandomUserListView(randomUsers: List<RandomUser>) {
    // 메모리 관리가 들어간 LazyColumn
    LazyColumn {
        items(randomUsers) { RandomUserView(randomUser = it) }
    }
}


@Composable
fun RandomUserView(randomUser: RandomUser) {
    val typography = MaterialTheme.typography

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

//            Box(
//                modifier = Modifier
//                    .size(60.dp, 60.dp)
//                    .clip(CircleShape)
//                    .background(Color.Green)
//            )

            ProfileImage(imgUrl = randomUser.profileImage)
            Column {
                Text(text = randomUser.name, style = typography.subtitle1)
                Text(text = randomUser.description, style = typography.body1)
            }
        }

    }
}


@Composable
fun ProfileImage(imgUrl: String, modifier: Modifier = Modifier) {
    // 이미지 비트맵
    // ''remember'' 를 통해 recompose 시에 값을 저장하여 유지할 수 있음
    // Composable 은 State 외에도 LiveData, Flow, RxJava2를 observing 함.
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    val imageModifier = modifier
        .size(50.dp, 50.dp)
        .clip(CircleShape)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>() {
            // 이미지가 로드되었을 때
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    // 비트맵이 있다면
    bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
        Image(
            bitmap = fetchedBitmap,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
    } ?: Image(
        painter = painterResource(id = R.drawable.ic_empty_user_img),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = imageModifier
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        ContentView()
    }
}
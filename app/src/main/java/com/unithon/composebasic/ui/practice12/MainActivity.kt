package com.unithon.composebasic.ui.practice12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unithon.composebasic.ui.practice12.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}

// 네비게이션 라우트 이넘 (값을 가지는 이넘)
enum class NAV_ROUTE(val routeName: String, val description: String, val color: Color) {
    MAIN("MAIN", "메인 화면", Color(0xFFFF7043)),
    LOGIN("LOGIN", "로그인 화면", Color(0xFFEF5350)),
    REGISTER("REGISTER", "회원가입 화면", Color(0xFFAB47BC)),
    USER_PROFILE("USER_PROFILE", "유저 프로필 화면", Color(0xFF5C6BC0)),
    SETTING("SETTING", "설정 화면", Color(0xFF9CCC65))
}

class RouteAction(navHostController: NavHostController) {

    // 특정 라우트로 이동
    val navTo: (NAV_ROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }

    // 뒤로가기 이동
    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }
}

@Composable
fun NavigationGraph(startRoute: NAV_ROUTE = NAV_ROUTE.MAIN) {

    // 네비게이션 컨트롤러
    val navController = rememberNavController()

    // 네비게이션 라우트 액션
    val routeAction = remember(navController) {
        RouteAction(navController)
    }

    // NavHost 로 네비게이션 결정
    // 네비게이션 연결할 녀석들을 설정한다
    NavHost(navController = navController, startRoute.routeName) {
        // 라우트 이름 = 화면의 키
        composable(NAV_ROUTE.MAIN.routeName) {
            // 화면 - 값
            MainScreen(routeAction = routeAction)
        }

        composable(NAV_ROUTE.LOGIN.routeName) {
            // 화면 - 값
            LoginScreen(routeAction = routeAction)
        }

        composable(NAV_ROUTE.REGISTER.routeName) {
            // 화면 - 값
            MainScreen(routeAction = routeAction)
        }

        composable(NAV_ROUTE.USER_PROFILE.routeName) {
            // 화면 - 값
            MainScreen(routeAction = routeAction)
        }

        composable(NAV_ROUTE.SETTING.routeName) {
            // 화면 - 값
            MainScreen(routeAction = routeAction)
        }
    }
}

// 메인 화면
@Composable
fun MainScreen(routeAction: RouteAction) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(16.dp)) {
            NavButton(NAV_ROUTE.MAIN, routeAction)
            NavButton(NAV_ROUTE.LOGIN, routeAction)
            NavButton(NAV_ROUTE.REGISTER, routeAction)
            NavButton(NAV_ROUTE.USER_PROFILE, routeAction)
            NavButton(NAV_ROUTE.SETTING, routeAction)
        }
    }
}

// 로그인 화면
@Composable
fun LoginScreen(routeAction: RouteAction) {
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "로그인 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            Button(
                onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)
            ) {
                Text(text = "뒤로가기")
            }
        }
    }
}

// 콜럼에 있는 네비게이션 버튼
@Composable
fun ColumnScope.NavButton(route: NAV_ROUTE, routeAction: RouteAction) {
    Button(
        onClick = {
            routeAction.navTo(route)
        }, colors = ButtonDefaults.buttonColors(backgroundColor = route.color),
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(
            text = route.description,
            style = TextStyle(Color.White, 22.sp, FontWeight.Medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview12() {
    ComposeBasicTheme {
        NavigationGraph()
    }
}